package com.github.cetoprca.recetasapispringboot.DTO;

import com.github.cetoprca.recetasapispringboot.model.Rating;

public record RatingDTO(
        Integer id,
        String title,
        String description,
        Integer stars,
        String author,
        Integer recipe
) implements GenericDTO<Rating, Integer> {
    public RatingDTO(Rating rating){
        this(
                rating.getId(),
                rating.getTitle() == null ? "" : rating.getTitle(),
                rating.getDescription() == null ? "" : rating.getDescription(),
                rating.getStars() == null ? 0 : rating.getStars(),
                rating.getUser() == null ? "" : rating.getUser().getId(),
                rating.getRecipe() == null ? -1 : rating.getRecipe().getId()
        );
    }
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public GenericDTO<Rating, Integer> fromModel(Rating entity) {
        return new RatingDTO(entity);
    }

    @Override
    public Rating toModel() {
        return new Rating(this);
    }
}
