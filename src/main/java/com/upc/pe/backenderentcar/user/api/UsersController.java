package com.upc.pe.backenderentcar.user.api;

import com.upc.pe.backenderentcar.user.domain.service.UserService;
import com.upc.pe.backenderentcar.user.mapping.UserMapper;
import com.upc.pe.backenderentcar.user.resource.CreateUserResource;
import com.upc.pe.backenderentcar.user.resource.UpdateUserResource;
import com.upc.pe.backenderentcar.user.resource.UserResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User")
@RestController
@CrossOrigin
@RequestMapping("api/v1/users")
public class UsersController {
    private final UserService userService;
    private final UserMapper mapper;

    public UsersController(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<UserResource> getAllUsers(Pageable pageable) {
        return mapper.modelListPage(userService.getAll(), pageable);
    }

    @GetMapping("{userId}")
    public UserResource getUserById (@PathVariable Long userId) {
        return mapper.toResource(userService.getById(userId));
    }

    @PostMapping
    public UserResource createUser(@RequestBody CreateUserResource resource) {
        return mapper.toResource(userService.create(mapper.toModel(resource)));
    }

    @PutMapping("{userId}")
    public UserResource updateUser(@PathVariable Long userId, @RequestBody UpdateUserResource resource) {
        return mapper.toResource(userService.update(userId, mapper.toModel(resource)));
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) { return userService.delete(userId); }
}
