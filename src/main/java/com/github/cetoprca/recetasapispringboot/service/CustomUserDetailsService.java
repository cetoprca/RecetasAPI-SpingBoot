package com.github.cetoprca.recetasapispringboot.service;

import com.github.cetoprca.recetasapispringboot.model.User;
import com.github.cetoprca.recetasapispringboot.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String handle)
            throws UsernameNotFoundException {

        User user = userRepository.findById(handle)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getId())
                .password(user.getPassword())
                .build();
    }
}