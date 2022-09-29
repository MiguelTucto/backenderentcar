package com.upc.pe.backenderentcar.user.service;

import com.upc.pe.backenderentcar.user.domain.model.entity.User;
import com.upc.pe.backenderentcar.user.domain.persistence.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.event.InputEvent;
import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;
    private User user;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setEmail("dan");
        user.setImageUrl("http://user.image.js");
        user.setLastName("delacruz");
        user.setName("daniel");
        user.setPassword("daniel15");
        user.setPhone(946401);

    }

    @Test
    void getById() {
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(user));
        assertNotNull(userRepository.findById(1L));
    }

    @Test
    void create() {
        when(userRepository.save(user)).thenReturn(user);
        assertNotNull(userRepository.findAll());
    }


    @Test
    void getAll() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));
        assertNotNull(userRepository.findAll());
    }
}