package com.github.cetoprca.recetasapispringboot.DTO;

import com.github.cetoprca.recetasapispringboot.model.User;

public record BasicUserDTO(
        String id,
        String displayName,
        String profilePicturePath
) implements GenericDTO<User, String> {
    public BasicUserDTO(User user){
        this(
                user.getId(),
                user.getDisplayName(),
                user.getProfilePicture() == null ? "placeholder" : user.getProfilePicture().getUrl()
                );
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public GenericDTO<User, String> fromModel(User entity) {
        return new BasicUserDTO(entity);
    }

    @Override
    public User toModel() {
        return null;
    }
}
