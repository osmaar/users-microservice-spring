package com.osmar.springboot.di.app.msvcusuarios.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Usuario no encontrado con ID: " + id);
    }
}
