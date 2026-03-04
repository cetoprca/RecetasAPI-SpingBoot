package com.github.cetoprca.recetasapispringboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "step")

@Data
@NoArgsConstructor @AllArgsConstructor
public class Step {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description", unique = true)
    private String description;

    @Column(name = "position")
    private Integer position;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
}
