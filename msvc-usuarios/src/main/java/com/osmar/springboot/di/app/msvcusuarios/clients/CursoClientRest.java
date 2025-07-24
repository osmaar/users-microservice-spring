package com.osmar.springboot.di.app.msvcusuarios.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-cursos", url = "localhost:8002/api/cursos")
public interface CursoClientRest {

    @DeleteMapping("/delete-user-of-curso/{id}")
    Void deleteCursoUserById(@PathVariable Long id);
}
