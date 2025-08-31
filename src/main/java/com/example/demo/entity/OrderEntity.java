package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Movie movie;
    @ManyToOne
    private User user;
    private int quantity;
    @ManyToOne
    private OrderStatus status;
    private LocalDateTime orderDate;
    private double totalPrice;
    @Column(columnDefinition = "TINYINT(1) DEFAULT 1")
    private int display = 1;
}
