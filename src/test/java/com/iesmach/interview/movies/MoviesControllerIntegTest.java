package com.iesmach.interview.movies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class MoviesControllerIntegTest {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @BeforeEach
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:%d/movies".formatted(port));
    }

    @Test
    public void find_returnsAllMovies() throws Exception {

        // Arrange
        String expected = "UHF\n" +
                "Dave\n" +
                "What About Bob\n" +
                "Colors\n" +
                "Short Circuit\n" +
                "Conan\n" +
                "Flowers In the Attic\n" +
                "Mystic Pizza\n" +
                "North Shore\n" +
                "Cliff Hanger\n" +
                "American Tail\n";

        // Act & Assert
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        assertThat(response.getBody()).isEqualTo(expected);
    }
}
