package com.github.cetoprca.recetasapispringboot.controllers;

import com.github.cetoprca.recetasapispringboot.DTO.CuisineDTO;
import com.github.cetoprca.recetasapispringboot.DTO.TagDTO;
import com.github.cetoprca.recetasapispringboot.model.Cuisine;
import com.github.cetoprca.recetasapispringboot.model.Recipe;
import com.github.cetoprca.recetasapispringboot.model.Tag;
import com.github.cetoprca.recetasapispringboot.service.CuisineService;
import com.github.cetoprca.recetasapispringboot.service.RecipeService;
import com.github.cetoprca.recetasapispringboot.service.TagService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("")
public class CuisineController extends GenericController<Cuisine, CuisineDTO> {

    private final CuisineService cuisineService;
    private final RecipeService recipeService;

    public CuisineController(CuisineService cuisineService, RecipeService recipeService) {
        super(cuisineService);
        this.cuisineService = cuisineService;
        this.recipeService = recipeService;
    }

    @Override
    protected Cuisine setRelations(Cuisine entity, CuisineDTO dto) {
        List<Recipe> recipes = new ArrayList<>();

        dto.recipes().forEach(id -> {
            recipeService.findByIdRaw(id).ifPresent(recipes::add);
        });

        entity.setRecipes(new HashSet<>(recipes));

        return entity;
    }
}
