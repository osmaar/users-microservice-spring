package com.osmar.springboot.di.app.msvcusuarios.controllers;

import com.osmar.springboot.di.app.msvcusuarios.models.entity.User;
import com.osmar.springboot.di.app.msvcusuarios.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    /**
     * Service for managing user entities.
     */
    private final UserService userService;

    /**
     * Constructor for UserController.
     *
     * @param userService the service to manage user entities
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves all users.
     *
     * @return a list of all users
     */
    @GetMapping
    public List<User> index() {
        return userService.findAll();
    }

    /**
     * Retrieves a user by its ID.
     *
     * @param id the ID of the user to retrieve
     * @return the user with the specified ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> details(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Saves a user entity.
     *
     * @param user the user entity to save
     * @return the saved user entity
     */
    @PostMapping
    public ResponseEntity<?> save(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    /**
     * Updates a user by its ID.
     *
     * @param id   the ID of the user to update
     * @param user the user entity with updated information
     * @return the updated user entity, or a 404 Not Found if the user does not exist
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        return userService.findById(id)
                .map(existingUser -> {
                    existingUser.setUsername(user.getUsername());
                    existingUser.setEmail(user.getEmail());
                    existingUser.setName(user.getName());
                    existingUser.setLastName(user.getLastName());

                    User updatedUser = userService.save(existingUser);
                    return ResponseEntity.ok(updatedUser);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Deletes a user by its ID.
     *
     * @param id the ID of the user to delete
     * @return a 204 No Content response if the user was deleted, or a 404 Not Found if the user does not exist
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return userService.findById(id)
                .map(user -> {
                    userService.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
