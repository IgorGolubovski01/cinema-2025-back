package com.example.demo.dto;

import com.example.demo.entity.OrderStatus;
import lombok.Data;

@Data
public class GetAllUserOrdersDto {
    private int id;
    private String title;
    private String filmDateTime;
    private int quantity;
    private double totalPrice;
    private OrderStatus status;
    private int display;




//    title: string,
//    filmDateTime: string,
//    quantity: string,
//    totalPrice: number
}
