package com.example.pruebaapi.dto;

import com.example.pruebaapi.domain.Usuario;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PublicacionRequestDTO {
    @NotEmpty(message = "El titulo es obligatorio")
    private String titulo;
    private String contenido;
    @NotNull(message = "El id es obligatorio")
    private Long usuarioId;
}
