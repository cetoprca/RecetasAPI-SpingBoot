package com.github.cetoprca.recetasapispringboot.controllers;

import com.github.cetoprca.recetasapispringboot.DTO.IngredientDTO;
import com.github.cetoprca.recetasapispringboot.DTO.TagDTO;
import com.github.cetoprca.recetasapispringboot.model.Ingredient;
import com.github.cetoprca.recetasapispringboot.model.Recipe;
import com.github.cetoprca.recetasapispringboot.service.IngredientService;
import com.github.cetoprca.recetasapispringboot.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("api/ingredient")
public class IngredientController extends GenericController<Ingredient, IngredientDTO> {

    private final IngredientService ingredientService;
    private final RecipeService recipeService;

    public IngredientController(IngredientService ingredientService, RecipeService recipeService) {
        super(ingredientService);
        this.ingredientService = ingredientService;
        this.recipeService = recipeService;
    }

    @Override
    @PatchMapping
    public ResponseEntity<?> update(@RequestBody IngredientDTO entityDTO){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null){
                throw new UserPrincipalNotFoundException(null);
            }

            if (!authentication.getName().equals("root")){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            return super.update(entityDTO);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @Override
    @DeleteMapping("/{ingredientID}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "ingredientID") Integer id) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null){
                throw new UserPrincipalNotFoundException(null);
            }

            if (!authentication.getName().equals("root")){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            return super.deleteById(id);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @Override
    protected Ingredient setRelations(Ingredient entity, IngredientDTO dto) {
        List<Recipe> recipes = new ArrayList<>();

        if (dto.recipes() != null){
            dto.recipes().forEach(id -> {
                recipeService.findByIdRaw(id).ifPresent(recipes::add);
            });
        }

        entity.setRecipes(new HashSet<>(recipes));

        return entity;
    }
}
