package com.iesmach.interview.movies;

import org.springframework.lang.Nullable;

import java.time.Month;
import java.util.List;
import java.util.Map;

public interface MoviesService {
    List<Movie> findAll(@Nullable Integer year, @Nullable Month month);

    Map<Integer, Integer> findCountByReleaseYear();
}
