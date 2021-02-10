package com.iesmach.interview.movies;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Month;

@RestController
public class MoviesController {

    private final MoviesService moviesService;

    public MoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @RequestMapping("/movies")
    public String find(@RequestParam(value = "year", required = false) Integer year,
                       @RequestParam(value = "month", required = false) Month month) {
        // TODO: Replace with a Java stream
        StringBuilder sb = new StringBuilder();
        for (Movie movie : moviesService.findAll()) {
            sb.append(movie.getTitle()).append("\n");
        }
        return sb.toString();
    }

}
