package com.github.cetoprca.recetasapispringboot.DTO;

import com.github.cetoprca.recetasapispringboot.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

public record RecipeCardDTO(
        Integer id,
        List<Integer> tags,
        String imageURL,
        String title,
        String description,
        String cuisine,
        Integer authorId,
        String authorUsername,
        String authorProfilePictureURL,
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

        String userProfilePicture = "";
        if (recipe.getAuthor() != null){
            if (recipe.getAuthor().getProfilePicture() != null){
                userProfilePicture = recipe.getAuthor().getProfilePicture().getUrl();
            }
        }

        this(
                recipe.getId(),
                recipe.getTags() == null ? new ArrayList<>() : recipe.getTags().stream().map(Tag::getId).toList(),
                recipe.getImage() == null ? "" : recipe.getImage().getUrl(),
                recipe.getTitle() == null ? "" : recipe.getTitle(),
                recipe.getDescription() == null ? "" : recipe.getDescription(),
                recipe.getCuisine() == null ? "" : recipe.getCuisine().getName(),
                recipe.getAuthor() == null ? -1 : recipe.getAuthor().getId(),
                recipe.getAuthor() == null ? "" : recipe.getAuthor().getUsername(),
                userProfilePicture,
                stars,
                recipe.getPrepTime() == null ? 0 : recipe.getPrepTime(),
                recipe.getCookTime() == null ? 0 : recipe.getCookTime()
        );
    }
}