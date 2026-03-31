package com.github.cetoprca.recetasapispringboot.DTO;

import java.time.LocalDate;
import java.util.List;

public record FilterDTO(
        List<Integer> tags,
        List<Integer> ingredients,
        Integer author,
        Integer cuisine,
        Integer rating,
        Boolean exactRating,
        LocalDate creationDate,
        Integer prepTime,
        Boolean exactPrepTime,
        Integer cookTime,
        Boolean exactCookTime,
        Integer totalTime,
        Boolean exactTotalTime
) {
        public static Builder builder() {
                return new Builder();
        }

        public static class Builder {
                private List<Integer> tags;
                private List<Integer> ingredients;
                private Integer author;
                private Integer cuisine;
                private Integer rating;
                private Boolean exactRating;
                private LocalDate creationDate;
                private Integer prepTime;
                private Boolean exactPrepTime;
                private Integer cookTime;
                private Boolean exactCookTime;
                private Integer totalTime;
                private Boolean exactTotalTime;

                public Builder tags(List<Integer> tags) {
                        this.tags = tags;
                        return this;
                }

                public Builder ingredients(List<Integer> ingredients) {
                        this.ingredients = ingredients;
                        return this;
                }

                public Builder author(Integer author) {
                        this.author = author;
                        return this;
                }

                public Builder cuisine(Integer cuisine) {
                        this.cuisine = cuisine;
                        return this;
                }

                public Builder rating(Integer rating) {
                        this.rating = rating;
                        return this;
                }

                public Builder exactRating(Boolean exactRating) {
                        this.exactRating = exactRating;
                        return this;
                }

                public Builder creationDate(LocalDate creationDate) {
                        this.creationDate = creationDate;
                        return this;
                }

                public Builder prepTime(Integer prepTime) {
                        this.prepTime = prepTime;
                        return this;
                }

                public Builder exactPrepTime(Boolean exactPrepTime) {
                        this.exactPrepTime = exactPrepTime;
                        return this;
                }

                public Builder cookTime(Integer cookTime) {
                        this.cookTime = cookTime;
                        return this;
                }

                public Builder exactCookTime(Boolean exactCookTime) {
                        this.exactCookTime = exactCookTime;
                        return this;
                }

                public Builder totalTime(Integer totalTime) {
                        this.totalTime = totalTime;
                        return this;
                }

                public Builder exactTotalTime(Boolean exactTotalTime) {
                        this.exactTotalTime = exactTotalTime;
                        return this;
                }

                public FilterDTO build() {
                        return new FilterDTO(
                                tags, ingredients, author, cuisine,
                                rating, exactRating, creationDate,
                                prepTime, exactPrepTime,
                                cookTime, exactCookTime,
                                totalTime, exactTotalTime
                        );
                }
        }
}