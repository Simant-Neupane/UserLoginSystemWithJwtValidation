package com.example.usermanagement.service;

import com.example.usermanagement.model.User;
import com.example.usermanagement.dto.UserMapper;
import com.example.usermanagement.dto.UserRequest;
import com.example.usermanagement.dto.UserResponse;
import com.example.usermanagement.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService{

    private final UserRepository repo;
    private final UserMapper mapper;
    public UserServiceImp(UserRepository repo, UserMapper mapper){
        this.repo = repo;
        this.mapper = mapper;
    }
    public List<UserResponse> getAllUser(){
        List<User> user = repo.findAll();
        return user.stream()
                .map(mapper::toResponseDto)
                .toList();
    }
    public UserResponse getUserById(Long id){
        User user = repo.findById(id).orElse(null);
        return user!=null ? mapper.toResponseDto(user): null;
    }

    public UserResponse createUser(UserRequest requestDto){

        if(requestDto.getUserName() == null || requestDto.getUserName().trim().isEmpty()){
            throw new IllegalArgumentException("Username required!");
        }
        if(!requestDto.getEmail().contains("@") || !requestDto.getEmail().contains(".com")){
            throw new IllegalArgumentException("Valid Email required!");
        }
        if(requestDto.getPassword().length() < 6){
            throw new IllegalArgumentException("Strong password needed!");
        }
        User user = mapper.toEntity(requestDto);
        User saved = repo.save(user);

        return mapper.toResponseDto(saved);
    }
    public UserResponse updateUser(Long id, UserRequest requestDto){
        User existingUser = repo.findById(id).orElse(null);
        if(existingUser != null){
            existingUser.setUserName(requestDto.getUserName());
            existingUser.setEmail(requestDto.getEmail());
            existingUser.setPassword(requestDto.getPassword());
            existingUser.setUpdatedDate(requestDto.getUpdatedDate());
            User update = repo.save(existingUser);
            return mapper.toResponseDto(update);
        }else {
            return null;
        }
    }

    public void deleteUserById(Long id){
        User user = repo.findById(id)
                .orElseThrow(()->new RuntimeException("The user id ("+id+") not found."));
        repo.delete(user);
    }
}
