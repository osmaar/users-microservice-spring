package com.osmar.springboot.di.app.msvcusuarios.repositories;

import com.osmar.springboot.di.app.msvcusuarios.models.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository <User, Long> {


}
