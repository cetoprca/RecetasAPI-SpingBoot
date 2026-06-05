package com.github.cetoprca.recetasapispringboot.controllers;

import com.github.cetoprca.recetasapispringboot.DTO.CuisineDTO;
import com.github.cetoprca.recetasapispringboot.DTO.TagDTO;
import com.github.cetoprca.recetasapispringboot.model.Cuisine;
import com.github.cetoprca.recetasapispringboot.model.Recipe;
import com.github.cetoprca.recetasapispringboot.service.CuisineService;
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
@RequestMapping("api/recipes/cuisine")
public class CuisineController extends GenericController<Cuisine, CuisineDTO, Integer> {

    private final CuisineService cuisineService;
    private final RecipeService recipeService;

    public CuisineController(CuisineService cuisineService, RecipeService recipeService) {
        super(cuisineService);
        this.cuisineService = cuisineService;
        this.recipeService = recipeService;
    }

    @Override
    @PatchMapping
    public ResponseEntity<?> update(@RequestBody CuisineDTO entityDTO){
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
    @DeleteMapping("/{cuisineID}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "cuisineID") Integer id) {
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
    protected Cuisine setRelations(Cuisine entity, CuisineDTO dto) {
        List<Recipe> recipes = new ArrayList<>();

        if (dto.recipes() != null){
            dto.recipes().forEach(id -> recipeService.findByIdRaw(id).ifPresent(recipes::add));
        }

        entity.setRecipes(new HashSet<>(recipes));

        return entity;
    }
}
