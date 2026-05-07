package com.github.cetoprca.recetasapispringboot.DTO;

public record PaginationDTO(Integer page, Integer size) {
    public PaginationDTO {
        if (page == null || page < 0) page = 0;
        if (size == null || size < 1) size = 20;
    }
}
