package com.github.cetoprca.recetasapispringboot.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Set;

public class ImageUtils {
    private static final Set<String> ALLOWED_TYPES = Set.of("image/jpeg", "image/png");
    public static void validateImage(MultipartFile file) throws Exception {
        if (file == null || file.isEmpty())
            throw new IllegalArgumentException("El archivo está vacío");

        String contentType = file.getContentType();
        if (contentType == null || !ALLOWED_TYPES.contains(contentType))
            throw new IllegalArgumentException("Solo se permiten PNG y JPG");

        if (!isImageByMagicBytes(file.getBytes()))
            throw new IllegalArgumentException("El archivo no es un PNG o JPG válido");
    }

    private static boolean isImageByMagicBytes(byte[] bytes) {
        if (bytes.length < 3) return false;

        // JPEG: FF D8 FF
        if (bytes[0] == (byte) 0xFF && bytes[1] == (byte) 0xD8 && bytes[2] == (byte) 0xFF)
            return true;

        // PNG: 89 50 4E 47
        return bytes.length >= 4 &&
                bytes[0] == (byte) 0x89 && bytes[1] == 0x50 && bytes[2] == 0x4E && bytes[3] == 0x47;
    }

    public static String genHash(MultipartFile file) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        try (InputStream is = file.getInputStream()) {
            byte[] buffer = new byte[8192];
            int bytesRead;

            while ((bytesRead = is.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }
        }

        byte[] hashBytes = digest.digest();

        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    }

    public static String getExtension(String nombre) {
        if (nombre == null || !nombre.contains(".")) return "bin";
        return nombre.substring(nombre.lastIndexOf(".") + 1);
    }
}
