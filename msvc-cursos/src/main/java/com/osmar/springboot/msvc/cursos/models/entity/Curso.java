package com.osmar.springboot.msvc.cursos.models.entity;

import com.osmar.springboot.msvc.cursos.models.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "curso_id", nullable = false)
    private List<CursoUser> cursosUsers;

    @Transient
    private List<User> users;

    /**
     * Constructor por defecto
     */
    public Curso() {
        cursosUsers = new ArrayList<>();
        users = new ArrayList<>();
    }

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

    /**
     * get field @Transient
     *
     * @return users @Transient

     */
    public List<User> getUsers() {
        return this.users;
    }

    /**
     * set field @Transient
     *
     * @param users @Transient

     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * get field @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
     *
     * @return cursosUsers @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)

     */
    public List<CursoUser> getCursosUsers() {
        return this.cursosUsers;
    }

    /**
     * set field @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
     *
     * @param cursosUsers @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)

     */
    public void setCursosUsers(List<CursoUser> cursosUsers) {
        this.cursosUsers = cursosUsers;
    }

    /**
     * Adds a CursoUser to the list of CursoUsers.
     *
     * @param cursoUser the CursoUser to add
     */
    public void addCursoUser(CursoUser cursoUser) {
        this.cursosUsers.add(cursoUser);
    }

    /**
     * Removes a CursoUser from the list of CursoUsers.
     *
     * @param cursoUser the CursoUser to remove
     */
    public void removeCursoUser(CursoUser cursoUser) {
        this.cursosUsers.remove(cursoUser);
    }


}
