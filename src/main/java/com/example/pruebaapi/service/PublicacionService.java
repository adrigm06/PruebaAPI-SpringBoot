package com.example.pruebaapi.service;

import com.example.pruebaapi.domain.Publicacion;
import com.example.pruebaapi.domain.Usuario;
import com.example.pruebaapi.dto.AutorResponseDTO;
import com.example.pruebaapi.dto.PublicacionRequestDTO;
import com.example.pruebaapi.dto.PublicacionResponseDTO;
import com.example.pruebaapi.exception.ResourceNotFoundException;
import com.example.pruebaapi.repository.PublicacionRepository;
import com.example.pruebaapi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublicacionService {
    private final PublicacionRepository publicacionRepository;
    private final UsuarioRepository usuarioRepository;
    public PublicacionService(PublicacionRepository publicacionRepository, UsuarioRepository usuarioRepository) {
        this.publicacionRepository = publicacionRepository;
        this.usuarioRepository = usuarioRepository;
    }
    public Publicacion crearPublicacion(PublicacionRequestDTO requestDTO) {
        Long usuarioId = requestDTO.getUsuarioId();
        Usuario autor = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + usuarioId));

        Publicacion publicacion = new Publicacion();
        publicacion.setTitulo(requestDTO.getTitulo());
        publicacion.setUsuario(autor);
        publicacion.setContenido(requestDTO.getContenido());
        return publicacionRepository.save(publicacion);
    }

    private PublicacionResponseDTO convertirADTO(Publicacion publicacion) {
        PublicacionResponseDTO dto = new PublicacionResponseDTO();
        dto.setId(publicacion.getId());
        dto.setTitulo(publicacion.getTitulo());
        dto.setContenido(publicacion.getContenido());

        AutorResponseDTO autorDTO = new AutorResponseDTO();
        autorDTO.setId(publicacion.getUsuario().getId());
        autorDTO.setNombre(publicacion.getUsuario().getNombre());
        dto.setAutor(autorDTO);
        return dto;
    }
    public List<PublicacionResponseDTO> obtenerTodasLasPublicaciones() {
        List<Publicacion> publicaciones = publicacionRepository.findAll();
        List<PublicacionResponseDTO> dtos = new ArrayList<>();
        for (Publicacion publicacion : publicaciones) {
            PublicacionResponseDTO dto = convertirADTO(publicacion);
            dtos.add(dto);
        }
        return dtos;
        /* return publicaciones.stream()
                .map(this::convertirADTO) // Aplica el "traductor" a cada item
                .toList(); // Lo convierte de nuevo en una Lista */
    }

    public PublicacionResponseDTO obtenerPublicacionPorId(Long id) {
        Publicacion publicacion = publicacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion no encontrada con id: " + id));
        return convertirADTO(publicacion);
    }

    public PublicacionResponseDTO actualizarPublicacion(Long id, PublicacionRequestDTO requestDTO) {
        Publicacion publicacion = publicacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion no encontrada con id: " + id));

        publicacion.setTitulo(requestDTO.getTitulo());
        publicacion.setContenido(requestDTO.getContenido());

        Publicacion publicacionGuardada = publicacionRepository.save(publicacion);
        return convertirADTO(publicacionGuardada);
    }

    public void borrarPublicacion(Long id) {
        Publicacion publicacion = publicacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion no encontrada con id: " + id));

        publicacionRepository.delete(publicacion);
    }
}

