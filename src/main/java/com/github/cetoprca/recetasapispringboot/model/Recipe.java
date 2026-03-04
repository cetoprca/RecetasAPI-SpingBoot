package com.github.cetoprca.recetasapispringboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recipe")

@Data
@NoArgsConstructor @AllArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", unique = true)
    private String title;
    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

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
    private User user;

    @ManyToMany(mappedBy = "savedRecipes")
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "recipe")
    private Set<Rating> ratings = new HashSet<>();

    @OneToMany(mappedBy = "recipe")
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

    @PrePersist
    private void setCreationDate(){
        creationDate = LocalDate.now();
    }
}
