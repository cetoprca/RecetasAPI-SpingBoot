package com.github.cetoprca.recetasapispringboot.DTO;

import com.github.cetoprca.recetasapispringboot.model.Cuisine;
import com.github.cetoprca.recetasapispringboot.model.Recipe;
import com.github.cetoprca.recetasapispringboot.model.Tag;
import com.github.cetoprca.recetasapispringboot.model.User;

import java.util.ArrayList;
import java.util.List;

public record TagDTO(
        Integer id,
        String name,
        List<Integer> recipes
) implements GenericDTO<Tag>{
    public TagDTO(Tag tag){
        this(
                tag.getId(),
                tag.getName() == null ? "" : tag.getName(),
                tag.getRecipes() == null ? new ArrayList<>() : tag.getRecipes().stream().map(Recipe::getId).toList()
        );
    }
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public GenericDTO<Tag> fromModel(Tag entity) {
        return new TagDTO(entity);
    }

    @Override
    public Tag toModel() {
        return new Tag(this);
    }
}
