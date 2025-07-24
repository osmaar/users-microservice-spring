package com.osmar.springboot.msvc.cursos.repositories;

import com.osmar.springboot.msvc.cursos.models.entity.Curso;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepository extends CrudRepository<Curso, Long> {

    @Modifying
    @Query("DELETE FROM CursoUser cu WHERE cu.userId=?1")
    void deleteCursoUserById(Long id);
}
