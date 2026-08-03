package com.example.cdc_synchronization_engine.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "role_name",
            nullable = false,
            unique = true,
            length = 50)
    private String roleName;

    @Column(name = "description",
            length = 255)
    private String description;

    @Column(name = "created_at",
            nullable = false,
            updatable = false)
    private LocalDateTime createdAt;

//    @ManyToMany(mappedBy = "roles")
//    private Set<User> users = new HashSet<>();

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    public Role() {
    }

    // Generate Getters and Setters
}