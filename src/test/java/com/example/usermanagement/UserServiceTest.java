package com.example.usermanagement;

import com.example.usermanagement.dto.UserResponse;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.service.UserServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    UserRepository repo;

    @InjectMocks
    UserServiceImp service;

    @Test
    void getUser_whenFound_returnUser(){
        // Act
        UserResponse fake = new UserResponse(null, "simant", "simant@gmail.com");
        when(repo.findById(1L)).thenReturn(Optional.of(fake));

        // Arrange
        UserResponse result = service.getUserById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("simant", result.getUserName());
        verify(repo, times(1)).findById(1L);

    }
    @Test
    void getUser_whenNotFound_throw(){
        // Act
        when(repo.findById(2L)).thenReturn(Optional.empty());

        // Arrange & Assert
        assertThrows(NoSuchElementException.class, ()-> service.getUserById(2L));
        verify(repo, times(1)).findById(2L);
    }


}
