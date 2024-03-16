package com.techtest.scrumretroapi.controller;

import com.techtest.scrumretroapi.entity.User;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@OpenAPIDefinition(
        info = @Info(
                title = "User Management API",
                description = "This template API project provides basic endpoints for reading, creating, editing and deleting user data."
        )
)
@RequestMapping("/user")
@Tag(name = "User API")
public class UserController {

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returned all available user data",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Failed to fetch users from server", content = @Content)
    })
    @GetMapping("/")
    public Optional<List<User>> getAllUsers() {
        return Optional.empty();
    }

    @Operation(summary = "Get a user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Failed to fetch user from server", content = @Content)
    })
    @GetMapping("/{id}")
    public Optional<User> getUserById(@Parameter(description = "Reference of the customer to be searched")
                                      @PathVariable Long id) {
        return Optional.empty();
    }

    @Operation(summary = "Create a new user",
            description = "Endpoint to create a new user in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully created",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request body",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Failed to create user in database",
                    content = @Content)
    })
    @PostMapping("/")
    public void createNewUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Customer object to be created.",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = User.class)
                    ))
            @RequestBody final User user) {
        // create new user via the service/repo
    }


    // TODO: verify that this is how you actually update a user through PUT
    @Operation(summary = "Update existing user",
            description = "Endpoint to update an existing user in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request body",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Failed to update user in database",
                    content = @Content)
    })
    @PutMapping("/")
    public void updateUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Customer object to be updated.",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = User.class)
                    ))
            @RequestBody final User user) {
        // update user via the service/repo (do so immutably!)
    }

    @Operation(summary = "Delete user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted the user",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Failed to fetch user from server", content = @Content)
    })
    @DeleteMapping("/{id}")
    public void deleteUserById(@Parameter(description = "Reference of the customer to be searched")
                               @PathVariable Long id) {
    }
}
