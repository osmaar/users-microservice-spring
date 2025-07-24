package com.osmar.springboot.msvc.cursos.exceptions;

public class CursoNotFoundException extends RuntimeException {
  public CursoNotFoundException(Long id) {
    super("Curso no encontrado con ID: " + id);
  }
}
