package com.github.cetoprca.recetasapispringboot.DTO;

import com.github.cetoprca.recetasapispringboot.model.Rating;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RatingCardDTO {
    Integer id;
    String title;
    String description;
    Integer stars;
    BasicUserDTO author;
    Integer recipe;

    public RatingCardDTO(Rating rating){
        this.id = rating.getId();
        this.title = rating.getTitle() == null ? "" : rating.getTitle();
        this.description = rating.getDescription() == null ? "" : rating.getDescription();
        this.stars = rating.getStars() == null ? 0 : rating.getStars();
        this.author = rating.getUser() == null ? null : new BasicUserDTO(rating.getUser());
        this.recipe = rating.getRecipe() == null ? -1 : rating.getRecipe().getId();
    }
}
