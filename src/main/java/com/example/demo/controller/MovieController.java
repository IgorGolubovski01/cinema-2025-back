package com.example.demo.controller;

import com.example.demo.entity.Movie;
import com.example.demo.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("movie")
@CrossOrigin(origins = "http://localhost:4200")
public class MovieController {

    private final MovieService movieService;

    @GetMapping("getAllMovies")
    public ResponseEntity<Page<Movie>> getAllMovies(
            @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "12") int size
    ) {
        return movieService.getAllMovies(page,size);
    }

    @GetMapping("getPromoMovie")
    public ResponseEntity<Movie> getPromoMovie() {
        return movieService.getPromoMovie();
    }

    @GetMapping("getMovieById/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Integer id) {
        return movieService.getMovieById(id);
    }
}
