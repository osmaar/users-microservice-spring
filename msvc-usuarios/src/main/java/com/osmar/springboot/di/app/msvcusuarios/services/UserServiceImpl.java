package com.osmar.springboot.di.app.msvcusuarios.services;

import com.osmar.springboot.di.app.msvcusuarios.models.entity.User;
import com.osmar.springboot.di.app.msvcusuarios.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    /**
     * Repository for managing user entities.
     */
    private final UserRepository userRepository;

    /**
     * Constructor for UserServiceImpl.
     *
     * @param userRepository the repository to manage user entities
     */
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    /**
     * Finds a user by its ID.
     *
     * @param id the ID of the user to find
     * @return an Optional containing the found user, or empty if not found
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Saves a user entity.
     *
     * @param user the user entity to save
     * @return the saved user entity
     */
    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * Deletes a user by its ID.
     *
     * @param id the ID of the user to delete
     */
    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
