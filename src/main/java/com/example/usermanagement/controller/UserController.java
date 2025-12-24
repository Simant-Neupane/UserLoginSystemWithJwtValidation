package com.example.usermanagement.controller;

import com.example.usermanagement.dto.UserRequest;
import com.example.usermanagement.dto.UserResponse;
import com.example.usermanagement.service.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserServiceImp userService;

    @GetMapping("/users")
    public List<UserResponse> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/users/getById={id}")
    public UserResponse getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

//    @PostMapping("/users/create-user")
//    public UserResponse createUser(@RequestBody UserRequest requestDto){
//        return userService.createUser(requestDto);
//    }

    @PutMapping("/users/updateById={id}")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UserRequest requestDto){
        return userService.updateUser(id, requestDto);
    }

    @DeleteMapping("/users/deleteById={id}")
    public void deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
    }
}
