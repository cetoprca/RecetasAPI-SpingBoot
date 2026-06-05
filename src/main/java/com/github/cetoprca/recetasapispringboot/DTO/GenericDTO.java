package com.github.cetoprca.recetasapispringboot.DTO;

public interface GenericDTO<T, ID> {
    ID getId();
    GenericDTO<T, ID> fromModel(T entity);
    T toModel();
}
