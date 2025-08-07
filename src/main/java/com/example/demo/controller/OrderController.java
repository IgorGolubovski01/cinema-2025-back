package com.example.demo.controller;

import com.example.demo.dto.AddToCartRequest;
import com.example.demo.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("order")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("addToCart")
    public ResponseEntity<String> addToCart(@RequestBody AddToCartRequest request) {
        return orderService.addToCart(request);
    }
}
