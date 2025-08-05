package com.example.demo.dto;

import com.example.demo.entity.Role;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class SignUpRequest {
    private Integer id;

    private String firstName;
    private String lastName;

    private String email;
    private String phone;
    private String address;

    private String username;
    private String password;

    @ManyToOne
    private Role role;
}
