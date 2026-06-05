package com.github.cetoprca.recetasapispringboot.repository.filters;

import com.github.cetoprca.recetasapispringboot.DTO.FilterDTO;
import com.github.cetoprca.recetasapispringboot.model.Ingredient;
import com.github.cetoprca.recetasapispringboot.model.Rating;
import com.github.cetoprca.recetasapispringboot.model.Recipe;
import com.github.cetoprca.recetasapispringboot.model.Tag;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class RecipeSpec {

    public static Specification<Recipe> conFiltro(FilterDTO filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // ── Autor ──────────────────────────────────────────────
            if (filtro.author() != null) {
                predicates.add(cb.equal(root.get("author").get("id"), filtro.author()));
            }

            // ── Cocina ─────────────────────────────────────────────
            if (filtro.cuisine() != null) {
                predicates.add(cb.equal(root.get("cuisine").get("id"), filtro.cuisine()));
            }

            // ── Fecha de creación ──────────────────────────────────
            if (filtro.creationDate() != null) {
                predicates.add(cb.equal(root.get("creationDate"), filtro.creationDate()));
            }

            // ── Prep time ──────────────────────────────────────────
            if (filtro.prepTime() != null) {
                boolean exact = Boolean.TRUE.equals(filtro.exactPrepTime());
                predicates.add(exact
                        ? cb.equal(root.get("prepTime"), filtro.prepTime())
                        : cb.lessThanOrEqualTo(root.get("prepTime"), filtro.prepTime())
                );
            }

            // ── Cook time ──────────────────────────────────────────
            if (filtro.cookTime() != null) {
                boolean exact = Boolean.TRUE.equals(filtro.exactCookTime());
                predicates.add(exact
                        ? cb.equal(root.get("cookTime"), filtro.cookTime())
                        : cb.lessThanOrEqualTo(root.get("cookTime"), filtro.cookTime())
                );
            }

            // ── Total time ─────────────────────────────────────────
            if (filtro.totalTime() != null) {
                boolean exact = Boolean.TRUE.equals(filtro.exactTotalTime());
                predicates.add(exact
                        ? cb.equal(root.get("totalTime"), filtro.totalTime())
                        : cb.lessThanOrEqualTo(root.get("totalTime"), filtro.totalTime())
                );
            }

            // ── Rating (subquery sobre avg de stars) ───────────────
            if (filtro.rating() != null) {
                boolean exact = Boolean.TRUE.equals(filtro.exactRating());

                Subquery<Double> avgSubquery = query.subquery(Double.class);
                Root<Rating> ratingRoot = avgSubquery.from(Rating.class);
                avgSubquery
                        .select(cb.avg(ratingRoot.get("stars")))
                        .where(cb.equal(ratingRoot.get("recipe"), root));

                predicates.add(exact
                        ? cb.equal(avgSubquery, filtro.rating().doubleValue())
                        : cb.greaterThanOrEqualTo(avgSubquery, filtro.rating().doubleValue())
                );
            }

            // ── Tags (debe tener TODOS los tags) ───────────────────
            if (filtro.tags() != null && !filtro.tags().isEmpty()) {
                filtro.tags().forEach(tagId -> {
                    Join<Recipe, Tag> join = root.join("tags", JoinType.INNER);
                    predicates.add(cb.equal(join.get("id"), tagId));
                });
                query.distinct(true);
            }

            // ── Ingredients (debe tener TODOS los ingredientes) ────
            if (filtro.ingredients() != null && !filtro.ingredients().isEmpty()) {
                filtro.ingredients().forEach(ingId -> {
                    Join<Recipe, Ingredient> join = root.join("ingredients", JoinType.INNER);
                    predicates.add(cb.equal(join.get("id"), ingId));
                });
                query.distinct(true);
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}