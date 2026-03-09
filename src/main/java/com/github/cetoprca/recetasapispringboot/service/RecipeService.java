package com.github.cetoprca.recetasapispringboot.service;

import com.github.cetoprca.recetasapispringboot.DTO.RecipeDTO;
import com.github.cetoprca.recetasapispringboot.model.Cuisine;
import com.github.cetoprca.recetasapispringboot.model.Recipe;
import com.github.cetoprca.recetasapispringboot.model.User;
import com.github.cetoprca.recetasapispringboot.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<RecipeDTO> findByTitle(String title){
        return recipeRepository.findByTitle(title).map(this::toDTO);
    }

    public List<RecipeDTO> findByAuthor(User user){
        return recipeRepository.findByAuthor(user).stream().map(this::toDTO).toList();
    }
    public List<RecipeDTO> findByAuthor_id(Integer userId){
        return recipeRepository.findByAuthor_id(userId).stream().map(this::toDTO).toList();
    }

    public List<RecipeDTO> findByCuisine(Cuisine cuisine){
        return recipeRepository.findByCuisine(cuisine).stream().map(this::toDTO).toList();
    }
    public List<RecipeDTO> findByCuisineId(Integer cuisineId){
        return recipeRepository.findByCuisine_id(cuisineId).stream().map(this::toDTO).toList();
    }

    public List<RecipeDTO> findByTags(Integer... tagsId){
        return recipeRepository.findDistinctByTags_IdIn(List.of(tagsId)).stream().map(this::toDTO).toList();
    }

    public List<RecipeDTO> findByIngredients(Integer... ingredientIds){
        return recipeRepository.findDistinctByIngredients_IdIn(List.of(ingredientIds)).stream().map(this::toDTO).toList();
    }


}
