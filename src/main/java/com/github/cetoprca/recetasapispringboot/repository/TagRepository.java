package com.github.cetoprca.recetasapispringboot.repository;

import com.github.cetoprca.recetasapispringboot.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    Optional<Tag> findByName(String name);

}
