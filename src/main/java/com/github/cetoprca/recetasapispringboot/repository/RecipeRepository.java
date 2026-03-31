package com.github.cetoprca.recetasapispringboot.repository;

import com.github.cetoprca.recetasapispringboot.model.Cuisine;
import com.github.cetoprca.recetasapispringboot.model.Recipe;
import com.github.cetoprca.recetasapispringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Integer>, JpaSpecificationExecutor<Recipe> {
    Optional<Recipe> findByTitle(String title);
    
    List<Recipe> findByAuthor(User user);
    List<Recipe> findByAuthor_id(Integer user_id);

    List<Recipe> findByCuisine(Cuisine cuisine);
    List<Recipe> findByCuisine_id(Integer cuisine_id);

    List<Recipe> findDistinctByTags_IdIn(Collection<Integer> tagIds);
    List<Recipe> findDistinctByIngredients_IdIn(Collection<Integer> ingredientIds);
}
