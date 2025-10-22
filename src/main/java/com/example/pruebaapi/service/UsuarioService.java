package com.example.pruebaapi.service;

import com.example.pruebaapi.domain.Usuario;
import com.example.pruebaapi.exception.ResourceNotFoundException;
import com.example.pruebaapi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    //Inyectar campo con Spring
    private final UsuarioRepository usuarioRepository;
    //Constructor que Spring usara para inyectar
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario crearUsuario(Usuario usuario) {
        // Aquí podríamos añadir lógica (ej. validar que el nombre no esté vacío)
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario actualizarUsuario(Long id, Usuario datosNuevos) {
        //Buscar por id, findbyid devuelve un Optional
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            Usuario usuarioExistente = usuarioOptional.get();

            usuarioExistente.setNombre(datosNuevos.getNombre());

            return usuarioRepository.save(usuarioExistente);
        } else {
            throw new ResourceNotFoundException("Usuario no encontrado con id: " + id);
        }
    }

    public void borrarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
