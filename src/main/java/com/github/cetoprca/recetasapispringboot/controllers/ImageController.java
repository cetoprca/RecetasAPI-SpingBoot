package com.github.cetoprca.recetasapispringboot.controllers;

import com.github.cetoprca.recetasapispringboot.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("api/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/file/{url:.+}")
    public ResponseEntity<Resource> getImageFile(@PathVariable String url) {
        try {
            Path ruta = Paths.get("images").resolve(url).normalize();

            if (!ruta.startsWith(Paths.get("images")))
                return ResponseEntity.badRequest().build(); // evita path traversal

            Resource resource = new UrlResource(ruta.toUri());

            if (!resource.exists() || !resource.isReadable())
                return ResponseEntity.notFound().build();

            String contentType = url.toLowerCase().endsWith(".png") ? "image/png" : "image/jpeg";

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + url + "\"")
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/db/{url}")
    public ResponseEntity<?> getImage(@PathVariable(name = "url") String url){
        try {
            return ResponseEntity.ok(imageService.getImage(url));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllImage(){
        try {
            return ResponseEntity.ok(imageService.findAll());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> postImage(@RequestParam("file") MultipartFile file){
        try {
            return ResponseEntity.ok(imageService.saveImage(file));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{url:.+}")
    public ResponseEntity<?> delete(@PathVariable String url) {
        try {
            imageService.deleteImage(url);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
