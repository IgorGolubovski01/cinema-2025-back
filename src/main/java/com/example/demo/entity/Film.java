package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    @ManyToOne
    private Genre genre;


    private String duration;
    private String releaseDate;
    private String filmDateTime;


    private String director;
    @ManyToMany
    private List<Actor> actors;


    private double price;

}
