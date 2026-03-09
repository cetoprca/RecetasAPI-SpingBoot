package com.github.cetoprca.recetasapispringboot.controllers;

import com.github.cetoprca.recetasapispringboot.DTO.TagDTO;
import com.github.cetoprca.recetasapispringboot.model.Recipe;
import com.github.cetoprca.recetasapispringboot.model.Tag;
import com.github.cetoprca.recetasapispringboot.service.RecipeService;
import com.github.cetoprca.recetasapispringboot.service.TagService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("api/recipes/tag")
public class TagController extends GenericController<Tag, TagDTO> {

    private final TagService tagService;
    private final RecipeService recipeService;

    public TagController(TagService tagService, RecipeService recipeService) {
        super(tagService);
        this.tagService = tagService;
        this.recipeService = recipeService;
    }

    @Override
    protected Tag setRelations(Tag entity, TagDTO dto) {
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
