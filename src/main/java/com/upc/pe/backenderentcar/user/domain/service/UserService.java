package com.upc.pe.backenderentcar.user.domain.service;

import com.upc.pe.backenderentcar.user.domain.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    List<User> getAll();
    Page<User> getAll(Pageable pageable);
    User getById(Long userId);
    User create(User user);
    User update(Long userId, User request);

    User login(String email, String password);
    ResponseEntity<?> delete(Long userId);
}
