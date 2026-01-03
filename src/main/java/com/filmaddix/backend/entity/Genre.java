package com.filmaddix.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "genres",
        uniqueConstraints = @UniqueConstraint(columnNames = "name")
)
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    // ✅ REQUIRED by JPA
    protected Genre() {
    }

    // ✅ Controlled constructor (for bootstrap only)
    public Genre(String name) {
        this.name = name;
    }

    // ---------- Getters ----------

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
