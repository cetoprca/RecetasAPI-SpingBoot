package com.github.cetoprca.recetasapispringboot.controllers;

import com.github.cetoprca.recetasapispringboot.DTO.CredentialsDTO;
import com.github.cetoprca.recetasapispringboot.DTO.UserDTO;
import com.github.cetoprca.recetasapispringboot.model.Rating;
import com.github.cetoprca.recetasapispringboot.model.Recipe;
import com.github.cetoprca.recetasapispringboot.model.User;
import com.github.cetoprca.recetasapispringboot.service.ImageService;
import com.github.cetoprca.recetasapispringboot.service.RatingService;
import com.github.cetoprca.recetasapispringboot.service.RecipeService;
import com.github.cetoprca.recetasapispringboot.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final RatingService ratingService;
    private final RecipeService recipeService;
    private final ImageService imageService;

    public UserController(UserService userService, RatingService ratingService, RecipeService recipeService, ImageService imageService) {
        this.userService = userService;
        this.ratingService = ratingService;
        this.recipeService = recipeService;
        this.imageService = imageService;
    }

    @GetMapping("/{userID}")
    public ResponseEntity<?> findAll(@PathVariable(name = "userID") Integer id){
        try {

            UserDTO userDTO = userService.findById(id).orElse(null);

            if (userDTO == null){
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(userDTO);

        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/logged")
    public ResponseEntity<?> findLoggedUser(Principal principal){
        try {
            return ResponseEntity.ok(userService.findByUsername(principal.getName()).orElseThrow());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
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

    @PatchMapping
    public ResponseEntity<?> update(@RequestBody UserDTO userDTO, Principal principal){
        try {
            User loggedUser = userService.findByUsernameRaw(principal.getName()).orElseThrow();

            if (!loggedUser.getId().equals(userDTO.id())){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            User user = userDTO.toModel();
            setRelations(user, userDTO);
            User patchUser = userService.findByIdRaw(userDTO.id()).orElse(null);
            if (patchUser == null) return ResponseEntity.notFound().build();

            patchUser.mergeWith(user);

            patchUser = userService.update(patchUser);

            return ResponseEntity.ok(new UserDTO(patchUser));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{userID}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "userID") Integer id, Principal principal, HttpServletRequest request){
        try {
            User loggedUser = userService.findByUsernameRaw(principal.getName()).orElseThrow();

            if (!loggedUser.getId().equals(id)){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            User user = userService.findByIdRaw(id).orElse(null);
            if (user == null) return ResponseEntity.notFound().build();

            userService.deleteById(id);

            request.getSession().invalidate();

            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

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

        if (dto.profilePicturePath() != null){
            imageService.findByIdRaw(dto.profilePicturePath()).ifPresent(entity::setProfilePicture);
        }

        entity.setRecipes(new HashSet<>(recipes));
        entity.setSavedRecipes(new HashSet<>(savedRecipes));
        entity.setRatings(new HashSet<>(ratings));

        return entity;
    }
}
