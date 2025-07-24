package com.osmar.springboot.di.app.msvcusuarios.repositories;

import com.osmar.springboot.di.app.msvcusuarios.models.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository <User, Long> {

    Optional<User> findByEmail(String email);

}
