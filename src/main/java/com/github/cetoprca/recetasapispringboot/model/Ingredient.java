package com.github.cetoprca.recetasapispringboot.model;

import com.github.cetoprca.recetasapispringboot.DTO.IngredientDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ingredient")

@Getter
@Setter
@ToString
@NoArgsConstructor @AllArgsConstructor
public class Ingredient implements BaseModel<Ingredient, IngredientDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @ManyToMany(mappedBy = "ingredients")
    private Set<Recipe> recipes = new HashSet<>();

    public Ingredient(IngredientDTO ingredientDTO){
        this.id = ingredientDTO.id();
        this.name = ingredientDTO.name();
    }

    @Override
    public Ingredient mergeWith(Ingredient baseModel) {
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
