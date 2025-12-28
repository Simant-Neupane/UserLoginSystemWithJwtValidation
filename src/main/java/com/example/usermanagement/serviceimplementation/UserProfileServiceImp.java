package com.example.usermanagement.serviceimplementation;

import com.example.usermanagement.dto.UserMapper;
import com.example.usermanagement.dto.UserProfile;
import com.example.usermanagement.model.User;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.service.UserProfileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserProfileServiceImp implements UserProfileService {
    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Override
    public UserProfile getUserProfile(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
        return mapper.toUserProfileDto(user);

    }
}
