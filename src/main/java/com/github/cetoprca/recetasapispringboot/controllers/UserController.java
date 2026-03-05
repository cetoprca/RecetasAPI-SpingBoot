package com.github.cetoprca.recetasapispringboot.controllers;

import com.github.cetoprca.recetasapispringboot.DTO.UserDTO;
import com.github.cetoprca.recetasapispringboot.model.Rating;
import com.github.cetoprca.recetasapispringboot.model.Recipe;
import com.github.cetoprca.recetasapispringboot.model.User;
import com.github.cetoprca.recetasapispringboot.service.RatingService;
import com.github.cetoprca.recetasapispringboot.service.RecipeService;
import com.github.cetoprca.recetasapispringboot.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController extends GenericController<User, UserDTO> {

    private final UserService userService;
    private final RatingService ratingService;
    private final RecipeService recipeService;

    public UserController(UserService userService, RatingService ratingService, RecipeService recipeService) {
        super(userService);
        this.userService = userService;
        this.ratingService = ratingService;
        this.recipeService = recipeService;
    }

    @Override
    protected User setRelations(User entity, UserDTO dto) {
        List<Recipe> recipes = new ArrayList<>();
        List<Rating> ratings = new ArrayList<>();

        dto.recipes().forEach(id -> {
            recipeService.findByIdRaw(id).ifPresent(recipes::add);
        });

        dto.ratings().forEach(id -> {
            ratingService.findByIdRaw(id).ifPresent(ratings::add);
        });

        entity.setRecipes(new HashSet<>(recipes));
        entity.setRatings(new HashSet<>(ratings));

        return entity;
    }
}
