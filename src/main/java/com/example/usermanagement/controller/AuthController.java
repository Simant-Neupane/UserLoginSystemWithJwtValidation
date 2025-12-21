package com.example.usermanagement.controller;

import com.example.usermanagement.dto.LoginRequest;
import com.example.usermanagement.dto.RegisterRequest;
import com.example.usermanagement.dto.UserRequest;
import com.example.usermanagement.model.User;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.security.JwtUtil;
import com.example.usermanagement.service.UserServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final UserRepository repo;
    private final UserServiceImp userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
        if(repo.findByUserName(request.getUserName()).isPresent()){
            throw new IllegalStateException("Username already exists!");
        }
        if(repo.findByEmail(request.getEmail()).isPresent()){
            throw new IllegalStateException("Email already exists!");
        }
        UserRequest user = new UserRequest();
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreatedDate(new Date());
        user.setUpdatedDate(new Date());
        userService.createUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        User user = repo.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Invalid email!"));
        if(!request.getEmail().matches(user.getEmail())){
            throw new BadCredentialsException("Invalid email!");
        }
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new BadCredentialsException("Invalid password!");
        }
        String token = JwtUtil.generateToken(user.getUserName());
        System.out.println("User token:"+ token);
        return ResponseEntity.ok("Congratulation! You are successfully logged in.");
    }
}
