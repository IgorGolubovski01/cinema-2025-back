package com.example.demo.service;

import com.example.demo.dto.AddToCartRequest;
import com.example.demo.dto.GetAllUserOrdersDto;
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
import java.util.ArrayList;
import java.util.List;

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

    public ResponseEntity<List<OrderEntity>> getOrderItems(int userId, int statusId) {
        List<OrderEntity> list = orderEntityRepo.findByUserIdAndStatusStatusId(userId, statusId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    public ResponseEntity<List<GetAllUserOrdersDto>> getAllUserOrders(int userId) {
        List<OrderEntity> list = orderEntityRepo.findByUserId(userId)
                .stream()
                .filter(order -> order.getDisplay() == 1)
                .toList();

        List<GetAllUserOrdersDto> dtoList = new ArrayList<>();
        for (OrderEntity order : list) {
            GetAllUserOrdersDto dto = new GetAllUserOrdersDto();
            dto.setId(order.getId());
            dto.setTitle(order.getMovie().getTitle());
            dto.setFilmDateTime(order.getMovie().getFilmDateTime());
            dto.setQuantity(order.getQuantity());
            dto.setTotalPrice(order.getMovie().getPrice() * order.getQuantity());
            dto.setStatus(order.getStatus());
            dto.setDisplay(order.getDisplay());
            dtoList.add(dto);
        }

        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }


    public ResponseEntity<String> purchase(int orderId) {
        OrderEntity order = orderEntityRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found."));
        Movie movie = order.getMovie();

        int available = order.getMovie().getCapacity()-order.getMovie().getSoldTickets();

        if(available < order.getQuantity()) {
            return new ResponseEntity<>("Not enough tickets available", HttpStatus.BAD_REQUEST);
        }

        movie.setSoldTickets(movie.getSoldTickets() + order.getQuantity());
        movieRepo.save(movie);
        order.setStatus(orderStatusRepo.getReferenceById(2));
        orderEntityRepo.save(order);
        return new ResponseEntity<>("Successfully purchased order.", HttpStatus.OK);
    }

    public ResponseEntity<String> cancel(int orderId) {
        OrderEntity order = orderEntityRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found."));

        order.setStatus(orderStatusRepo.getReferenceById(4));
        orderEntityRepo.save(order);

        return new ResponseEntity<>("Order cancelled", HttpStatus.OK);
    }

    public ResponseEntity<String> watched(int orderId) {
        OrderEntity order = orderEntityRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found."));

        order.setStatus(orderStatusRepo.getReferenceById(3));
        orderEntityRepo.save(order);
        return new ResponseEntity<>("Order set to \"watched\" ", HttpStatus.OK);
    }

    public ResponseEntity<String> dontShow(int orderId) {
        OrderEntity order = orderEntityRepo.findById(orderId).orElseThrow(()
                -> new RuntimeException("Order not found."));
        order.setDisplay(0);
        orderEntityRepo.save(order);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
