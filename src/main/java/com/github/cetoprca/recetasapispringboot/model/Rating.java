package com.github.cetoprca.recetasapispringboot.model;

import com.github.cetoprca.recetasapispringboot.DTO.RatingDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rating")

@Getter
@Setter
@ToString
@NoArgsConstructor @AllArgsConstructor
public class Rating implements BaseModel<Rating, RatingDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "stars")
    private Integer stars;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Rating(RatingDTO ratingDTO) {
        this.id = ratingDTO.id();
        this.title = ratingDTO.title();
        this.description = ratingDTO.description();
        this.stars = ratingDTO.stars();
    }

    @Override
    public Rating mergeWith(Rating baseModel) {
        if (baseModel.getTitle() != null) this.title = baseModel.getTitle();
        if (baseModel.getDescription() != null) this.description = baseModel.getDescription();
        if (baseModel.getStars() != null) this.stars = baseModel.getStars();

        return this;
    }
}
