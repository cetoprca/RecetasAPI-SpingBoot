package com.github.cetoprca.recetasapispringboot.controllers;

import com.github.cetoprca.recetasapispringboot.DTO.IngredientDTO;
import com.github.cetoprca.recetasapispringboot.model.Ingredient;
import com.github.cetoprca.recetasapispringboot.model.Recipe;
import com.github.cetoprca.recetasapispringboot.service.IngredientService;
import com.github.cetoprca.recetasapispringboot.service.RecipeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("api/ingredient")
public class IngredientController extends GenericController<Ingredient, IngredientDTO> {

    private final IngredientService ingredientService;
    private final RecipeService recipeService;

    public IngredientController(IngredientService ingredientService, RecipeService recipeService) {
        super(ingredientService);
        this.ingredientService = ingredientService;
        this.recipeService = recipeService;
    }

    @Override
    protected Ingredient setRelations(Ingredient entity, IngredientDTO dto) {
        List<Recipe> recipes = new ArrayList<>();

        if (dto.recipes() != null){
            dto.recipes().forEach(id -> {
                recipeService.findByIdRaw(id).ifPresent(recipes::add);
            });
        }

        entity.setRecipes(new HashSet<>(recipes));

        return entity;
    }
}
