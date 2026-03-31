package com.github.cetoprca.recetasapispringboot.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Image {
    @Id
    private String url;

    @ManyToMany
    @JoinTable(
            name = "user_image",
            joinColumns = @JoinColumn(name = "image_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> usedInUsers = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "recipe_image",
            joinColumns = @JoinColumn(name = "image_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private Set<Recipe> usedInRecipes = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "step_image",
            joinColumns = @JoinColumn(name = "image_id"),
            inverseJoinColumns = @JoinColumn(name = "step_id")
    )
    private Set<Step> usedInSteps = new HashSet<>();
}
