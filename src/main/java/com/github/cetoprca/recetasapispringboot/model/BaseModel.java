package com.github.cetoprca.recetasapispringboot.model;


public interface BaseModel<T, D> {
    T mergeWith(T baseModel);
}
