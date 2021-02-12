package com.iesmach.interview.movies;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.Month.APRIL;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;

@Service
class MoviesServiceImpl implements MoviesService {

    private final MovieRepository repository;
    private final JdbcTemplate jdbcTemplate;

    MoviesServiceImpl(MovieRepository repository, JdbcTemplate jdbcTemplate) {
        this.repository = repository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void populateDB() {
        repository.saveAll(List.of(
                new Movie("UHF", YearMonth.of(1987, MARCH)),
                new Movie("Dave", YearMonth.of(1992, APRIL)),
                new Movie("What About Bob", YearMonth.of(1992, MAY)),
                new Movie("Colors", YearMonth.of(1989, FEBRUARY)),
                new Movie("Short Circuit", YearMonth.of(1988, JANUARY)),
                new Movie("Conan", YearMonth.of(1986, MARCH)),
                new Movie("Flowers In the Attic", YearMonth.of(1991, MAY)),
                new Movie("Mystic Pizza", YearMonth.of(1988, MAY)),
                new Movie("North Shore", YearMonth.of(1987, MAY)),
                new Movie("Cliff Hanger", YearMonth.of(1989, FEBRUARY)),
                new Movie("American Tail", YearMonth.of(1986, MARCH))
        ));

        System.out.println("Populated the database!");
    }

    @Override
    public List<Movie> findAll(@Nullable Integer year, @Nullable Month month) {

        List<String> ignorePaths = new ArrayList<>();
        ignorePaths.add("title");

        Movie exampleMovie = new Movie();

        if(year != null) exampleMovie.setYear(year);
        else ignorePaths.add("year");

        if(month != null) exampleMovie.setMonth(month.getValue());
        else ignorePaths.add("month");

        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnorePaths(ignorePaths.toArray(new String[0]));
        Example<Movie> example = Example.of(exampleMovie, matcher);

        return repository.findAll(example);
    }

    @Override
    public Map<Integer, Integer> findCountByReleaseYear() {
        Map<Integer, Integer> result = new HashMap<>();

        jdbcTemplate.query("SELECT year, COUNT(*) FROM Movie GROUP BY year", (RowCallbackHandler) rs ->
                result.put(rs.getInt(1), rs.getInt(2)));

        return result;
    }
}
