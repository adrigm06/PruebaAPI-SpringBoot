package com.example.pruebaapi.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AutoIncrement
    private Long id; //Usar Long porque es null por defecto y no confunde a la BBDD
    private String nombre;
    @Column(unique = true) // <-- 3. ¡Esta es de BBDD! Debe quedarse.
    private String email;
}
