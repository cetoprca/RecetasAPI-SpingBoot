package com.github.cetoprca.recetasapispringboot.DTO;

import com.github.cetoprca.recetasapispringboot.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

public record RecipeCardDTO(
        Integer id,
        List<TagDTO> tags,
        String imageURL,
        String title,
        String description,
        String cuisine,
        UserDTO author,
        Integer stars,
        Integer prepTime,
        Integer cookTime
) {
    public RecipeCardDTO(Recipe recipe){
        int stars = 0;
        OptionalDouble starsDouble = recipe.getRatings().stream().mapToInt(Rating::getStars).average();
        if (starsDouble.isPresent()){
            stars = Math.toIntExact(Math.round(starsDouble.getAsDouble()));
        }

        UserDTO userDTO = null;
        if (recipe.getAuthor() != null){
            userDTO = new UserDTO(recipe.getAuthor());
        }

        String imageUrl = "";
        if (recipe.getImage() != null){
            imageUrl = recipe.getImage().getUrl();
        }

        String cuisine = "";
        if (recipe.getCuisine() != null){
            cuisine = recipe.getCuisine().getName();
        }

        List<TagDTO> tags = recipe.getTags().stream().map(TagDTO::new).toList();

        this(
                recipe.getId(),
                tags,
                imageUrl,
                recipe.getTitle(),
                recipe.getDescription(),
                cuisine,
                userDTO,
                stars,
                recipe.getPrepTime(),
                recipe.getCookTime()
        );
    }
}