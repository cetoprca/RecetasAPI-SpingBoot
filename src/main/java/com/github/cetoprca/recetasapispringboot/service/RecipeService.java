package com.github.cetoprca.recetasapispringboot.service;

import com.github.cetoprca.recetasapispringboot.DTO.RecipeDTO;
import com.github.cetoprca.recetasapispringboot.model.Recipe;
import com.github.cetoprca.recetasapispringboot.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RecipeService extends GenericService<Recipe, RecipeDTO, Integer> {

    @Autowired
    private RecipeRepository recipeRepository;

    protected RecipeService(JpaRepository<Recipe, Integer> repository) {
        super(repository);
    }

    @Override
    protected RecipeDTO toDTO(Recipe entity) {
        return new RecipeDTO(entity);
    }

    public Page<Recipe> findByFilter(Specification<Recipe> spec, Pageable pageable) {
        return recipeRepository.findAll(spec, pageable);
    }

    public Page<Recipe> findSavedRecipesByUserId(String userId, Pageable pageable) {
        return recipeRepository.findSavedRecipesByUserId(userId, pageable);
    }

    public Page<Recipe> findByAuthorId(String userId, Pageable pageable) {
        return recipeRepository.findByAuthorId(userId, pageable);
    }

    @Transactional
    public void deleteSavedRecipeReferences(Integer recipeId) {
        recipeRepository.deleteSavedRecipeReferences(recipeId);
    }
}
