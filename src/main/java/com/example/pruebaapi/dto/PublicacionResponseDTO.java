package com.example.pruebaapi.dto;

import lombok.Data;

@Data
public class PublicacionResponseDTO {
    private Long id;
    private String titulo;
    private String contenido;
    private AutorResponseDTO autor;
}
