package com.github.cetoprca.recetasapispringboot.controllers;

import com.github.cetoprca.recetasapispringboot.DTO.FilterDTO;
import com.github.cetoprca.recetasapispringboot.DTO.RecipeDTO;
import com.github.cetoprca.recetasapispringboot.model.*;
import com.github.cetoprca.recetasapispringboot.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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

    public RecipeController(RecipeService recipeService, IngredientService ingredientService, RatingService ratingService, StepService stepService, CuisineService cuisineService, TagService tagService, UserService userService) {
        super(recipeService);
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.ratingService = ratingService;
        this.stepService = stepService;
        this.cuisineService = cuisineService;
        this.tagService = tagService;
        this.userService = userService;
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

    @PostMapping("/filter")
    public ResponseEntity<?> findAll(@RequestBody FilterDTO filterDTO) {
        try {

            return ResponseEntity.ok(recipeService.findByFilter(filterDTO).stream().filter(RecipeDTO::isPublic));

        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{recipeID}")
    public ResponseEntity<?> findById(@PathVariable(name = "recipeID") Integer recipeID){
        try {

            RecipeDTO recipeDTO = recipeService.findById(recipeID).orElse(null);

            if (recipeDTO == null){
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(recipeDTO);

        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/user/{userID}")
    public ResponseEntity<?> findByUser(@PathVariable(name = "userID") Integer userID){
        try {

            User user = userService.findByIdRaw(userID).orElse(null);

            if (user == null){
                return ResponseEntity.notFound().build();
            }

<<<<<<< HEAD
            return ResponseEntity.ok(user.getRecipes().stream().map(RecipeDTO::new).toList());
=======
            isAuthor = loggedUser == user;

            List<RecipeDTO> userRecipes = recipeService.findByFilter(FilterDTO.builder().author(userID).build()).stream().filter(recipe -> recipe.isPublic() || isAuthor).toList();

            return ResponseEntity.ok(userRecipes);
>>>>>>> bd8fec1 (added search filter to recipes)

        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/user/saved")
    public ResponseEntity<?> findBySavedByUser(@PathVariable(name = "userID") Integer userID){
        try {

<<<<<<< HEAD
            User user = userService.findByIdRaw(userID).orElse(null);

            if (user == null){
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(user.getSavedRecipes().stream().map(RecipeDTO::new).toList());
=======
            return ResponseEntity.ok(loggedUser.getSavedRecipes().stream().map(RecipeDTO::new).toList());
>>>>>>> bd8fec1 (added search filter to recipes)

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

        entity.setIngredients(new HashSet<>(ingredients));
        entity.setTags(new HashSet<>(tags));
        entity.setSteps(new HashSet<>(steps));
        entity.setRatings(new HashSet<>(ratings));

        return entity;
    }
}
