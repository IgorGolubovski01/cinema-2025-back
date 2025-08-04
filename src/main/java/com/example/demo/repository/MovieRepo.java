package com.example.demo.repository;

import com.example.demo.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Integer> {
    Movie findByPromo(boolean promo);
}
