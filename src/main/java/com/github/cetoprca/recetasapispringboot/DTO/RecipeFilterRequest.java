package com.github.cetoprca.recetasapispringboot.DTO;

public class RecipeFilterRequest {
    private FilterDTO filter;
    private PaginationDTO pagination;

    public FilterDTO getFilter() {
        return filter;
    }

    public void setFilter(FilterDTO filter) {
        this.filter = filter;
    }

    public PaginationDTO getPagination() {
        return pagination;
    }

    public void setPagination(PaginationDTO pagination) {
        this.pagination = pagination;
    }
}
