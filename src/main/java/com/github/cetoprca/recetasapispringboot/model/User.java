package com.github.cetoprca.recetasapispringboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")

@Data
@NoArgsConstructor @AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "password")
    private String password;

    @Column(name = "biography")
    private String biography;

    @Column(name = "profilePicturePath")
    private String profilePicturePath;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Recipe> savedRecipes = new HashSet<>();
    @OneToMany(mappedBy = "user")
    private Set<Recipe> recipes = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Rating> ratings = new HashSet<>();

}
