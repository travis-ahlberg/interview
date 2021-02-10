package com.iesmach.interview.movies;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class MoviesServiceImplTest {

    @Autowired
    private MoviesService service;

    @Test
    public void testReturnsMovieCountsByReleaseYear() throws Exception {
        Map<Integer, Integer> movieCountByYear = service.findCountByReleaseYear();

        assertEquals(Map.of(
                1986, 2,
                1987, 2,
                1988, 2,
                1989, 2,
                1991, 1,
                1992, 2
        ), movieCountByYear);
    }
}
