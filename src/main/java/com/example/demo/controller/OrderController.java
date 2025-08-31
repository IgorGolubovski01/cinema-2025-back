package com.example.demo.controller;

import com.example.demo.dto.AddToCartRequest;
import com.example.demo.dto.GetAllUserOrdersDto;
import com.example.demo.entity.OrderEntity;
import com.example.demo.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("getOrderItems")
    public ResponseEntity<List<OrderEntity>> getOrderItems(@RequestParam int userId, @RequestParam int statusId) {
        return orderService.getOrderItems(userId, statusId);
    }

    @GetMapping("getAllUserOrders")
    public ResponseEntity<List<GetAllUserOrdersDto>> getAllUserOrders(@RequestParam int userId) {
        return orderService.getAllUserOrders(userId);
    }

    @PostMapping("purchase")
    public ResponseEntity<String> purchase(@RequestParam int orderId){
        return orderService.purchase(orderId);
    }

    @PostMapping("cancel")
    public ResponseEntity<String> cancel(@RequestParam int orderId){
        return orderService.cancel(orderId);
    }

    @PostMapping("watched")
    public ResponseEntity<String> watched(@RequestParam int orderId){
        return orderService.watched(orderId);
    }

    @PostMapping("dontShow")
    public ResponseEntity<String> dontShow(@RequestParam int orderId){
        return orderService.dontShow(orderId);
    }

}
