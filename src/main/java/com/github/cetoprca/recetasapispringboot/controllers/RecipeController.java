package com.github.cetoprca.recetasapispringboot.controllers;

import com.github.cetoprca.recetasapispringboot.DTO.RecipeDTO;
import com.github.cetoprca.recetasapispringboot.model.*;
import com.github.cetoprca.recetasapispringboot.service.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RecipeController extends GenericController<Recipe, RecipeDTO> {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final RatingService ratingService;
    private final StepService stepService;
    private final CuisineService cuisineService;
    private final TagService tagService;
    private final UserService userService;

    public RecipeController(RecipeService recipeService, IngredientService ingredientService, RatingService ratingService, StepService stepService, CuisineService cuisineService, TagService tagService, UserService userService) {
        super(recipeService);
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.ratingService = ratingService;
        this.stepService = stepService;
        this.cuisineService = cuisineService;
        this.tagService = tagService;
        this.userService = userService;
    }

    @Override
    protected Recipe setRelations(Recipe entity, RecipeDTO dto) {
        List<Ingredient> ingredients = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        List<Step> steps = new ArrayList<>();
        List<Rating> ratings = new ArrayList<>();

        dto.ingredients().forEach(id -> {
            ingredientService.findByIdRaw(id).ifPresent(ingredients::add);
        });
        dto.tags().forEach(id -> {
            tagService.findByIdRaw(id).ifPresent(tags::add);
        });
        dto.steps().forEach(id -> {
            stepService.findByIdRaw(id).ifPresent(steps::add);
        });
        dto.ratings().forEach(id -> {
            ratingService.findByIdRaw(id).ifPresent(ratings::add);
        });

        userService.findByIdRaw(dto.author()).ifPresent(entity::setUser);
        cuisineService.findByIdRaw(dto.cuisine()).ifPresent(entity::setCuisine);

        entity.setIngredients(new HashSet<>(ingredients));
        entity.setTags(new HashSet<>(tags));
        entity.setSteps(new HashSet<>(steps));
        entity.setRatings(new HashSet<>(ratings));

        return entity;
    }
}
