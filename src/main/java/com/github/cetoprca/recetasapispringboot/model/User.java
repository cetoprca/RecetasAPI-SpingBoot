package com.github.cetoprca.recetasapispringboot.model;

import com.github.cetoprca.recetasapispringboot.DTO.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")

@Getter @Setter
@ToString
@NoArgsConstructor @AllArgsConstructor
public class User implements BaseModel<User, UserDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "password")
    private String password;

    @Column(name = "biography")
    private String biography;

    @ManyToOne
    @JoinColumn(name = "profile_image_url")
    private Image profilePicture;

    @ManyToMany
    @JoinTable(
            name = "user_saved_recipe",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private Set<Recipe> savedRecipes = new HashSet<>();

    @OneToMany(mappedBy = "author")
    private Set<Recipe> recipes = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Rating> ratings = new HashSet<>();

    public User(UserDTO userDTO) {
        this.id = userDTO.id();
        this.username = userDTO.username();
        this.biography = userDTO.biography();
    }

    @Override
    public User mergeWith(User baseModel) {
        if (baseModel.getBiography() != null) this.biography = baseModel.getBiography();
        if (baseModel.getProfilePicture() != null) this.profilePicture = baseModel.getProfilePicture();
        if (baseModel.getRecipes() != null){
            for (Recipe recipe : baseModel.getRecipes()){
                if (this.recipes.contains(recipe)){
                    this.recipes.remove(recipe);
                }else{
                    this.recipes.add(recipe);
                }
            }
        }
        if (baseModel.getSavedRecipes() != null){
            for (Recipe recipe : baseModel.getSavedRecipes()){
                if (this.savedRecipes.contains(recipe)){
                    this.savedRecipes.remove(recipe);
                }else{
                    this.savedRecipes.add(recipe);
                }
            }
        }
        if (baseModel.getRatings() != null){
            for (Rating rating : baseModel.getRatings()){
                if (this.ratings.contains(rating)){
                    this.ratings.remove(rating);
                }else{
                    this.ratings.add(rating);
                }
            }
        }

        return this;
    }

}
