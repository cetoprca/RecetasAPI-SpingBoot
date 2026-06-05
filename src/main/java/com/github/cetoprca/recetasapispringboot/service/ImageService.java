package com.github.cetoprca.recetasapispringboot.service;

import com.github.cetoprca.recetasapispringboot.DTO.ImageDTO;
import com.github.cetoprca.recetasapispringboot.model.Image;
import com.github.cetoprca.recetasapispringboot.repository.ImageRepository;
import com.github.cetoprca.recetasapispringboot.utils.ImageUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public List<ImageDTO> findAll(){
        return imageRepository.findAll().stream().map(ImageDTO::new).toList();
    }

    public Optional<Image> findByIdRaw(String url){
        return imageRepository.findById(url);
    }

    public Image saveImage(MultipartFile file) {
        try {
            ImageUtils.validateImage(file);

            String hash = ImageUtils.genHash(file);
            String extension = ImageUtils.getExtension(file.getOriginalFilename());
            String fileName = hash + "." + extension;
            Path ruta = Paths.get("images/" + fileName);

            if (Files.exists(ruta)) {
                return imageRepository.findById(fileName)
                        .orElseThrow(() -> new RuntimeException("Inconsistencia"));
            }

            Files.createDirectories(ruta.getParent());
            Files.write(ruta, file.getBytes());

            Image image = new Image();
            image.setUrl(fileName);

            return imageRepository.save(image);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error guardando imagen", e);
        }
    }

    public Optional<ImageDTO> getImage(String url){
        return imageRepository.findById(url).map(ImageDTO::new);
    }

    public void deleteImageIfUnused(String url) {
        if (imageRepository.countStepReferences(url) > 0) return;
        if (imageRepository.countRecipeReferences(url) > 0) return;
        if (imageRepository.countUserReferences(url) > 0) return;

        try {
            Path ruta = Paths.get("images").resolve(url).normalize();
            Files.deleteIfExists(ruta);
        } catch (Exception e) {
            throw new RuntimeException("Error borrando archivo", e);
        }

        imageRepository.deleteById(url);
    }

    @Transactional
    public void deleteImage(String url) {

        Image image = imageRepository.findById(url)
                .orElseThrow(() -> new RuntimeException("Imagen no encontrada"));

        try {
            Path ruta = Paths.get("images").resolve(url).normalize();
            Files.deleteIfExists(ruta);
        } catch (Exception e) {
            throw new RuntimeException("Error borrando archivo", e);
        }

        image.getUsedInUsers().clear();
        image.getUsedInRecipes().clear();
        image.getUsedInSteps().clear();

        imageRepository.delete(image);
    }
}
