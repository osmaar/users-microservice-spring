package com.osmar.springboot.msvc.cursos.controllers;

import com.osmar.springboot.msvc.cursos.models.entity.Curso;
import com.osmar.springboot.msvc.cursos.services.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    /**
     * Service for managing course entities.
     */
    private final CursoService cursoService;

    /**
     * Constructor for CursoController.
     *
     * @param cursoService the service to manage course entities
     */
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    /**
     * Retrieves all courses.
     *
     * @return a list of all courses
     */
    @RequestMapping
    public ResponseEntity<List<Curso>> index() {
        return ResponseEntity.ok(cursoService.findAll());
    }


    /**
     * Retrieves a course by its ID.
     *
     * @param id the ID of the course to retrieve
     * @return the course with the specified ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> details(@PathVariable Long id) {
        return cursoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Saves a course entity.
     *
     * @param curso the course entity to save
     * @return the saved course entity
     */
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Curso curso) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.save(curso));
    }

    /**
     * Updates a course by its ID.
     *
     * @param id the ID of the course to update
     * @param curso the updated course entity
     * @return the updated course entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<Curso> update(@PathVariable Long id, @RequestBody Curso curso) {
        return cursoService.findById(id)
                .map(existingCurso -> {
                    existingCurso.setNombre(curso.getNombre());
                    return ResponseEntity.ok(cursoService.save(existingCurso));
                })
                .orElse(ResponseEntity.notFound().build());
    }


    /**
     * Deletes a course by its ID.
     *
     * @param id the ID of the course to delete
     * @return a response indicating the result of the deletion
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return cursoService.findById(id)
                .map(curso -> {
                    cursoService.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
