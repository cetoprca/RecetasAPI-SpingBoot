package com.github.cetoprca.recetasapispringboot.service;

import com.github.cetoprca.recetasapispringboot.DTO.CuisineDTO;
import com.github.cetoprca.recetasapispringboot.DTO.TagDTO;
import com.github.cetoprca.recetasapispringboot.model.Cuisine;
import com.github.cetoprca.recetasapispringboot.model.Tag;
import com.github.cetoprca.recetasapispringboot.repository.CuisineRepository;
import com.github.cetoprca.recetasapispringboot.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CuisineService extends GenericService<Cuisine, CuisineDTO>{

    @Autowired
    private CuisineRepository cuisineRepository;

    protected CuisineService(JpaRepository<Cuisine, Integer> repository) {
        super(repository);
    }

    @Override
    protected CuisineDTO toDTO(Cuisine entity) {
        return new CuisineDTO(entity);
    }

    public Optional<CuisineDTO> findByName(String name) {
        return cuisineRepository.findByName(name).map(this::toDTO);
    }

    public Optional<Cuisine> findByNameRaw(String name) {
        return cuisineRepository.findByName(name);
    }

}
