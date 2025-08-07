package com.example.demo.dto;

import lombok.Data;

@Data
public class AddToCartRequest {
    private int movieId;
    private int userId;
    private int quantity;
}
