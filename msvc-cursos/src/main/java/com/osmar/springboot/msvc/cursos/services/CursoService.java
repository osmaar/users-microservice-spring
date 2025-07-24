package com.osmar.springboot.msvc.cursos.services;

import com.osmar.springboot.msvc.cursos.models.User;
import com.osmar.springboot.msvc.cursos.models.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<Curso> findAll();
    Optional<Curso> findById(Long id);
    Curso save(Curso curso);
    void deleteById(Long id);
    void deleteCursoUserById(Long id);
    Optional<Curso> findByIdUsers(Long id);

    Optional<User> assignUser(User user, Long cursoId);
    User createUser(User user, Long cursoId);
    User deleteUser(User user, Long cursoId);
}
