package com.github.cetoprca.recetasapispringboot.controllers;

import com.github.cetoprca.recetasapispringboot.DTO.CredentialsDTO;
import com.github.cetoprca.recetasapispringboot.DTO.PaginationDTO;
import com.github.cetoprca.recetasapispringboot.DTO.RecipeCardDTO;
import com.github.cetoprca.recetasapispringboot.DTO.UserDTO;
import com.github.cetoprca.recetasapispringboot.model.Rating;
import com.github.cetoprca.recetasapispringboot.model.Recipe;
import com.github.cetoprca.recetasapispringboot.model.User;
import com.github.cetoprca.recetasapispringboot.service.ImageService;
import com.github.cetoprca.recetasapispringboot.service.RatingService;
import com.github.cetoprca.recetasapispringboot.service.RecipeService;
import com.github.cetoprca.recetasapispringboot.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.security.Principal;
import java.util.*;

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
    public ResponseEntity<?> findAll(@PathVariable(name = "userID") String id){
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
            return ResponseEntity.ok(userService.findByIdRaw(principal.getName()).map(u -> new UserDTO(u)).orElseThrow());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CredentialsDTO credentialsDTO){
        try {

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashPassword = passwordEncoder.encode(credentialsDTO.password());

            User triedUser = userService.findByIdRaw(credentialsDTO.handle()).orElse(null);

            if (triedUser != null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            User user = new User();
            user.setId(credentialsDTO.handle());
            user.setDisplayName(credentialsDTO.displayName());
            user.setPassword(hashPassword);

            user = userService.save(user);

            return ResponseEntity.ok(new UserDTO(user));
        }catch (Exception e){
//            throw new RuntimeException(e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/follow/{userID}")
    public ResponseEntity<?> followUser(@PathVariable(name = "userID") String id, Principal principal){
        try {
            User loggedUser = userService.findByIdRaw(principal.getName()).orElseThrow();

            if (loggedUser.getId().equals(id)){
                return ResponseEntity.badRequest().body("You can't follow yourself");
            }

            Optional<User> userOpt = userService.findByIdRaw(id);
            if (!userOpt.isPresent()){
                return ResponseEntity.notFound().build();
            }
            User userToFollow = userOpt.get();

            System.out.println("Siguiendome: " + loggedUser.getFollowers().stream().map(User::getId).toList());
            System.out.println("Siugiendo: " + loggedUser.getFollowing().stream().map(User::getId).toList());

            System.out.println("Lo siguen: " + userToFollow.getFollowers().stream().map(User::getId).toList());
            System.out.println("Sigue: " + userToFollow.getFollowing().stream().map(User::getId).toList());



            Set<User> followedUsers = loggedUser.getFollowing();
            if(followedUsers.contains(userToFollow)) {
                followedUsers.remove(userToFollow);
            }else {
                followedUsers.add(userToFollow);
            }
            loggedUser.setFollowing(followedUsers);

            loggedUser = userService.save(loggedUser);

            return ResponseEntity.ok(new UserDTO(loggedUser));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestBody UserDTO userDTO, Principal principal){
        try {
            User loggedUser = userService.findByIdRaw(principal.getName()).orElseThrow();

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
    public ResponseEntity<?> deleteById(@PathVariable(name = "userID") String id, Principal principal, HttpServletRequest request){
        try {
            User loggedUser = userService.findByIdRaw(principal.getName()).orElseThrow();

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

    @PostMapping("/save/{recipeID}")
    public ResponseEntity<?> saveRecipe(@PathVariable(name = "recipeID") Integer recipeID, Principal principal){
        try {
            System.out.println("userController save recipe");
            if (principal == null || principal.getName() == null){
                return ResponseEntity.internalServerError().body("No user logged");
            }

            User user = userService.findByIdRaw(principal.getName()).get();

            Optional<Recipe> recipeOpt = recipeService.findByIdRaw(recipeID);
            if (!recipeOpt.isPresent()){
                return ResponseEntity.badRequest().body("Recipe not found");
            }
            Recipe recipe = recipeOpt.get();

            Set<Recipe> savedRecipes = user.getSavedRecipes();
            if(savedRecipes.contains(recipe)) {
                savedRecipes.remove(recipe);
            }else {
                savedRecipes.add(recipe);
            }
            user.setSavedRecipes(savedRecipes);

            user = userService.save(user);

            return ResponseEntity.ok(new UserDTO(user));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/savedRecipes")
    public ResponseEntity<?> findSavedByUser(@RequestBody(required = false) PaginationDTO pagination,
                                              Principal principal) {
        try {
            if (principal == null || principal.getName() == null){
                throw new UserPrincipalNotFoundException(null);
            }

            User loggedUser = userService.findByIdRaw(principal.getName()).orElseThrow();

            int page = pagination != null ? pagination.page() : 0;
            int size = pagination != null ? pagination.size() : 20;
            Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "creationDate"));

            Page<RecipeCardDTO> recipePage = recipeService.findSavedRecipesByUserId(loggedUser.getId(), pageable)
                .map(recipe -> {
                    RecipeCardDTO cardDTO = new RecipeCardDTO(recipe);
                    cardDTO.setIsSaved(true);
                    return cardDTO;
                });

            return ResponseEntity.ok(recipePage);

        }catch (Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
    }

    protected User setRelations(User entity, UserDTO dto) {
        List<Recipe> recipes = new ArrayList<>();
        List<Recipe> savedRecipes = new ArrayList<>();
        List<Rating> ratings = new ArrayList<>();
        List<User> following = new ArrayList<>();

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

        if (dto.following() != null){
            dto.following().forEach(id -> {
                userService.findByIdRaw(id).ifPresent(following::add);
            });
        }

        if (dto.profilePicturePath() != null){
            imageService.findByIdRaw(dto.profilePicturePath()).ifPresent(entity::setProfilePicture);
        }

        entity.setRecipes(new HashSet<>(recipes));
        entity.setSavedRecipes(new HashSet<>(savedRecipes));
        entity.setRatings(new HashSet<>(ratings));
        entity.setFollowing(new HashSet<>(following));

        return entity;
    }
}
