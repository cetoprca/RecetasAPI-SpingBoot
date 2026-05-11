package com.github.cetoprca.recetasapispringboot.DTO;

import com.github.cetoprca.recetasapispringboot.model.Rating;
import com.github.cetoprca.recetasapispringboot.model.Recipe;
import com.github.cetoprca.recetasapispringboot.model.User;

import java.util.ArrayList;
import java.util.List;

public record UserDTO(
        String id,
        String displayName,
        String biography,
        String profilePicturePath,
        List<Integer> recipes,
        List<Integer> savedRecipes,
        List<Integer> ratings,
        List<String> following
) implements GenericDTO<User, String> {
    public UserDTO(User user){
        this(
                user.getId(),
                user.getDisplayName(),
                user.getBiography() == null ? "" : user.getBiography(),
                user.getProfilePicture() == null ? "placeholder" : user.getProfilePicture().getUrl(),
                user.getRecipes() == null ? new ArrayList<>() : user.getRecipes().stream().map(Recipe::getId).toList(),
                user.getSavedRecipes() == null ? new ArrayList<>() : user.getSavedRecipes().stream().map(Recipe::getId).toList(),
                user.getRatings() == null ? new ArrayList<>() : user.getRatings().stream().map(Rating::getId).toList(),
                user.getFollowing() == null ? new ArrayList<>() : user.getFollowing().stream().map(User::getId).toList()
                );
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public GenericDTO<User, String> fromModel(User entity) {
        return new UserDTO(entity);
    }

    @Override
    public User toModel() {
        return new User(this);
    }
}
