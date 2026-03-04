package com.github.cetoprca.recetasapispringboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ingredient")

@Data
@NoArgsConstructor @AllArgsConstructor
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @ManyToMany(mappedBy = "ingredients")
    private Set<Recipe> recipes = new HashSet<>();
}
