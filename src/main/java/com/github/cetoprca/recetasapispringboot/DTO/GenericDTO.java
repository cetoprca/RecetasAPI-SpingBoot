package com.github.cetoprca.recetasapispringboot.DTO;

public interface GenericDTO<T> {
    Integer getId();
    GenericDTO<T> fromModel(T entity);
    T toModel();
}
