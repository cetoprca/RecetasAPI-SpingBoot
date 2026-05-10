package com.github.cetoprca.recetasapispringboot.DTO;

import com.github.cetoprca.recetasapispringboot.model.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RecipeCardDTO {
    Integer id;
    List<TagDTO> tags;
    String imageURL;
    String title;
    String description;
    String cuisine;
    UserDTO author;
    Integer stars;
    Integer prepTime;
    Integer cookTime;
    Integer totalTime;
    Boolean isSaved;
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

        List<TagDTO> tags = recipe.getTags().stream().map(TagDTO::new).sorted(Comparator.comparing(TagDTO::name)).toList();

        this.id = recipe.getId();
        this.tags = tags;
        this.imageURL = imageUrl;
        this.title = recipe.getTitle();
        this.description = recipe.getDescription();
        this.cuisine = cuisine;
        this.author = userDTO;
        this.stars = stars;
        this.prepTime = recipe.getPrepTime();
        this.cookTime = recipe.getCookTime();
        this.totalTime = recipe.getTotalTime();
        this.isSaved = false;
    }
}