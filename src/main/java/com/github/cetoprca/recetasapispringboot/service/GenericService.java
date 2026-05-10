package com.github.cetoprca.recetasapispringboot.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class GenericService<T, D, ID> {

    protected final JpaRepository<T, ID> repository;

    protected GenericService(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    protected abstract D toDTO(T entity);

    public List<D> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public Optional<D> findById(ID id) {
        return repository.findById(id)
                .map(this::toDTO);
    }

    public Optional<T> findByIdRaw(ID id) {
        return repository.findById(id);
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public T update(T entity) {
        return save(entity);
    }

    public void delete(T entity) {
        repository.delete(entity);
    }

    public void deleteById(ID id) {
        repository.deleteById(id);
    }
}