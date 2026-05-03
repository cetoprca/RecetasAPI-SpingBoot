package com.github.cetoprca.recetasapispringboot.model;

import com.github.cetoprca.recetasapispringboot.DTO.ImageDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Image {
    @Id
    private String url;

    @OneToMany(mappedBy = "profilePicture")
    private Set<User> usedInUsers = new HashSet<>();

    @OneToMany(mappedBy = "image")
    private Set<Recipe> usedInRecipes = new HashSet<>();

    @OneToMany(mappedBy = "image")
    private Set<Step> usedInSteps = new HashSet<>();

    public Image(ImageDTO imageDTO) {
        this.url = imageDTO.url();
    }
}