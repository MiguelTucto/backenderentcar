package com.upc.pe.backenderentcar.user.api;

import com.upc.pe.backenderentcar.car.resource.CarResource;
import com.upc.pe.backenderentcar.user.domain.service.UserService;
import com.upc.pe.backenderentcar.user.mapping.UserMapper;
import com.upc.pe.backenderentcar.user.resource.CreateUserResource;
import com.upc.pe.backenderentcar.user.resource.LoginUserResource;
import com.upc.pe.backenderentcar.user.resource.UpdateUserResource;
import com.upc.pe.backenderentcar.user.resource.UserResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/users")
public class UsersController {
    private final UserService userService;
    private final UserMapper mapper;

    public UsersController(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get Users", description = "You can get all users", tags = {"Users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "You can get all users",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarResource.class)
                    ))
    })
    @GetMapping
    public Page<UserResource> getAllUsers(Pageable pageable) {
        return mapper.modelListPage(userService.getAll(), pageable);
    }

    @Operation(summary = "Get User", description = "You can get an user by id", tags = {"Users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "You can get an user by id",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarResource.class)
                    ))
    })
    @GetMapping("{userId}")
    public UserResource getUserById (@PathVariable Long userId) {
        return mapper.toResource(userService.getById(userId));
    }

    @Operation(summary = "Create User", description = "You can create an user", tags = {"Users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "You can create an user",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarResource.class)
                    ))
    })
    @PostMapping
    public UserResource createUser(@RequestBody CreateUserResource resource) {
        return mapper.toResource(userService.create(mapper.toModel(resource)));
    }

    @Operation(summary = "Update User", description = "You can update an user by id", tags = {"Users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "You can update an user by id",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarResource.class)
                    ))
    })
    @PutMapping("{userId}")
    public UserResource updateUser(@PathVariable Long userId, @RequestBody UpdateUserResource resource) {
        return mapper.toResource(userService.update(userId, mapper.toModel(resource)));
    }

    @Operation(summary = "Delete User", description = "You can delete an user by id", tags = {"Users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "You can delete an user by id",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarResource.class)
                    ))
    })
    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) { return userService.delete(userId); }

    @Operation(summary = "Logging User", description = "You can logging with an email and password", tags = {"Users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "You can logging with an email and password",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarResource.class)
                    ))
    })
    @PostMapping("/login")
    public UserResource login(@RequestBody LoginUserResource resource) {
        return mapper.toResource(userService.login(resource.getEmail(), resource.getPassword()));
    }
}
