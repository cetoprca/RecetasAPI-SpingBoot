package com.github.cetoprca.recetasapispringboot.controllers;

import com.github.cetoprca.recetasapispringboot.DTO.RatingDTO;
import com.github.cetoprca.recetasapispringboot.model.Rating;
import com.github.cetoprca.recetasapispringboot.model.Recipe;
import com.github.cetoprca.recetasapispringboot.model.User;
import com.github.cetoprca.recetasapispringboot.service.RatingService;
import com.github.cetoprca.recetasapispringboot.service.RecipeService;
import com.github.cetoprca.recetasapispringboot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Objects;

@RestController
@RequestMapping("api/recipe/{recipeID}/rating")
public class RatingController{

    private final RatingService ratingService;
    private final RecipeService recipeService;
    private final UserService userService;

    public RatingController(RatingService ratingService, RecipeService recipeService, UserService userService) {
        this.ratingService = ratingService;
        this.recipeService = recipeService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> findAll(@PathVariable(name = "recipeID") Integer recipeID){
        try {

            Recipe recipe = recipeService.findByIdRaw(recipeID).orElse(null);

            if (recipe == null){
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(recipe.getRatings().stream().map(RatingDTO::new).toList());

        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{ratingID}")
    public ResponseEntity<?> findById(@PathVariable(name = "recipeID") Integer recipeID, @PathVariable(name = "ratingID") Integer stepID){
        try {

            Recipe recipe = recipeService.findByIdRaw(recipeID).orElse(null);

            if (recipe == null){
                return ResponseEntity.notFound().build();
            }

            RatingDTO ratingDTO = ratingService.findById(stepID).orElse(null);

            if (ratingDTO == null){
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(ratingDTO);

        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@PathVariable(name = "recipeID") Integer recipeID, @RequestBody RatingDTO entityDTO, Principal principal){
        try {

            User loggedUser = userService.findByIdRaw(principal.getName()).orElseThrow();

            Recipe recipe = recipeService.findByIdRaw(recipeID).orElse(null);

            if (recipe == null){
                return ResponseEntity.notFound().build();
            }


            Rating entity = entityDTO.toModel();
            entity = setRelations(entity, entityDTO);

            entity.setRecipe(recipe);
            entity.setUser(loggedUser);

            entity = ratingService.save(entity);

            return ResponseEntity.ok(entityDTO.fromModel(entity));

        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PatchMapping
    public ResponseEntity<?> update(@PathVariable(name = "recipeID") Integer recipeID, @RequestBody RatingDTO entityDTO, Principal principal){
        try {

            User loggedUser = userService.findByIdRaw(principal.getName()).orElseThrow();

            Recipe recipe = recipeService.findByIdRaw(recipeID).orElse(null);

            if (recipe == null){
                return ResponseEntity.notFound().build();
            }

            Rating entityA = entityDTO.toModel();

            if (!entityA.getUser().getId().equals(loggedUser.getId())){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            Rating entityB = ratingService.findByIdRaw(entityDTO.getId()).orElse(null);

            if (entityB == null){
                return ResponseEntity.notFound().build();
            }

            entityA = setRelations(entityA, entityDTO);

            Rating finalEntity = entityB.mergeWith(entityA);

            finalEntity.setUser(loggedUser);

            finalEntity = ratingService.update(finalEntity);

            return ResponseEntity.ok(entityDTO.fromModel(finalEntity));

        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{ratingID}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "recipeID") Integer recipeID, @PathVariable(name = "ratingID") Integer stepID, Principal principal){
        try {
            User loggedUser = userService.findByIdRaw(principal.getName()).orElseThrow();

            Recipe recipe = recipeService.findByIdRaw(recipeID).orElse(null);

            if (recipe == null){
                return ResponseEntity.notFound().build();
            }

            Rating entity = ratingService.findByIdRaw(stepID).orElse(null);

            if (entity == null){
                return ResponseEntity.notFound().build();
            }

            if ((entity.getUser() != null && Objects.equals(entity.getUser().getId(), loggedUser.getId())) || principal.getName().equals("root")){
                ratingService.deleteById(stepID);
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    protected Rating setRelations(Rating entity, RatingDTO dto) {
        if (dto.recipe() != null){
            recipeService.findByIdRaw(dto.recipe()).ifPresent(entity::setRecipe);
        }
        if (dto.author() != null){
            userService.findByIdRaw(dto.author()).ifPresent(entity::setUser);
        }

        return entity;
    }
}
