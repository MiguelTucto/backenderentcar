package com.upc.pe.backenderentcar.user.service;

import com.upc.pe.backenderentcar.shared.exception.ResourceNotFoundException;
import com.upc.pe.backenderentcar.shared.exception.ResourceValidationException;
import com.upc.pe.backenderentcar.user.domain.model.entity.User;
import com.upc.pe.backenderentcar.user.domain.persistence.UserRepository;
import com.upc.pe.backenderentcar.user.domain.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private static final String ENTITY = "User";
    private final UserRepository userRepository;
    private final Validator validator;

    public UserServiceImpl(UserRepository userRepository, Validator validator) {
        this.userRepository = userRepository;
        this.validator = validator;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }

    @Override
    public User create(User user) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return userRepository.save(user);
    }

    @Override
    public User update(Long userId, User request) {
        Set<ConstraintViolation<User>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        User userWithName = userRepository.findByName(request.getName());


        return userRepository.findById(userId).map(user ->
                userRepository.save(user
                        .withName(request.getName())
                        .withLastName(request.getLastName())
                        .withEmail(request.getEmail())
                        .withPassword(request.getPassword())
                        .withImageUrl(request.getImageUrl())
                        .withPhone(request.getPhone())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }

    @Override
    public User login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public ResponseEntity<?> delete(Long userId) {
        return userRepository.findById(userId).map(user -> {
                    userRepository.delete(user);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }
}
