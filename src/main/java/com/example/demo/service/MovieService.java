package com.example.demo.service;

import com.example.demo.entity.Movie;
import com.example.demo.repository.MovieRepo;
import lombok.AllArgsConstructor;
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
}
