package com.example.demo.dto;

import lombok.Data;

@Data
public class EditProfileDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phone;
    private String address;
    private String password;
}
