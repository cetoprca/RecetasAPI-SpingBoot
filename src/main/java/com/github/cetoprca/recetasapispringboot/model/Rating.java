package com.github.cetoprca.recetasapispringboot.model;

import com.github.cetoprca.recetasapispringboot.DTO.RatingDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rating")

@Data
@NoArgsConstructor @AllArgsConstructor
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "description", unique = true)
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
}
