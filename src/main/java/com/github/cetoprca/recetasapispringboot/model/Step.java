package com.github.cetoprca.recetasapispringboot.model;

import com.github.cetoprca.recetasapispringboot.DTO.StepDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "step")

@Getter
@Setter
@ToString
@NoArgsConstructor @AllArgsConstructor
public class Step implements BaseModel<Step, StepDTO> {
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

    @Override
    public Step mergeWith(Step baseModel) {
        if (baseModel.getTitle() != null) this.title = baseModel.getTitle();
        if (baseModel.getDescription() != null) this.description = baseModel.getDescription();
        if (baseModel.getPosition() != null) this.position = baseModel.getPosition();
        if (baseModel.getImage() != null) this.image = baseModel.getImage();
        if (baseModel.getRecipe() != null) this.recipe = baseModel.getRecipe();

        return this;
    }

}
