package com.github.cetoprca.recetasapispringboot.DTO;

import com.github.cetoprca.recetasapispringboot.model.Rating;
import com.github.cetoprca.recetasapispringboot.model.Recipe;
import com.github.cetoprca.recetasapispringboot.model.User;

import java.util.ArrayList;
import java.util.List;

public record UserDTO(Integer id, String username, String biography, String profilePicturePath, List<Integer> recipes, List<Integer> savedRecipes, List<Integer> ratings) {
    public UserDTO(User user){
        this(
                user.getId(),
                user.getUsername(),
                user.getBiography() == null ? "" : user.getBiography(),
                user.getProfilePicturePath() == null ? "placeholder" : user.getProfilePicturePath(),
                user.getRecipes() == null ? new ArrayList<>() : user.getRecipes().stream().map(Recipe::getId).toList(),
                user.getSavedRecipes() == null ? new ArrayList<>() : user.getSavedRecipes().stream().map(Recipe::getId).toList(),
                user.getRatings() == null ? new ArrayList<>() : user.getRatings().stream().map(Rating::getId).toList()
        );
    }
}
