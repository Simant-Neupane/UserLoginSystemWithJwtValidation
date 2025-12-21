package com.example.usermanagement.dto;

import com.fasterxml.jackson.databind.DatabindException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequest {
    private Long id;
    private String userName;
    private String email;
    private String password;
    private Date createdDate;
    private Date updatedDate;
}
