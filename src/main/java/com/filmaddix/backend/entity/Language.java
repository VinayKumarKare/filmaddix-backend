package com.filmaddix.backend.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "languages",
        uniqueConstraints = @UniqueConstraint(columnNames = "code")
)
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ISO-style short code: en, hi, ta, te
    @Column(nullable = false, unique = true, length = 10)
    private String code;

    // Display name: English, Hindi, Tamil
    @Column(nullable = false, length = 50)
    private String name;

    // Required by JPA
    protected Language() {
    }

    @OneToMany(mappedBy = "language", fetch = FetchType.LAZY)
    private Set<ContentLanguage> contents = new HashSet<>();

    // Controlled constructor (bootstrap only)
    public Language(String code, String name) {
        this.code = code;
        this.name = name;
    }

    // -------- Getters --------

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
