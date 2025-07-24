package com.osmar.springboot.msvc.cursos.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cursos_users")
public class CursoUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", unique = true, nullable = false)
    private Long userId;

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
     * get field @Column(unique = true, nullable = false)
     *
     * @return userId @Column(unique = true, nullable = false)

     */
    public Long getUserId() {
        return this.userId;
    }

    /**
     * set field @Column(unique = true, nullable = false)
     *
     * @param userId @Column(unique = true, nullable = false)

     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * hashCode method
     *
     * @return int hash code
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CursoUser other)) {
            return false;
        }

        return this.userId != null && this.userId.equals(other.userId);
    }
}


