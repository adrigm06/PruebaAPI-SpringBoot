package com.example.pruebaapi.service;

import com.example.pruebaapi.domain.Usuario;
import com.example.pruebaapi.dto.UsuarioRequestDTO;
import com.example.pruebaapi.dto.UsuarioResponseDTO;
import com.example.pruebaapi.exception.ResourceNotFoundException;
import com.example.pruebaapi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
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

    public UsuarioResponseDTO crearUsuario(UsuarioRequestDTO requestDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(requestDTO.getNombre());
        usuario.setEmail(requestDTO.getEmail());

        Usuario UsuarioGuardado = usuarioRepository.save(usuario);

        UsuarioResponseDTO responseDTO = new UsuarioResponseDTO();
        responseDTO.setId(UsuarioGuardado.getId());
        responseDTO.setEmail(UsuarioGuardado.getEmail());
        responseDTO.setNombre(UsuarioGuardado.getNombre());

        // Aquí podríamos añadir lógica (ej. validar que el nombre no esté vacío)
        return responseDTO;
    }

    public List<UsuarioResponseDTO> obtenerTodosLosUsuarios() {
        //Obtenemos la lista de ENTIDADES de la BBDD
        List<Usuario> usuarios = usuarioRepository.findAll();

        //Creamos una lista vacía para nuestros DTOs
        List<UsuarioResponseDTO> dtos = new ArrayList<>();

        //Recorremos la lista de entidades (el bucle)
        for (Usuario usuario : usuarios) {

            //Por CADA entidad, creamos un DTO
            UsuarioResponseDTO responseDTO = new UsuarioResponseDTO();
            responseDTO.setId(usuario.getId());
            responseDTO.setNombre(usuario.getNombre());
            responseDTO.setEmail(usuario.getEmail());

            //Añadimos el DTO a nuestra lista de DTOs
            dtos.add(responseDTO);
        }
        //Devolvemos la lista de DTOs ya convertida
        return dtos;
    }

    public UsuarioResponseDTO obtenerUsuarioPorId(Long id) {
        //Buscamos el usuario por ID
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado."));

        //Convertimos la entidad en un DTO
        UsuarioResponseDTO responseDTO = new UsuarioResponseDTO();
        responseDTO.setId(usuario.getId());
        responseDTO.setNombre(usuario.getNombre());
        responseDTO.setEmail(usuario.getEmail());

        return responseDTO;
    }

    public UsuarioResponseDTO actualizarUsuario(Long id, UsuarioRequestDTO requestDTO) {

        //Buscamos al usuario por ID (¡esto lanza 404 si no existe!)
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));

        //Actualizamos la entidad con los datos del DTO
        usuarioExistente.setNombre(requestDTO.getNombre());
        usuarioExistente.setEmail(requestDTO.getEmail());

        //Guardamos la entidad actualizada
        Usuario usuarioGuardado = usuarioRepository.save(usuarioExistente);

        //Lo convertimos a DTO para devolverlo
        UsuarioResponseDTO responseDTO = new UsuarioResponseDTO();
        responseDTO.setId(usuarioGuardado.getId());
        responseDTO.setNombre(usuarioGuardado.getNombre());
        responseDTO.setEmail(usuarioGuardado.getEmail());

        return responseDTO;
    }

    public void borrarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
