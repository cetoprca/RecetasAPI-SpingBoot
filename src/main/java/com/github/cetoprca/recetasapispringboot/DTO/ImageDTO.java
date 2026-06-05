package com.github.cetoprca.recetasapispringboot.DTO;

import com.github.cetoprca.recetasapispringboot.model.*;

import java.util.List;

public record ImageDTO(
        String url,
        List<String> usedInUsers,
        List<Integer> usedInRecipes,
        List<Integer> usedInSteps
) {
    public ImageDTO(Image image){
        this(
                image.getUrl(),
                image.getUsedInUsers().stream().map(User::getId).toList(),
                image.getUsedInRecipes().stream().map(Recipe::getId).toList(),
                image.getUsedInSteps().stream().map(Step::getId).toList()
        );
    }

    public GenericDTO<Rating, Integer> fromModel(Rating entity) {
        return new RatingDTO(entity);
    }

    public Image toModel() {
        return new Image(this);
    }
}
