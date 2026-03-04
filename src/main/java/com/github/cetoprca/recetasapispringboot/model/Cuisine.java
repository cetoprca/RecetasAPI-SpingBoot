package com.github.cetoprca.recetasapispringboot.model;

import com.github.cetoprca.recetasapispringboot.DTO.CuisineDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cuisine")

@Data
@NoArgsConstructor @AllArgsConstructor
public class Cuisine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "cuisine")
    private Set<Recipe> recipes = new HashSet<>();

    public Cuisine(CuisineDTO cuisineDTO){
        this.id = cuisineDTO.id();
        this.name = cuisineDTO.name();
    }
}
