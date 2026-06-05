package com.github.cetoprca.recetasapispringboot.service;

import com.github.cetoprca.recetasapispringboot.DTO.StepDTO;
import com.github.cetoprca.recetasapispringboot.model.Recipe;
import com.github.cetoprca.recetasapispringboot.model.Step;
import com.github.cetoprca.recetasapispringboot.repository.StepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StepService extends GenericService<Step, StepDTO, Integer> {

    @Autowired
    private StepRepository stepRepository;

    protected StepService(JpaRepository<Step, Integer> repository) {
        super(repository);
    }

    @Override
    protected StepDTO toDTO(Step entity) {
        return new StepDTO(entity);
    }

    public List<StepDTO> findByRecipe(Recipe recipe){
        return stepRepository.findByRecipe(recipe).stream().map(this::toDTO).toList();
    }

    public List<StepDTO> findByRecipeId(Integer recipeId){
        return stepRepository.findByRecipe_id(recipeId).stream().map(this::toDTO).toList();
    }

}
