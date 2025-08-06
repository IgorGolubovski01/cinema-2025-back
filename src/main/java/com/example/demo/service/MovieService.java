package com.example.demo.service;

import com.example.demo.entity.Movie;
import com.example.demo.repository.MovieRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class MovieService {

    private final MovieRepo movieRepo;


    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieRepo.findAll());
    }

    public ResponseEntity<Movie> getPromoMovie() {
        return new ResponseEntity<>(movieRepo.findByPromo(true), HttpStatus.OK);
    }

    public ResponseEntity<Movie> getMovieById(Integer id) {
        Movie m = movieRepo.findById(id).orElse(null);
        return new ResponseEntity<>(m, HttpStatus.OK);
    }
}
