package com.github.cetoprca.recetasapispringboot.service;

import com.github.cetoprca.recetasapispringboot.DTO.CuisineDTO;
import com.github.cetoprca.recetasapispringboot.DTO.IngredientDTO;
import com.github.cetoprca.recetasapispringboot.model.Cuisine;
import com.github.cetoprca.recetasapispringboot.model.Ingredient;
import com.github.cetoprca.recetasapispringboot.repository.CuisineRepository;
import com.github.cetoprca.recetasapispringboot.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientService extends GenericService<Ingredient, IngredientDTO>{

    @Autowired
    private IngredientRepository ingredientRepository;

    protected IngredientService(JpaRepository<Ingredient, Integer> repository) {
        super(repository);
    }

    @Override
    protected IngredientDTO toDTO(Ingredient entity) {
        return new IngredientDTO(entity);
    }

    public Optional<IngredientDTO> findByName(String name) {
        return ingredientRepository.findByName(name).map(this::toDTO);
    }

    public Optional<Ingredient> findByNameRaw(String name) {
        return ingredientRepository.findByName(name);
    }

}
