package com.example.pruebaapi;

import com.example.pruebaapi.domain.Publicacion;
import com.example.pruebaapi.dto.PublicacionRequestDTO;
import com.example.pruebaapi.dto.PublicacionResponseDTO;
import com.example.pruebaapi.service.PublicacionService;
import com.example.pruebaapi.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publicaciones")
public class PublicacionController {
    private final PublicacionService publicacionService;
    public PublicacionController(PublicacionService publicacionService) {
        this.publicacionService = publicacionService;
    }

    @PostMapping
    public Publicacion crearPublicacion(@Valid @RequestBody PublicacionRequestDTO publicacion) {
        return publicacionService.crearPublicacion(publicacion);
    }

    @GetMapping
    public List<PublicacionResponseDTO> getPublicaciones() {
        return publicacionService.obtenerTodasLasPublicaciones();
    }

    @GetMapping("/{id}")
    public PublicacionResponseDTO getPublicacionPorId(@PathVariable Long id) {
        return publicacionService.obtenerPublicacionPorId(id);
    }

    @PutMapping("/{id}")
    public PublicacionResponseDTO actualizarPublicacion(@PathVariable Long id, @Valid @RequestBody PublicacionRequestDTO requestDTO) {
        return publicacionService.actualizarPublicacion(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    public void borrarPublicacion(@PathVariable Long id) {
        publicacionService.borrarPublicacion(id);
    }
}
