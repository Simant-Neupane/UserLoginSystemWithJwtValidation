package com.example.usermanagement.dto;

import com.example.usermanagement.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse toResponseDto(User user){
        UserResponse responseDto = new UserResponse();
        responseDto.setId(user.getId());
        responseDto.setUserName(user.getUserName());
        responseDto.setEmail(user.getEmail());
        return responseDto;
    }
    public UserRequest toRequestDto(User user){
        UserRequest requestDto = new UserRequest();
        user.setId(requestDto.getId());
        user.setUserName(requestDto.getUserName());
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());
        user.setCreatedDate(requestDto.getCreatedDate());
        user.setUpdatedDate(requestDto.getUpdatedDate());
        return requestDto;
    }
    public User toEntity(UserRequest requestDto){
        User user = new User();
        user.setId(requestDto.getId());
        user.setUserName(requestDto.getUserName());
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());
        user.setCreatedDate(requestDto.getCreatedDate());
        user.setUpdatedDate(requestDto.getUpdatedDate());
        return user;
    }
    public UserProfile toUserProfileDto(User user){
        UserProfile userProfile = new UserProfile();
        userProfile.setId(user.getId());
        userProfile.setUserName(user.getUserName());
        userProfile.setEmail(user.getEmail());
        userProfile.setCreatedDate(user.getCreatedDate());
        userProfile.setUpdatedDate(user.getUpdatedDate());
        return userProfile;
    }

}
