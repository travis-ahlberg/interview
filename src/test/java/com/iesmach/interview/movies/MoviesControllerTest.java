package com.iesmach.interview.movies;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MoviesControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getMovies() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/movies")
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("UHF\n" +
                        "Dave\n" +
                        "What About Bob\n" +
                        "Colors\n" +
                        "Short Circuit\n" +
                        "Conan\n" +
                        "Flowers In the Attic\n" +
                        "Mystic Pizza\n" +
                        "North Shore\n" +
                        "Cliff Hanger\n" +
                        "American Tail")));
    }

    @Test
    public void getMoviesReleasedIn1987() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/movies?year=1987")
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("UHF\n" +
                        "North Shore")));
    }

    @Test
    public void getMoviesReleasedInMay() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/movies?month=MAY")
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("What About Bob\n" +
                        "Flowers In the Attic\n" +
                        "Mystic Pizza\n" +
                        "North Shore")));
    }
    
}
