package com.github.cetoprca.recetasapispringboot.service;

import com.github.cetoprca.recetasapispringboot.DTO.UserDTO;
import com.github.cetoprca.recetasapispringboot.model.User;
import com.github.cetoprca.recetasapispringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends GenericService<User, UserDTO> {

    @Autowired
    private UserRepository userRepository;

    protected UserService(JpaRepository<User, Integer> repository) {
        super(repository);
    }

    @Override
    protected UserDTO toDTO(User entity) {
        return new UserDTO(entity);
    }

    public Optional<UserDTO> findByUsername(String username) {
        return userRepository.findByUsername(username).map(this::toDTO);
    }

    public Optional<User> findByUsernameRaw(String username) {
        return userRepository.findByUsername(username);
    }



}
