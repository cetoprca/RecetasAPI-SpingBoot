package com.github.cetoprca.recetasapispringboot.service;

import com.github.cetoprca.recetasapispringboot.DTO.RatingDTO;
import com.github.cetoprca.recetasapispringboot.model.Rating;
import com.github.cetoprca.recetasapispringboot.model.Recipe;
import com.github.cetoprca.recetasapispringboot.model.User;
import com.github.cetoprca.recetasapispringboot.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingService extends GenericService<Rating, RatingDTO> {

    @Autowired
    private RatingRepository ratingRepository;

    protected RatingService(JpaRepository<Rating, Integer> repository) {
        super(repository);
    }

    @Override
    protected RatingDTO toDTO(Rating entity) {
        return new RatingDTO(entity);
    }

    public Optional<RatingDTO> findByRecipe(Recipe recipe){
        return ratingRepository.findByRecipe(recipe).map(this::toDTO);
    }
    public Optional<RatingDTO> findByRecipeID(Integer recipeId){
        return ratingRepository.findByRecipe_id(recipeId).map(this::toDTO);
    }

    public Optional<RatingDTO> findByUser(User user){
        return ratingRepository.findByUser(user).map(this::toDTO);
    }
    public Optional<RatingDTO> findByUserId(Integer userId){
        return ratingRepository.findByUser_id(userId).map(this::toDTO);
    }

}
