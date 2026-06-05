package com.github.cetoprca.recetasapispringboot.DTO;

import com.github.cetoprca.recetasapispringboot.model.Step;

public record StepDTO(
        Integer id,
        String title,
        String description,
        Integer position,
        String image,
        Integer recipe
) implements GenericDTO<Step, Integer> {
    public StepDTO(Step step){
        this(
                step.getId(),
                step.getTitle() == null ? "" : step.getTitle(),
                step.getDescription() == null ? "" : step.getDescription(),
                step.getPosition() == null ? 0 : step.getPosition(),
                step.getImage() == null ? "" : step.getImage().getUrl(),
                step.getRecipe() == null ? -1 : step.getRecipe().getId()
                );
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public GenericDTO<Step, Integer> fromModel(Step entity) {
        return new StepDTO(entity);
    }

    @Override
    public Step toModel() {
        return new Step(this);
    }
}
