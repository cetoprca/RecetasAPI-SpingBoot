package com.github.cetoprca.recetasapispringboot.model;

import com.github.cetoprca.recetasapispringboot.DTO.IngredientDTO;
import com.github.cetoprca.recetasapispringboot.DTO.TagDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ingredient")

@Data
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
