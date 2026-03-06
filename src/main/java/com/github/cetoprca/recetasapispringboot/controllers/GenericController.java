package com.github.cetoprca.recetasapispringboot.controllers;

import com.github.cetoprca.recetasapispringboot.DTO.GenericDTO;
import com.github.cetoprca.recetasapispringboot.model.BaseModel;
import com.github.cetoprca.recetasapispringboot.service.GenericService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public abstract class GenericController<T extends BaseModel<T, D>, D extends GenericDTO<T>> {

    protected final GenericService<T, D> service;
    protected GenericController(GenericService<T, D> genericService){
        service = genericService;
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        try {

            return ResponseEntity.ok(service.findAll());

        }catch (Exception e){
            throw new RuntimeException(e);
//            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody D entityDTO){
        try {

            T entity = entityDTO.toModel();
            entity = setRelations(entity, entityDTO);

            entity = service.save(entity);

            return ResponseEntity.ok(entityDTO.fromModel(entity));

        }catch (Exception e){
            throw new RuntimeException(e);
//            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestBody D entityDTO){
        try {

            T entityA = entityDTO.toModel();
            T entityB = service.findByIdRaw(entityDTO.getId()).orElse(null);

            if (entityB == null){
                return ResponseEntity.notFound().build();
            }

            entityA = setRelations(entityA, entityDTO);

            T finalEntity = entityB.mergeWith(entityA);

            finalEntity = service.update(finalEntity);

            return ResponseEntity.ok(entityDTO.fromModel(finalEntity));

        }catch (Exception e){
            throw new RuntimeException(e);
//            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") Integer id){
        try {

            T entity = service.findByIdRaw(id).orElse(null);

            if (entity != null){
                service.deleteById(id);
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.notFound().build();

        }catch (Exception e){
            throw new RuntimeException(e);
//            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    protected abstract T setRelations(T entity, D dto);
}
