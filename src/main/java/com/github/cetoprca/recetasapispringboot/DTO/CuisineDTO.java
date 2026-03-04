package com.github.cetoprca.recetasapispringboot.DTO;

import com.github.cetoprca.recetasapispringboot.model.Cuisine;
import com.github.cetoprca.recetasapispringboot.model.Ingredient;
import com.github.cetoprca.recetasapispringboot.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public record CuisineDTO(
        Integer id,
        String name,
        List<Integer> recipes
) {
    public CuisineDTO(Cuisine cuisine){
        this(
                cuisine.getId(),
                cuisine.getName() == null ? "" : cuisine.getName(),
                cuisine.getRecipes() == null ? new ArrayList<>() : cuisine.getRecipes().stream().map(Recipe::getId).toList()
        );
    }
}
