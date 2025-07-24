package com.osmar.springboot.di.app.msvcusuarios.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Column(unique = true, nullable = false)
    @NotBlank
    @Email
    private String email;
    private String name;
    private String lastName;

    /**
     * get field @Id
     @GeneratedValue(strategy = GenerationType. IDENTITY)

      *
      * @return id @Id
     @GeneratedValue(strategy = GenerationType. IDENTITY)

     */
    public Long getId() {
        return this.id;
    }

    /**
     * set field @Id
     @GeneratedValue(strategy = GenerationType. IDENTITY)

      *
      * @param id @Id
     @GeneratedValue(strategy = GenerationType. IDENTITY)

     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * get field
     *
     * @return username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * set field
     *
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * get field
     *
     * @return password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * set field
     *
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * get field @Column(unique = true, nullable = false)
     *
     * @return email @Column(unique = true, nullable = false)

     */
    public String getEmail() {
        return this.email;
    }

    /**
     * set field @Column(unique = true, nullable = false)
     *
     * @param email @Column(unique = true, nullable = false)

     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * get field
     *
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * set field
     *
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get field
     *
     * @return lastName
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * set field
     *
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
