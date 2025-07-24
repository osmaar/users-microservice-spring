package com.osmar.springboot.di.app.msvcusuarios.controllers;

import com.osmar.springboot.di.app.msvcusuarios.models.entity.User;
import com.osmar.springboot.di.app.msvcusuarios.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Optional<User>> details(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    /**
     * Saves a user entity.
     *
     * @param user the user entity to save
     * @return the saved user entity
     */
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    /**
     * Updates a user by its ID.
     *
     * @param id   the ID of the user to update
     * @param user the user entity with updated information
     * @return the updated user entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@Valid @PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(userService.update(id, user));
    }

    /**
     * Deletes a user by its ID.
     *
     * @param id the ID of the user to delete
     * @return a 204 No Content response if the user was deleted, or a 404 Not Found if the user does not exist
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes a user from a course by its ID.
     *
     * @param id the ID of the user to delete from the course
     * @return a 204 No Content response if the user was deleted, or a 404 Not Found if the user does not exist
     */
    @DeleteMapping("/delete-user-of-curso/{id}")
    public ResponseEntity<Void> deleteUserFromCurso(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Lists users by their IDs.
     *
     * @param ids the list of user IDs to retrieve
     * @return a list of users with the specified IDs
     */
    @GetMapping("/list-users-by-curso")
    public ResponseEntity<?> list(@RequestParam List<Long> ids ) {
        return ResponseEntity.ok(userService.listByIds(ids));
    }
}
