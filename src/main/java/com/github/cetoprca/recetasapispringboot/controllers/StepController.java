package com.github.cetoprca.recetasapispringboot.controllers;

import com.github.cetoprca.recetasapispringboot.DTO.StepDTO;
import com.github.cetoprca.recetasapispringboot.model.Recipe;
import com.github.cetoprca.recetasapispringboot.model.Step;
import com.github.cetoprca.recetasapispringboot.service.RecipeService;
import com.github.cetoprca.recetasapispringboot.service.StepService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/recipe/{recipeID}/step")
public class StepController {

    private final StepService stepService;
    private final RecipeService recipeService;

    public StepController(StepService stepService, RecipeService recipeService) {
        this.stepService = stepService;
        this.recipeService = recipeService;
    }

    @GetMapping
    public ResponseEntity<?> findAll(@PathVariable(name = "recipeID") Integer recipeID){
        try {

            Recipe recipe = recipeService.findByIdRaw(recipeID).orElse(null);

            if (recipe == null){
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(recipe.getSteps().stream().map(StepDTO::new).toList());

        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{stepID}")
    public ResponseEntity<?> findById(@PathVariable(name = "recipeID") Integer recipeID, @PathVariable(name = "stepId") Integer stepID){
        try {

            Recipe recipe = recipeService.findByIdRaw(recipeID).orElse(null);

            if (recipe == null){
                return ResponseEntity.notFound().build();
            }

            StepDTO stepDTO = stepService.findById(stepID).orElse(null);

            if (stepDTO == null){
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(stepDTO);

        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@PathVariable(name = "recipeID") Integer recipeID, @RequestBody StepDTO entityDTO){
        try {

            Recipe recipe = recipeService.findByIdRaw(recipeID).orElse(null);

            if (recipe == null){
                return ResponseEntity.notFound().build();
            }


            Step entity = entityDTO.toModel();
            entity = setRelations(entity, entityDTO);

            entity = stepService.save(entity);

            return ResponseEntity.ok(entityDTO.fromModel(entity));

        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PatchMapping
    public ResponseEntity<?> update(@PathVariable(name = "recipeID") Integer recipeID, @RequestBody StepDTO entityDTO){
        try {

            Recipe recipe = recipeService.findByIdRaw(recipeID).orElse(null);

            if (recipe == null){
                return ResponseEntity.notFound().build();
            }

            Step entityA = entityDTO.toModel();
            Step entityB = stepService.findByIdRaw(entityDTO.getId()).orElse(null);

            if (entityB == null){
                return ResponseEntity.notFound().build();
            }

            entityA = setRelations(entityA, entityDTO);

            Step finalEntity = entityB.mergeWith(entityA);

            finalEntity = stepService.update(finalEntity);

            return ResponseEntity.ok(entityDTO.fromModel(finalEntity));

        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{stepID}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "recipeID") Integer recipeID, @PathVariable(name = "stepID") Integer stepID){
        try {

            Recipe recipe = recipeService.findByIdRaw(recipeID).orElse(null);

            if (recipe == null){
                return ResponseEntity.notFound().build();
            }

            Step entity = stepService.findByIdRaw(stepID).orElse(null);

            if (entity != null){
                stepService.deleteById(stepID);
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.notFound().build();

        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    protected Step setRelations(Step entity, StepDTO dto) {
        if (dto.recipe() != null) {
            recipeService.findByIdRaw(dto.recipe()).ifPresent(entity::setRecipe);
        }

        return entity;
    }
}
