package com.github.cetoprca.recetasapispringboot.repository;

import com.github.cetoprca.recetasapispringboot.model.Recipe;
import com.github.cetoprca.recetasapispringboot.model.Step;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StepRepository extends JpaRepository<Step, Integer> {
    List<Step> findByRecipe(Recipe recipe);
    List<Step> findByRecipe_id(Integer recipe_id);

}
