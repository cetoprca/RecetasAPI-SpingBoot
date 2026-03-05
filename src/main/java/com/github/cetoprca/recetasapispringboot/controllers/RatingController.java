package com.github.cetoprca.recetasapispringboot.controllers;

import com.github.cetoprca.recetasapispringboot.DTO.RatingDTO;
import com.github.cetoprca.recetasapispringboot.DTO.TagDTO;
import com.github.cetoprca.recetasapispringboot.model.Rating;
import com.github.cetoprca.recetasapispringboot.model.Recipe;
import com.github.cetoprca.recetasapispringboot.model.Tag;
import com.github.cetoprca.recetasapispringboot.service.RatingService;
import com.github.cetoprca.recetasapispringboot.service.RecipeService;
import com.github.cetoprca.recetasapispringboot.service.TagService;
import com.github.cetoprca.recetasapispringboot.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("")
public class RatingController extends GenericController<Rating, RatingDTO> {

    private final RatingService ratingService;
    private final RecipeService recipeService;
    private final UserService userService;

    public RatingController(RatingService ratingService, RecipeService recipeService, UserService userService) {
        super(ratingService);
        this.ratingService = ratingService;
        this.recipeService = recipeService;
        this.userService = userService;
    }

    @Override
    protected Rating setRelations(Rating entity, RatingDTO dto) {
        userService.findByIdRaw(dto.author()).ifPresent(entity::setUser);
        recipeService.findByIdRaw(dto.author()).ifPresent(entity::setRecipe);

        return entity;
    }
}
