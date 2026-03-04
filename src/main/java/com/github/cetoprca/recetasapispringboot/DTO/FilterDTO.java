package com.github.cetoprca.recetasapispringboot.DTO;

import java.time.LocalDate;
import java.util.List;

public record FilterDTO(
        List<Integer> tags,
        List<Integer> ingredients,
        Integer author,
        Integer cuisine,
        Integer rating,
        LocalDate startDate,
        LocalDate endDate,
        Integer prepTime,
        Integer cookTime,
        Integer totalTime
        ) {
}
