package com.example.pruebaapi.repository;

import com.example.pruebaapi.domain.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {
}
