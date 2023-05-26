package com.devplusops.movies;

import org.springframework.web.bind.annotation.*;
//import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
//import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
//import java.util.Scanner;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private List<Movie> movies = new ArrayList<>();


    @GetMapping
    public List<Movie> getAllMovies() {

        try {
            movies.clear();
            InputStream inputStream = getClass().getResourceAsStream("/movies.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    
            // Skip the header line
            reader.readLine();
    
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
    
                if (fields.length >= 3) {
                    String title = fields[0];
                    int year = Integer.parseInt(fields[1]);
                    String genre = fields[2];
    
                    Movie movie = new Movie(title, year, genre);
                    movies.add(movie);
                }
            }
    
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        
        return movies;
    }

    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        movies.add(movie);
        return movie;
    }
}
