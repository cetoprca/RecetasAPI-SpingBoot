package com.github.cetoprca.recetasapispringboot.controllers;

import com.github.cetoprca.recetasapispringboot.DTO.StepDTO;
import com.github.cetoprca.recetasapispringboot.model.Step;
import com.github.cetoprca.recetasapispringboot.service.RecipeService;
import com.github.cetoprca.recetasapispringboot.service.StepService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("")
public class StepController extends GenericController<Step, StepDTO> {

    private final StepService stepService;
    private final RecipeService recipeService;

    public StepController(StepService stepService, RecipeService recipeService) {
        super(stepService);
        this.stepService = stepService;
        this.recipeService = recipeService;
    }

    @Override
    protected Step setRelations(Step entity, StepDTO dto) {
        recipeService.findByIdRaw(dto.recipe()).ifPresent(entity::setRecipe);

        return entity;
    }
}
