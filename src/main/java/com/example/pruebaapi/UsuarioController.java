package com.example.pruebaapi;

import com.example.pruebaapi.domain.Usuario;
import com.example.pruebaapi.dto.UsuarioRequestDTO;
import com.example.pruebaapi.dto.UsuarioResponseDTO;
import com.example.pruebaapi.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {
    // 1. El campo que queremos inyectar
    private final UsuarioService usuarioService;

    // 2. El constructor que Spring usará
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @PostMapping("/usuarios")
    public UsuarioResponseDTO crearUsuario(@Valid @RequestBody UsuarioRequestDTO usuario) {
        return usuarioService.crearUsuario(usuario);
    }
    @GetMapping("/usuarios")
    public List<UsuarioResponseDTO> obtenerTodosLosUsuarios() {
        return usuarioService.obtenerTodosLosUsuarios();
    }
    @GetMapping("/usuarios/{id}")
    public UsuarioResponseDTO obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioService.obtenerUsuarioPorId(id);
    }
    @PutMapping("/usuarios/{id}")
    public UsuarioResponseDTO actualizarUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDTO usuario) {
        return usuarioService.actualizarUsuario(id, usuario);
    }
    @DeleteMapping("/usuarios/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioService.borrarUsuario(id);
    }
}
