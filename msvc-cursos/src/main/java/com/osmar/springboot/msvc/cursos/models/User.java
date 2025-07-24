package com.osmar.springboot.msvc.cursos.models;

public class User {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String lastName;

    /**
     * get field
     *
     * @return id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * set field
     *
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
     * get field
     *
     * @return email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * set field
     *
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
