package com.github.cetoprca.recetasapispringboot.DTO;

import com.github.cetoprca.recetasapispringboot.model.Step;

public record StepDTO(
        Integer id,
        String title,
        String description,
        Integer position,
        String image,
        Integer recipe
) {
    public StepDTO(Step step){
        this(
                step.getId(),
                step.getTitle() == null ? "" : step.getTitle(),
                step.getDescription() == null ? "" : step.getDescription(),
                step.getPosition() == null ? 0 : step.getPosition(),
                step.getImage() == null ? "" : step.getImage(),
                step.getRecipe() == null ? -1 : step.getRecipe().getId()
                );
    }
}
