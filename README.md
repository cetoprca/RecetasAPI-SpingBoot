# RecetasAPI-SpringBoot

Backend de la aplicación Rataouille.net, una red social de recetas de cocina.
API RESTful desarrollada con Spring Boot 4.0.3 y Java 25.

**Tecnologías:** Spring Boot, Spring Security, Spring Data JPA, MySQL, Maven

**Funcionalidades:**

- CRUD completo de recetas, usuarios, valoraciones, pasos, tags e ingredientes
- Autenticación por sesiones HTTP con cookies HTTP-only y BCrypt
- Filtrado dinámico de recetas con JPA Specifications
- Subida y deduplicación de imágenes mediante hash SHA-256
- Borrado en cascada de recetas con limpieza de imágenes

**Arranque con repo RecetasDeploy-DOCKER:**

Despliegue de desarrollo:
```bash
cd RecetasDeploy-DOCKER
docker compose -f compose.dev.yml up
```

Despliegue de produccion:
```bash
cd RecetasDeploy-DOCKER
docker compose -f compose.prod.yml up
```

Memoria: https://drive.google.com/file/d/15Nng71Sbz7vqwplNdlQJdKne_hKmav5l/view?usp=sharing
