package com.github.cetoprca.recetasapispringboot.model;

import com.github.cetoprca.recetasapispringboot.DTO.StepDTO;
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

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "position")
    private Integer position;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public Step(StepDTO stepDTO) {
        this.id = stepDTO.id();
        this.title = stepDTO.title();
        this.description = stepDTO.description();
        this.position = stepDTO.position();
        this.image = stepDTO.image();
    }
}
