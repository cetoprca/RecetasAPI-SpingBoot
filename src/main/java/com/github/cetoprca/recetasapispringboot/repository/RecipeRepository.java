package com.github.cetoprca.recetasapispringboot.repository;

import com.github.cetoprca.recetasapispringboot.model.Cuisine;
import com.github.cetoprca.recetasapispringboot.model.Recipe;
import com.github.cetoprca.recetasapispringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    Optional<Recipe> findByTitle(String title);
    
    List<Recipe> findByUser(User user);
    List<Recipe> findByUser_id(Integer user_id);

    List<Recipe> findByCuisine(Cuisine cuisine);
    List<Recipe> findByCuisine_id(Integer cuisine_id);

    List<Recipe> findDistinctByTags_IdIn(Collection<Integer> tagIds);
    List<Recipe> findDistinctByIngredients_IdIn(Collection<Integer> ingredientIds);
}
