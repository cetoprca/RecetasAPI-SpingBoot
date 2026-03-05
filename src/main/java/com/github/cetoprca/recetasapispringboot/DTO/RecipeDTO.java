package com.github.cetoprca.recetasapispringboot.DTO;

import com.github.cetoprca.recetasapispringboot.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record RecipeDTO(
        Integer id,
        String title,
        String description,
        String image,
        Integer prepTime,
        Integer cookTime,
        Integer totalTime,
        Boolean isPublic,
        LocalDate creationDate,
        Integer author,
        Integer cuisine,
        List<Integer> ratings,
        List<Integer> steps,
        List<Integer> tags,
        List<Integer> ingredients
        ) implements GenericDTO<Recipe> {
    public RecipeDTO(Recipe recipe){
        this(
                recipe.getId(),
                recipe.getTitle() == null ? "" : recipe.getTitle(),
                recipe.getDescription() == null ? "" : recipe.getDescription(),
                recipe.getImage() == null ? "" : recipe.getImage(),
                recipe.getPrepTime() == null ? 0 : recipe.getPrepTime(),
                recipe.getCookTime() == null ? 0 : recipe.getCookTime(),
                recipe.getTotalTime() == null ? 0 : recipe.getTotalTime(),
                recipe.getIsPublic() != null && recipe.getIsPublic(),
                recipe.getCreationDate() == null ? LocalDate.now() : recipe.getCreationDate(),
                recipe.getUser() == null ? -1 : recipe.getUser().getId(),
                recipe.getCuisine() == null ? -1 : recipe.getCuisine().getId(),
                recipe.getRatings() == null ? new ArrayList<>() : recipe.getRatings().stream().map(Rating::getId).toList(),
                recipe.getSteps() == null ? new ArrayList<>() : recipe.getSteps().stream().map(Step::getId).toList(),
                recipe.getTags() == null ? new ArrayList<>() : recipe.getTags().stream().map(Tag::getId).toList(),
                recipe.getIngredients() == null ? new ArrayList<>() : recipe.getIngredients().stream().map(Ingredient::getId).toList()

                );
    }
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public GenericDTO<Recipe> fromModel(Recipe entity) {
        return new RecipeDTO(entity);
    }

    @Override
    public Recipe toModel() {
        return new Recipe(this);
    }

}
