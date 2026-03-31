package com.github.cetoprca.recetasapispringboot.service;

import com.github.cetoprca.recetasapispringboot.DTO.FilterDTO;
import com.github.cetoprca.recetasapispringboot.DTO.RecipeDTO;
import com.github.cetoprca.recetasapispringboot.model.Cuisine;
import com.github.cetoprca.recetasapispringboot.model.Recipe;
import com.github.cetoprca.recetasapispringboot.model.User;
import com.github.cetoprca.recetasapispringboot.repository.RecipeRepository;
import com.github.cetoprca.recetasapispringboot.repository.filters.RecipeSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeService extends GenericService<Recipe, RecipeDTO> {

    @Autowired
    private RecipeRepository recipeRepository;


    protected RecipeService(JpaRepository<Recipe, Integer> repository) {
        super(repository);
    }

    @Override
    protected RecipeDTO toDTO(Recipe entity) {
        return new RecipeDTO(entity);
    }

    public List<RecipeDTO> findByFilter(FilterDTO filterDTO){
        return recipeRepository.findAll(RecipeSpec.conFiltro(filterDTO)).stream().map(this::toDTO).toList();
    }
}
