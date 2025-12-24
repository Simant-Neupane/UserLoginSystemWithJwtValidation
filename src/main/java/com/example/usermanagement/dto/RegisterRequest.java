package com.example.usermanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;


@Data
public class RegisterRequest {
    @NotBlank(message = "Username Required!")
    private String userName;
    @NotBlank(message = "Email Required!")
    @Email(message = "Valid email required!")
    private String email;
    @NotBlank(message = "Username Required!")
    @Size(min = 6, message = "Password too short!")
    private String password;
}
