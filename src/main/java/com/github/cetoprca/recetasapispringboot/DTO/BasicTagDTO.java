package com.github.cetoprca.recetasapispringboot.DTO;

import com.github.cetoprca.recetasapispringboot.model.Tag;

public record BasicTagDTO(
        Integer id,
        String name
) implements GenericDTO<Tag, Integer>{
    public BasicTagDTO(Tag tag){
        this(
                tag.getId(),
                tag.getName() == null ? "" : tag.getName()
        );
    }
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public GenericDTO<Tag, Integer> fromModel(Tag entity) {
        return new BasicTagDTO(entity);
    }

    @Override
    public Tag toModel() {
        return null;
    }
}
