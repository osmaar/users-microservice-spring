package com.osmar.springboot.di.app.msvcusuarios.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String email) {
        super("El usuario con el correo '" + email + "' ya existe.");
    }
}
