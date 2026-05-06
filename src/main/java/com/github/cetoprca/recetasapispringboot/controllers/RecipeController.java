package com.github.cetoprca.recetasapispringboot.controllers;

import com.github.cetoprca.recetasapispringboot.DTO.FilterDTO;
import com.github.cetoprca.recetasapispringboot.DTO.RecipeCardDTO;
import com.github.cetoprca.recetasapispringboot.DTO.RecipeDTO;
import com.github.cetoprca.recetasapispringboot.model.*;
import com.github.cetoprca.recetasapispringboot.service.*;
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
import java.util.Objects;

@RestController
@RequestMapping("api/recipe")
public class RecipeController extends GenericController<Recipe, RecipeDTO> {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final RatingService ratingService;
    private final StepService stepService;
    private final CuisineService cuisineService;
    private final TagService tagService;
    private final UserService userService;
    private final ImageService imageService;

    public RecipeController(RecipeService recipeService, IngredientService ingredientService, RatingService ratingService, StepService stepService, CuisineService cuisineService, TagService tagService, UserService userService, ImageService imageService) {
        super(recipeService);
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.ratingService = ratingService;
        this.stepService = stepService;
        this.cuisineService = cuisineService;
        this.tagService = tagService;
        this.userService = userService;
        this.imageService = imageService;
    }

    @Override
    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null){
                throw new UserPrincipalNotFoundException(null);
            }

            User loggedUser = userService.findByUsernameRaw(authentication.getName()).orElseThrow();

            if (!loggedUser.getUsername().equals("root")){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (UserPrincipalNotFoundException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return null;
    }

    @GetMapping("/user/saved")
    public ResponseEntity<?> findSavedByUser(){
        try {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null){
                throw new UserPrincipalNotFoundException(null);
            }

            User loggedUser = userService.findByUsernameRaw(authentication.getName()).orElseThrow();

            FilterDTO filterDTO = FilterDTO.builder().author(loggedUser.getId()).build();

            return ResponseEntity.ok(recipeService.findByFilter(filterDTO).stream().map(RecipeCardDTO::new));

        }catch (Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @PostMapping("/filter")
    public ResponseEntity<?> findAll(@RequestBody FilterDTO filterDTO) {
        try {
            return ResponseEntity.ok(recipeService.findByFilter(filterDTO).stream().filter(Recipe::getIsPublic).map(RecipeCardDTO::new).toList());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{recipeID}")
    public ResponseEntity<?> findById(@PathVariable(name = "recipeID") Integer recipeID, Principal principal){
        try {

            boolean isAuthor = false;
            User loggedUser = userService.findByUsernameRaw(principal.getName()).orElseThrow();

            Recipe recipe = recipeService.findByIdRaw(recipeID).orElse(null);

            if (recipe == null){
                return ResponseEntity.notFound().build();
            }

            if (Objects.equals(loggedUser.getId(), recipe.getAuthor().getId())){
                isAuthor = true;
            }

            RecipeDTO recipeDTO = new RecipeDTO(recipe);

            if (recipeDTO.isPublic() || isAuthor){
                return ResponseEntity.ok(recipeDTO);
            }else {
                return ResponseEntity.ok().build();
            }

        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @Override
    @PatchMapping
    public ResponseEntity<?> update(@RequestBody RecipeDTO entityDTO) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null){
                throw new UserPrincipalNotFoundException(null);
            }

            User loggedUser = userService.findByUsernameRaw(authentication.getName()).orElseThrow();

            Recipe recipe = recipeService.findByIdRaw(entityDTO.id()).orElse(null);

            if (recipe == null){
                return ResponseEntity.notFound().build();
            }

            if (recipe.getAuthor() != loggedUser && !loggedUser.getUsername().equals("root")){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            return super.update(entityDTO);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


    @Override
    @DeleteMapping("/{recipeID}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "recipeID") Integer id) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null){
                throw new UserPrincipalNotFoundException(null);
            }

            User loggedUser = userService.findByUsernameRaw(authentication.getName()).orElseThrow();

            Recipe recipe = recipeService.findByIdRaw(id).orElse(null);

            if (recipe == null){
                return ResponseEntity.notFound().build();
            }

            if (recipe.getAuthor() != loggedUser && !loggedUser.getUsername().equals("root")){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            return super.deleteById(id);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @Override
    protected Recipe setRelations(Recipe entity, RecipeDTO dto) {
        List<Ingredient> ingredients = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        List<Step> steps = new ArrayList<>();
        List<Rating> ratings = new ArrayList<>();

        if (dto.ingredients() != null){
            dto.ingredients().forEach(id -> {
                ingredientService.findByIdRaw(id).ifPresent(ingredients::add);
            });
        }
        if (dto.tags() != null){
            dto.tags().forEach(id -> {
                tagService.findByIdRaw(id).ifPresent(tags::add);
            });
        }
        if (dto.steps() != null){
            dto.steps().forEach(id -> {
                stepService.findByIdRaw(id).ifPresent(steps::add);
            });
        }
        if (dto.ratings() != null){
            dto.ratings().forEach(id -> {
                ratingService.findByIdRaw(id).ifPresent(ratings::add);
            });
        }

        if (dto.author() != null){
            userService.findByIdRaw(dto.author()).ifPresent(entity::setAuthor);
        }
        if (dto.cuisine() != null){
            cuisineService.findByIdRaw(dto.cuisine()).ifPresent(entity::setCuisine);
        }
        if (dto.image() != null){
            imageService.findByIdRaw(dto.image()).ifPresent(entity::setImage);
        }

        entity.setIngredients(new HashSet<>(ingredients));
        entity.setTags(new HashSet<>(tags));
        entity.setSteps(new HashSet<>(steps));
        entity.setRatings(new HashSet<>(ratings));

        return entity;
    }
}
