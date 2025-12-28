package com.example.usermanagement.controller;

import com.example.usermanagement.dto.UserProfile;
import com.example.usermanagement.service.UserProfileService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
@AllArgsConstructor
public class ProfileController {
    private final UserProfileService profileService;

    @GetMapping("/user")
    public UserProfile getUserProfile(@AuthenticationPrincipal CustomUserDetails userDetails){
        return profileService.getUserProfile(userDetails.getId());

    }
}
