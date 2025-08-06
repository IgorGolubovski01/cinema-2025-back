package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.SignUpRequest;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepo;
import com.example.demo.repository.UserRepo;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final AuthenticationManager authMng;

    public ResponseEntity<String> signUp(SignUpRequest request) {

        if(userRepo.existsByEmail(request.getEmail()))
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        if(userRepo.existsByUsername(request.getUsername()))
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);

        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());


        user.setRole(roleRepo.findById(2)
                .orElseThrow(() -> new RuntimeException("Role not found")));

        userRepo.save(user);

        return new ResponseEntity<>("Sign up successful", HttpStatus.CREATED);
    }

    public ResponseEntity<UserDto> login(LoginRequest request) {
        Authentication auth = authMng.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );



        if(auth.isAuthenticated()) {
            User user = userRepo.findUserByUsername(request.getUsername());

            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setUsername(user.getUsername());

            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
