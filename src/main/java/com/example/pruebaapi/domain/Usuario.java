package com.example.pruebaapi.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity //JPA
@Getter //Lombok
@Setter //Lombok
@NoArgsConstructor //Lombok
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AutoIncrement
    private Long id; //Usar Long porque es null por defecto y no confunde a la BBDD
    private String nombre;
    @Column(unique = true)
    private String email;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Publicacion> publicaciones;
}
