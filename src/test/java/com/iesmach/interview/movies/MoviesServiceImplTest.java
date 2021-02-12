package com.iesmach.interview.movies;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.time.Month.*;
import static java.time.Month.MARCH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class MoviesServiceImplTest {

    @Autowired
    private MoviesService service;

    @Test
    public void findCountByReleaseYear_returnsMovieCountByReleaseYear() throws Exception {

        // Arrange
        Map<Integer, Integer> expected = Map.of(
                1986, 2,
                1987, 2,
                1988, 2,
                1989, 2,
                1991, 1,
                1992, 2
        );

        // Act
        Map<Integer, Integer> actual = service.findCountByReleaseYear();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void findAll_returnsAllMovies() throws Exception {

        // Arrange
        List<Movie> expected = new ArrayList<>() {{
            add(new Movie("UHF", YearMonth.of(1987, MARCH)));
            add(new Movie("Dave", YearMonth.of(1992, APRIL)));
            add(new Movie("What About Bob", YearMonth.of(1992, MAY)));
            add(new Movie("Colors", YearMonth.of(1989, FEBRUARY)));
            add(new Movie("Short Circuit", YearMonth.of(1988, JANUARY)));
            add(new Movie("Conan", YearMonth.of(1986, MARCH)));
            add(new Movie("Flowers In the Attic", YearMonth.of(1991, MAY)));
            add(new Movie("Mystic Pizza", YearMonth.of(1988, MAY)));
            add(new Movie("North Shore", YearMonth.of(1987, MAY)));
            add(new Movie("Cliff Hanger", YearMonth.of(1989, FEBRUARY)));
            add(new Movie("American Tail", YearMonth.of(1986, MARCH)));
        }};

        // Act
        List<Movie> actual = service.findAll(null, null);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void findAll_givenYearMonth_returnsFilteredMovies() throws Exception {

        // Arrange
        Integer year = 1992;
        Month month = MAY;
        List<Movie> expected = new ArrayList<>() {{
            add(new Movie("What About Bob", YearMonth.of(1992, MAY)));
        }};

        // Act
        List<Movie> actual = service.findAll(year, month);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void findAll_givenMonth_returnsFilteredMovies() throws Exception {

        // Arrange
        Month month = MARCH;
        List<Movie> expected = new ArrayList<>() {{
            add(new Movie("UHF", YearMonth.of(1987, MARCH)));
            add(new Movie("Conan", YearMonth.of(1986, MARCH)));
            add(new Movie("American Tail", YearMonth.of(1986, MARCH)));
        }};

        // Act
        List<Movie> actual = service.findAll(null, month);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void findAll_givenYear_returnsFilteredMovies() throws Exception {

        // Arrange
        Integer year = 1988;
        List<Movie> expected = new ArrayList<>() {{
            add(new Movie("Short Circuit", YearMonth.of(1988, JANUARY)));
            add(new Movie("Mystic Pizza", YearMonth.of(1988, MAY)));
        }};

        // Act
        List<Movie> actual = service.findAll(year, null);

        // Assert
        assertEquals(expected, actual);
    }
}
