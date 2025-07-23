package com.osmar.springboot.msvc.cursos.services;

import com.osmar.springboot.msvc.cursos.models.entity.Curso;
import com.osmar.springboot.msvc.cursos.repositories.CursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService  {

    /** * Repository for managing course entities.
     */
    private final CursoRepository cursoRepository;

    /**
     * Constructor for CursoServiceImpl.
     *
     * @param cursoRepository the repository to be used for course operations
     */
    public CursoServiceImpl(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
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


}
