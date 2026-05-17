package com.github.cetoprca.recetasapispringboot.repository;

import com.github.cetoprca.recetasapispringboot.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ImageRepository extends JpaRepository<Image, String> {

    @Query(value = "SELECT COUNT(*) FROM step WHERE image_url = :url", nativeQuery = true)
    int countStepReferences(@Param("url") String url);

    @Query(value = "SELECT COUNT(*) FROM recipe WHERE image_url = :url", nativeQuery = true)
    int countRecipeReferences(@Param("url") String url);

    @Query(value = "SELECT COUNT(*) FROM user WHERE profile_image_url = :url OR banner_image_url = :url", nativeQuery = true)
    int countUserReferences(@Param("url") String url);
}
