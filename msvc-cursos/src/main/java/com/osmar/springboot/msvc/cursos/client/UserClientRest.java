package com.osmar.springboot.msvc.cursos.client;

import com.osmar.springboot.msvc.cursos.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "msvc-usuarios", url = "localhost:8001/api/users")
public interface UserClientRest {

    @GetMapping
    User index();

    @GetMapping("/{id}")
    User details(@PathVariable Long id);

    @PostMapping
    User save(@RequestBody User user);

    @PutMapping("/{id}")
    User update(@PathVariable Long id, @RequestBody User user);

    @DeleteMapping("/{id}")
    User delete(@PathVariable Long id);

    @GetMapping("/list-users-by-curso")
    List<User> listUsersByCurso(@RequestParam Iterable<Long> ids);
}
