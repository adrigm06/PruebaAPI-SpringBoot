package com.example.pruebaapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UsuarioRequestDTO {
    @NotEmpty(message = "El nombre es obligatorio")
    private String nombre;

    @Email(message = "El formato del email no es válido")
    @NotEmpty(message = "El email es obligatorio")
    private String email;
}
