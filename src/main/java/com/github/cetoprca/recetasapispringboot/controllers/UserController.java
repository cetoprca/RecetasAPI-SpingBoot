package com.github.cetoprca.recetasapispringboot.controllers;

import com.github.cetoprca.recetasapispringboot.DTO.CredentialsDTO;
import com.github.cetoprca.recetasapispringboot.DTO.UserDTO;
import com.github.cetoprca.recetasapispringboot.model.Rating;
import com.github.cetoprca.recetasapispringboot.model.Recipe;
import com.github.cetoprca.recetasapispringboot.model.User;
import com.github.cetoprca.recetasapispringboot.service.RatingService;
import com.github.cetoprca.recetasapispringboot.service.RecipeService;
import com.github.cetoprca.recetasapispringboot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController extends GenericController<User, UserDTO> {

    private final UserService userService;
    private final RatingService ratingService;
    private final RecipeService recipeService;

    public UserController(UserService userService, RatingService ratingService, RecipeService recipeService) {
        super(userService);
        this.userService = userService;
        this.ratingService = ratingService;
        this.recipeService = recipeService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CredentialsDTO credentialsDTO){
        try {

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashPassword = passwordEncoder.encode(credentialsDTO.password());

            User triedUser = userService.findByUsernameRaw(credentialsDTO.username()).orElse(null);

            if (triedUser != null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            User user = new User();
            user.setPassword(hashPassword);
            user.setUsername(credentialsDTO.username());

            user = userService.save(user);

            return ResponseEntity.ok(new UserDTO(user));
        }catch (Exception e){
//            throw new RuntimeException(e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @Override
    protected User setRelations(User entity, UserDTO dto) {
        List<Recipe> recipes = new ArrayList<>();
        List<Recipe> savedRecipes = new ArrayList<>();
        List<Rating> ratings = new ArrayList<>();

        if (dto.recipes() != null){
            dto.recipes().forEach(id -> {
                recipeService.findByIdRaw(id).ifPresent(recipes::add);
            });
        }

        if (dto.savedRecipes() != null){
            dto.savedRecipes().forEach(id -> {
                recipeService.findByIdRaw(id).ifPresent(savedRecipes::add);
            });
        }

        if (dto.ratings() != null) {
            dto.ratings().forEach(id -> {
                ratingService.findByIdRaw(id).ifPresent(ratings::add);
            });
        }

        entity.setRecipes(new HashSet<>(recipes));
        entity.setSavedRecipes(new HashSet<>(savedRecipes));
        entity.setRatings(new HashSet<>(ratings));

        return entity;
    }
}
