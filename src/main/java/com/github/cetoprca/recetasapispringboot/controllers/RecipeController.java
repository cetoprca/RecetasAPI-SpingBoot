package com.github.cetoprca.recetasapispringboot.controllers;

import com.github.cetoprca.recetasapispringboot.DTO.FilterDTO;
import com.github.cetoprca.recetasapispringboot.DTO.PaginationDTO;
import com.github.cetoprca.recetasapispringboot.DTO.RecipeCardDTO;
import com.github.cetoprca.recetasapispringboot.DTO.RecipeDTO;
import com.github.cetoprca.recetasapispringboot.DTO.RecipeFilterRequest;
import com.github.cetoprca.recetasapispringboot.DTO.UserDTO;
import com.github.cetoprca.recetasapispringboot.model.*;
import com.github.cetoprca.recetasapispringboot.repository.filters.RecipeSpec;
import com.github.cetoprca.recetasapispringboot.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("api/recipe")
public class RecipeController extends GenericController<Recipe, RecipeDTO, Integer> {

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

            User loggedUser = userService.findByIdRaw(authentication.getName()).orElseThrow();

            if (!loggedUser.getDisplayName().equals("root")){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (UserPrincipalNotFoundException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return null;
    }

    @PostMapping("/filter")
    public ResponseEntity<?> findAll(@RequestBody RecipeFilterRequest request, Principal principal) {
        try {
            if (principal == null || principal.getName() == null){
                return ResponseEntity.internalServerError().body("No user logged");
            }

            User userLogged = userService.findByIdRaw(principal.getName()).get();

            FilterDTO filterDTO = request.getFilter();
            PaginationDTO paginationDTO = request.getPagination();

            int page = paginationDTO != null ? paginationDTO.page() : 0;
            int size = paginationDTO != null ? paginationDTO.size() : 20;
            Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "creationDate"));

            Specification<Recipe> privacySpec = (root, query, cb) ->
                cb.or(
                    cb.isTrue(root.get("isPublic")),
                    cb.equal(root.get("author"), userLogged)
                );

            Specification<Recipe> spec = privacySpec;
            if (filterDTO != null) {
                spec = RecipeSpec.conFiltro(filterDTO).and(privacySpec);
            }

            Page<RecipeCardDTO> recipePage = recipeService.findByFilter(spec, pageable)
                .map(recipe -> {
                    boolean saved = userLogged.getSavedRecipes().contains(recipe);
                    RecipeCardDTO cardDTO = new RecipeCardDTO(recipe);
                    cardDTO.setIsSaved(saved);
                    return cardDTO;
                });

            return ResponseEntity.ok(recipePage);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/byUser/{userId}")
    public ResponseEntity<?> findByUserId(@PathVariable String userId,
                                           @RequestBody(required = false) PaginationDTO pagination,
                                           Principal principal) {
        try {
            if (principal == null || principal.getName() == null){
                return ResponseEntity.internalServerError().body("No user logged");
            }

            User userLogged = userService.findByIdRaw(principal.getName()).get();

            int page = pagination != null ? pagination.page() : 0;
            int size = pagination != null ? pagination.size() : 20;
            Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "creationDate"));

            Specification<Recipe> privacySpec = (root, query, cb) ->
                cb.or(
                    cb.isTrue(root.get("isPublic")),
                    cb.equal(root.get("author"), userLogged)
                );

            Specification<Recipe> spec = privacySpec
                .and((root, query, cb) -> cb.equal(root.get("author").get("id"), userId));

            Page<RecipeCardDTO> recipePage = recipeService.findByFilter(spec, pageable)
                .map(recipe -> {
                    boolean saved = userLogged.getSavedRecipes().contains(recipe);
                    RecipeCardDTO cardDTO = new RecipeCardDTO(recipe);
                    cardDTO.setIsSaved(saved);
                    return cardDTO;
                });

            return ResponseEntity.ok(recipePage);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{recipeID}")
    public ResponseEntity<?> findById(@PathVariable(name = "recipeID") Integer recipeID, Principal principal){
        try {

            boolean isAuthor = false;
            User loggedUser = userService.findByIdRaw(principal.getName()).orElseThrow();

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

    @GetMapping("/card/{recipeID}")
    public ResponseEntity<?> findCardById(@PathVariable(name = "recipeID") Integer recipeID, Principal principal){
        try {

            boolean isAuthor = false;
            User loggedUser = userService.findByIdRaw(principal.getName()).orElseThrow();

            Recipe recipe = recipeService.findByIdRaw(recipeID).orElse(null);

            if (recipe == null){
                return ResponseEntity.notFound().build();
            }

            if (Objects.equals(loggedUser.getId(), recipe.getAuthor().getId())){
                isAuthor = true;
            }

            RecipeCardDTO recipeDTO = new RecipeCardDTO(recipe);

            if (loggedUser.getSavedRecipes().contains(recipe)) recipeDTO.setIsSaved(true);

            if (recipe.getIsPublic() || isAuthor){
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

            User loggedUser = userService.findByIdRaw(authentication.getName()).orElseThrow();

            Recipe recipe = recipeService.findByIdRaw(entityDTO.id()).orElse(null);


            if (recipe == null){
                return ResponseEntity.notFound().build();
            }

            if (recipe.getAuthor() != loggedUser && !loggedUser.getDisplayName().equals("root")){
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

            User loggedUser = userService.findByIdRaw(authentication.getName()).orElseThrow();

            Recipe recipe = recipeService.findByIdRaw(id).orElse(null);

            if (recipe == null){
                return ResponseEntity.notFound().build();
            }

            if (recipe.getAuthor() != loggedUser && !loggedUser.getDisplayName().equals("root")){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            Set<String> imageUrls = new HashSet<>();
            if (recipe.getImage() != null) imageUrls.add(recipe.getImage().getUrl());
            for (Step step : recipe.getSteps()) {
                if (step.getImage() != null) imageUrls.add(step.getImage().getUrl());
            }

            recipeService.deleteSavedRecipeReferences(id);
            recipeService.deleteById(id);

            for (String url : imageUrls) {
                imageService.deleteImageIfUnused(url);
            }

            return ResponseEntity.noContent().build();
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
