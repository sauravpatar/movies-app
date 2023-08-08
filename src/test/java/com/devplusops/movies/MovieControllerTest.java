package com.devplusops.movies;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class MovieControllerTest {

    private MovieController movieController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        movieController = new MovieController();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetAllMovies() throws Exception {
        // Mock movie data
        Movie movie1 = new Movie("The Shawshank Redemption", 1994, "Drama");
        Movie movie2 = new Movie("The Godfather", 1972, "Crime");
        List<Movie> movies = Arrays.asList(movie1, movie2);

        // Perform the GET request
        MockHttpServletResponse response = standaloneSetup(movieController)
                .build()
                .perform(get("/movies"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        // Verify the response
        List<Movie> responseMovies = Arrays.asList(objectMapper.readValue(response.getContentAsString(), Movie[].class));
        assertEquals(16, responseMovies.size());
        assertEquals(movie1.getTitle(), responseMovies.get(0).getTitle());
        assertEquals(movie1.getYear(), responseMovies.get(0).getYear());
        assertEquals(movie1.getGenre(), responseMovies.get(0).getGenre());
        assertEquals(movie2.getTitle(), responseMovies.get(1).getTitle());
        assertEquals(movie2.getYear(), responseMovies.get(1).getYear());
        assertEquals(movie2.getGenre(), responseMovies.get(1).getGenre());
    }
}
