package com.example.demo.service;

import com.example.demo.entity.Movie;
import com.example.demo.entity.OrderEntity;
import com.example.demo.entity.OrderStatus;
import com.example.demo.entity.User;
import com.example.demo.repository.MovieRepo;
import com.example.demo.repository.OrderEntityRepo;
import com.example.demo.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {

    private final UserRepo userRepo;
    private final MovieRepo movieRepo;
    private final OrderEntityRepo orderEntityRepo;


    public ResponseEntity<String> addToCart(OrderEntity order) {

        Movie movie = movieRepo.findById(order.getMovie().getId())
                .orElseThrow(() -> new RuntimeException("Movie not found."));

        int available = movie.getCapacity()-movie.getSoldTickets();

        if(order.getQuantity() > available) {
            return new ResponseEntity<>("Not enough tickets.", HttpStatus.BAD_REQUEST);
        }

        

        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
