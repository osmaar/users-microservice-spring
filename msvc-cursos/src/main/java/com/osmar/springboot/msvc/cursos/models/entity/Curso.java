package com.osmar.springboot.msvc.cursos.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    /**
     * get field @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)

      *
      * @return id @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)

     */
    public Long getId() {
        return this.id;
    }

    /**
     * set field @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)

      *
      * @param id @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)

     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * get field
     *
     * @return nombre
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * set field
     *
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
