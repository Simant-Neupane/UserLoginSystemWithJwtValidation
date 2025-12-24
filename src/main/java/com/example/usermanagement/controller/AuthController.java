package com.example.usermanagement.controller;

import com.example.usermanagement.dto.LoginRequest;
import com.example.usermanagement.dto.LoginResponse;
import com.example.usermanagement.dto.RegisterRequest;
import com.example.usermanagement.dto.UserRequest;
import com.example.usermanagement.model.User;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.security.JwtUtil;
import com.example.usermanagement.service.UserServiceImp;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final UserRepository repo;
    private final UserServiceImp userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request){
        if(repo.findByUserName(request.getUserName()).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Username already exists!");
        }
        if(repo.findByEmail(request.getEmail()).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Email already exists!");
        }
        UserRequest user = new UserRequest();
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreatedDate(new Date());
        user.setUpdatedDate(new Date());
        userService.createUser(user);
        return ResponseEntity.ok(Map.of("message","User registered successfully!"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        User user = repo.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid email!"));
        if(!request.getEmail().matches(user.getEmail())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid email!");
        }
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid email!");
        }
        String token = JwtUtil.generateToken(user.getUserName());
        System.out.println("User token:"+ token);
        return ResponseEntity.ok(new LoginResponse(user.getId(), token));
    }
}
