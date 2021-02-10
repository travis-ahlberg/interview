package com.iesmach.interview.movies;

import java.util.List;
import java.util.Map;

public interface MoviesService {
    List<Movie> findAll();

    Map<Integer, Integer> findCountByReleaseYear();
}
