package com.example.demo.service;

import com.example.demo.dto.AddToCartRequest;
import com.example.demo.entity.Movie;
import com.example.demo.entity.OrderEntity;
import com.example.demo.entity.User;
import com.example.demo.repository.MovieRepo;
import com.example.demo.repository.OrderEntityRepo;
import com.example.demo.repository.OrderStatusRepo;
import com.example.demo.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OrderService {

    private final MovieRepo movieRepo;
    private final OrderEntityRepo orderEntityRepo;
    private final UserRepo userRepo;
    private final OrderStatusRepo orderStatusRepo;

    public ResponseEntity<String> addToCart(AddToCartRequest request) {

        Movie movie = movieRepo.findById(request.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found."));

        int available = movie.getCapacity()-movie.getSoldTickets();

        if(request.getQuantity() > available) {
            return new ResponseEntity<>("Not enough tickets available", HttpStatus.BAD_REQUEST);
        }

        User user = userRepo.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found."));

        OrderEntity order = new OrderEntity();
        order.setUser(user);
        order.setMovie(movie);
        order.setQuantity(request.getQuantity());
        order.setStatus(orderStatusRepo.getReferenceById(1));
        order.setOrderDate(LocalDateTime.now());
        order.setTotalPrice(request.getQuantity()*movie.getPrice());
        orderEntityRepo.save(order);

        return new ResponseEntity<>("Successfully added to cart.", HttpStatus.OK);
    }
}
