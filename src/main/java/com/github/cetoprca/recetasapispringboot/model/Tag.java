package com.github.cetoprca.recetasapispringboot.model;

import com.github.cetoprca.recetasapispringboot.DTO.TagDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tag")

@Getter
@Setter
@ToString
@NoArgsConstructor @AllArgsConstructor
public class Tag implements BaseModel<Tag, TagDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Recipe> recipes = new HashSet<>();

    public Tag(TagDTO tagDTO){
        this.id = tagDTO.id();
        this.name = tagDTO.name();
    }

    @Override
    public Tag mergeWith(Tag baseModel) {
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
