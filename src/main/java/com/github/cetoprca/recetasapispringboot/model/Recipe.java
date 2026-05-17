package com.github.cetoprca.recetasapispringboot.model;

import com.github.cetoprca.recetasapispringboot.DTO.RecipeDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recipe")

@Getter
@Setter
@ToString
@NoArgsConstructor @AllArgsConstructor
public class Recipe implements BaseModel<Recipe, RecipeDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", unique = true)
    private String title;
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "image_url")
    private Image image;

    @Column(name = "prepTime")
    private Integer prepTime;

    @Column(name = "cookTime")
    private Integer cookTime;

    @Column(name = "totalTime")
    private Integer totalTime;

    @Column(name = "isPublic")
    private Boolean isPublic;

    @Column(name = "creationDate")
    private LocalDate creationDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Rating> ratings = new HashSet<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("position ASC")
    private Set<Step> steps = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "recipe_tag",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "cuisine_id", nullable = false)
    private Cuisine cuisine;

    @ManyToMany
    @JoinTable(
            name = "recipe_ingredient",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private Set<Ingredient> ingredients = new HashSet<>();

    public Recipe(RecipeDTO recipeDTO) {
        this.id = recipeDTO.id();
        this.title = recipeDTO.title();
        this.description = recipeDTO.description();
        this.prepTime = recipeDTO.prepTime();
        this.cookTime = recipeDTO.cookTime();
        this.totalTime = recipeDTO.totalTime();
        this.isPublic = recipeDTO.isPublic();
        this.creationDate = recipeDTO.creationDate();
    }

    @PrePersist
    private void setCreationDate(){
        creationDate = LocalDate.now();
    }

    @Override
    public Recipe mergeWith(Recipe baseModel) {
        if (baseModel.getTitle() != null) this.title = baseModel.getTitle();
        if (baseModel.getDescription() != null) this.description = baseModel.getDescription();
        if (baseModel.getImage() != null) this.image = baseModel.getImage();
        if (baseModel.getPrepTime() != null) this.prepTime = baseModel.getPrepTime();
        if (baseModel.getCookTime() != null) this.cookTime = baseModel.getCookTime();
        if (baseModel.getTotalTime() != null) this.totalTime = baseModel.getTotalTime();
        if (baseModel.getIsPublic() != null) this.isPublic = baseModel.getIsPublic();
        if (baseModel.getAuthor() != null) this.author = baseModel.getAuthor();
        if (baseModel.getCuisine() != null) this.cuisine = baseModel.getCuisine();

        if (baseModel.getIngredients() != null){
            for (Ingredient ingredient : baseModel.getIngredients()){
                if (this.ingredients.contains(ingredient)){
                    this.ingredients.remove(ingredient);
                }else{
                    this.ingredients.add(ingredient);
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
        if (baseModel.getTags() != null){
            for (Tag tag : baseModel.getTags()){
                if (this.tags.contains(tag)){
                    this.tags.remove(tag);
                }else{
                    this.tags.add(tag);
                }
            }
        }

        return this;
    }
}
