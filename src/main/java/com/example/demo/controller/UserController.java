package com.example.demo.controller;

import com.example.demo.dto.SignUpRequest;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    @PostMapping("signUp")
    public ResponseEntity<String> signUp(SignUpRequest request){
        return userService.signUp(request);
    }
}
