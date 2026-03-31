package com.github.cetoprca.recetasapispringboot.DTO;

import com.github.cetoprca.recetasapispringboot.model.Cuisine;
import com.github.cetoprca.recetasapispringboot.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public record CuisineDTO(
        Integer id,
        String name,
        List<Integer> recipes
) implements GenericDTO<Cuisine>{
    public CuisineDTO(Cuisine cuisine){
        this(
                cuisine.getId(),
                cuisine.getName() == null ? "" : cuisine.getName(),
                cuisine.getRecipes() == null ? new ArrayList<>() : cuisine.getRecipes().stream().map(Recipe::getId).toList()
        );
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public GenericDTO<Cuisine> fromModel(Cuisine entity) {
        return new CuisineDTO(entity);
    }

    @Override
    public Cuisine toModel() {
        return new Cuisine(this);
    }
}
