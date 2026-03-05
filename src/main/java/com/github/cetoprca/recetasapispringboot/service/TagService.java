package com.github.cetoprca.recetasapispringboot.service;

import com.github.cetoprca.recetasapispringboot.DTO.TagDTO;
import com.github.cetoprca.recetasapispringboot.model.Tag;
import com.github.cetoprca.recetasapispringboot.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagService extends GenericService<Tag, TagDTO>{

    @Autowired
    private TagRepository tagRepository;

    protected TagService(JpaRepository<Tag, Integer> repository) {
        super(repository);
    }

    @Override
    protected TagDTO toDTO(Tag entity) {
        return new TagDTO(entity);
    }

    public Optional<TagDTO> findByName(String name) {
        return tagRepository.findByName(name).map(this::toDTO);
    }

    public Optional<Tag> findByNameRaw(String name) {
        return tagRepository.findByName(name);
    }

}
