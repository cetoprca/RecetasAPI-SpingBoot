package com.github.cetoprca.recetasapispringboot.repository;

import com.github.cetoprca.recetasapispringboot.model.Rating;
import com.github.cetoprca.recetasapispringboot.model.Recipe;
import com.github.cetoprca.recetasapispringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
    Optional<Rating> findByRecipe(Recipe recipe);
    Optional<Rating> findByRecipe_id(Integer recipe_id);

    Optional<Rating> findByUser(User author);
    Optional<Rating> findByUser_id(Integer author_id);
}
