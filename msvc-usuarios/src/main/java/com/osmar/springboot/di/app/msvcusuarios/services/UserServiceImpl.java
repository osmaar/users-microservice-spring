package com.osmar.springboot.di.app.msvcusuarios.services;

import com.osmar.springboot.di.app.msvcusuarios.clients.CursoClientRest;
import com.osmar.springboot.di.app.msvcusuarios.exceptions.EmailAlreadyExistsException;
import com.osmar.springboot.di.app.msvcusuarios.exceptions.UserNotFoundException;
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
    private final CursoClientRest cursoClientRest;

    /**
     * Constructor for UserServiceImpl.
     *
     * @param userRepository the repository to manage user entities
     */
    public UserServiceImpl(UserRepository userRepository, CursoClientRest cursoClientRest) {
        this.userRepository = userRepository;
        this.cursoClientRest = cursoClientRest;
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
        return Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id)));
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
        userRepository.findByEmail(user.getEmail()).ifPresent(
                existingUser -> {
                    throw new EmailAlreadyExistsException(user.getEmail());
                }
        );

        return userRepository.save(user);
    }


    @Override
    @Transactional
    public User update(Long id, User user){
        return userRepository.findById(id).map(
                existingUser -> {
                    userRepository.findByEmail(user.getEmail()).ifPresent(
                            userWithEmail -> {
                                if (!userWithEmail.getId().equals(id)) {
                                    throw new EmailAlreadyExistsException(user.getEmail());
                                }
                            }
                    );
                    existingUser.setUsername(user.getUsername());
                    existingUser.setEmail(user.getEmail());
                    existingUser.setName(user.getName());
                    existingUser.setLastName(user.getLastName());

                    return userRepository.save(existingUser);
                }
        ).orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * Deletes a user by its ID.
     *
     * @param id the ID of the user to delete
     */
    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        cursoClientRest.deleteCursoUserById(id);
    }

    /**
     * Finds a user by its email.
     *
     * @param email the email of the user to find
     * @return an Optional containing the found user, or empty if not found
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Lists users by their IDs.
     *
     * @param ids the IDs of the users to list
     * @return a list of users with the specified IDs
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> listByIds(Iterable<Long> ids) {
        return (List<User>) userRepository.findAllById(ids);
    }
}
