package com.github.cetoprca.recetasapispringboot.model;

import com.github.cetoprca.recetasapispringboot.DTO.CuisineDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cuisine")

@Getter
@Setter
@ToString
@NoArgsConstructor @AllArgsConstructor
public class Cuisine implements BaseModel<Cuisine, CuisineDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "cuisine")
    private Set<Recipe> recipes = new HashSet<>();

    public Cuisine(CuisineDTO cuisineDTO){
        this.id = cuisineDTO.id();
        this.name = cuisineDTO.name();
    }

    @Override
    public Cuisine mergeWith(Cuisine baseModel) {
        if (baseModel.getName() != null) this.name = baseModel.getName();
        if (baseModel.getRecipes() != null){
            for (Recipe recipe : baseModel.getRecipes()){
                if (this.recipes.contains(recipe)){
                    this.recipes.remove(recipe);
                }else{
                    this.recipes.add(recipe);
                }
            }
        }

        return this;
    }
}
