package com.osmar.springboot.msvc.cursos.services;

import com.osmar.springboot.msvc.cursos.client.UserClientRest;
import com.osmar.springboot.msvc.cursos.exceptions.CursoNotFoundException;
import com.osmar.springboot.msvc.cursos.exceptions.UserNotFoundException;
import com.osmar.springboot.msvc.cursos.models.User;
import com.osmar.springboot.msvc.cursos.models.entity.Curso;
import com.osmar.springboot.msvc.cursos.models.entity.CursoUser;
import com.osmar.springboot.msvc.cursos.repositories.CursoRepository;
import feign.FeignException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService  {

    /** * Repository for managing course entities.
     */
    private final CursoRepository cursoRepository;

    private final UserClientRest userClientRest;

    /**
     * Constructor for CursoServiceImpl.
     *
     * @param cursoRepository the repository to be used for course operations
     */
    public CursoServiceImpl(CursoRepository cursoRepository, UserClientRest userClientRest) {
        this.cursoRepository = cursoRepository;
        this.userClientRest = userClientRest;
    }

    /**
     * Retrieves all courses from the repository.
     *
     * @return a list of all courses
     */
    @Override
    @Transactional(readOnly = true)
    public List<Curso> findAll() {
        return (List<Curso>) cursoRepository.findAll();
    }

    /**
     * Finds a course by its ID.
     *
     * @param id the ID of the course
     * @return an Optional containing the course if found, or empty if not found
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> findById(Long id) {
        return cursoRepository.findById(id);
    }

    /**
     * Saves a course to the repository.
     *
     * @param curso the course to save
     * @return the saved course
     */
    @Override
    @Transactional
    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }

    /**
     * Deletes a course by its ID.
     *
     * @param id the ID of the course to delete
     */
    @Override
    @Transactional
    public void deleteById(Long id) {
        cursoRepository.deleteById(id);
    }

    /**
     * Deletes a course user by its ID.
     *
     * @param id the ID of the course user to delete
     */
    @Override
    @Transactional
    public void deleteCursoUserById(Long id) {
        cursoRepository.deleteCursoUserById(id);
    }

    /**
     * Finds a course by its ID and retrieves the associated users.
     *
     * @param id the ID of the course
     * @return an Optional containing the course with users if found, or empty if not found
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> findByIdUsers(Long id) {
        Optional<Curso> o = cursoRepository.findById(id);
        if (o.isPresent()) {
            Curso curso = o.get();

            if (!curso.getCursosUsers().isEmpty()) {
                List<Long> ids = curso.getCursosUsers()
                        .stream()
                        .map(CursoUser::getUserId)
                        .toList();

                List<User> users = userClientRest.listUsersByCurso(ids);
                curso.setUsers(users);

            }
                return Optional.of(curso);
        }
        return Optional.empty();
    }

    /**
     * Assigns a user to a course.
     *
     * @param user the user to assign
     * @param cursoId the ID of the course to assign the user to
     * @return an Optional containing the assigned user if successful, or empty if not found
     */
    @Override
    @Transactional
    public Optional<User> assignUser(User user, Long cursoId) {
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + cursoId));

        User newUser;
        try {
            newUser = userClientRest.details(user.getId());
        } catch (FeignException e) {
            throw new UserNotFoundException(user.getId());
        }

        CursoUser cursoUser = new CursoUser();
        cursoUser.setUserId(newUser.getId());
        curso.addCursoUser(cursoUser);
        cursoRepository.save(curso);

        return Optional.of(newUser);
    }

    /**
     * Creates a new user and assigns them to a course.
     *
     * @param user the user to create
     * @param cursoId the ID of the course to assign the user to
     * @return the created user
     */
    @Override
    @Transactional
    public User createUser(User user, Long cursoId) {
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + cursoId));

        User newUser = userClientRest.save(user);

        CursoUser cursoUser = new CursoUser();
        cursoUser.setUserId(newUser.getId());

        curso.addCursoUser(cursoUser);
        cursoRepository.save(curso);

        return newUser;
    }

    /**
     * Deletes a user from a course.
     *
     * @param user the user to delete
     * @param cursoId the ID of the course
     * @return an Optional containing the deleted user if successful, or empty if not found
     */
    @Override
    @Transactional
    public User deleteUser(User user, Long cursoId) {
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new CursoNotFoundException(cursoId));

        User existingUser = userClientRest.details(user.getId());

        CursoUser cursoUser = new CursoUser();
        cursoUser.setUserId(existingUser.getId());

        curso.removeCursoUser(cursoUser);
        cursoRepository.save(curso);

        return existingUser;
    }

}
