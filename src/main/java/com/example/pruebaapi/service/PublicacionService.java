package com.example.pruebaapi.service;

import com.example.pruebaapi.dto.PublicacionRequestDTO;
import com.example.pruebaapi.repository.PublicacionRepository;
import org.springframework.stereotype.Service;

@Service
public class PublicacionService {
    private PublicacionRepository publicacionRepository;
    public PublicacionService(PublicacionRepository publicacionRepository) {
        this.publicacionRepository = publicacionRepository;
    }
    public PublicacionRequestDTO crearPublicacion(PublicacionRequestDTO RequestDTO) {

        return null;
    }
}

