package com.github.cetoprca.recetasapispringboot.DTO;

public record CredentialsDTO(
        String handle,
        String password,
        String displayName
) {
}
