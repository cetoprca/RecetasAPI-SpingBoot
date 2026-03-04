package com.github.cetoprca.recetasapispringboot.DTO;

import com.github.cetoprca.recetasapispringboot.model.Cuisine;
import com.github.cetoprca.recetasapispringboot.model.Recipe;
import com.github.cetoprca.recetasapispringboot.model.Tag;

import java.util.ArrayList;
import java.util.List;

public record TagDTO(
        Integer id,
        String name,
        List<Integer> recipes
) {
    public TagDTO(Tag tag){
        this(
                tag.getId(),
                tag.getName() == null ? "" : tag.getName(),
                tag.getRecipes() == null ? new ArrayList<>() : tag.getRecipes().stream().map(Recipe::getId).toList()
        );
    }
}
