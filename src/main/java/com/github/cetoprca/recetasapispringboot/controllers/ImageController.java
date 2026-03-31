package com.github.cetoprca.recetasapispringboot.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/image")
public class ImageController {

    @GetMapping("/{url}")
    public ResponseEntity<?> getImage(@PathVariable(name = "url") String url){
        try {
            return ResponseEntity.ok("");
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> postImage(@PathVariable(name = "url") String url){
        try {
            return ResponseEntity.ok("");
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
