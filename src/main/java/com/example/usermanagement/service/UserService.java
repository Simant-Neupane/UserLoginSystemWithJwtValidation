package com.example.usermanagement.service;

import com.example.usermanagement.dto.UserRequest;
import com.example.usermanagement.dto.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUser();
    UserResponse getUserById(Long id);
    UserResponse createUser(UserRequest requestDto);
    UserResponse updateUser(Long id, UserRequest requestDto);
    void deleteUserById(Long id);
}
