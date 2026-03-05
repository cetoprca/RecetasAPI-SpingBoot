package com.github.cetoprca.recetasapispringboot.DTO;

import com.github.cetoprca.recetasapispringboot.model.Ingredient;
import com.github.cetoprca.recetasapispringboot.model.Recipe;
import com.github.cetoprca.recetasapispringboot.model.User;

import java.util.ArrayList;
import java.util.List;

public record IngredientDTO(
        Integer id,
        String name,
        List<Integer> recipes
) implements GenericDTO<Ingredient> {
    public IngredientDTO(Ingredient ingredient){
        this(
                ingredient.getId(),
                ingredient.getName() == null ? "" : ingredient.getName(),
                ingredient.getRecipes() == null ? new ArrayList<>() : ingredient.getRecipes().stream().map(Recipe::getId).toList()
        );
    }
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public GenericDTO<Ingredient> fromModel(Ingredient entity) {
        return new IngredientDTO(entity);
    }

    @Override
    public Ingredient toModel() {
        return new Ingredient(this);
    }
}
