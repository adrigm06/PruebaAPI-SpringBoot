package com.example.pruebaapi;

import com.example.pruebaapi.domain.Usuario;
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
    public Usuario crearUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioService.crearUsuario(usuario);
    }
    @GetMapping("/usuarios")
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioService.obtenerTodosLosUsuarios();
    }
    @PutMapping("/usuarios/{id}")
    public Usuario actualizarUsuario(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
        return usuarioService.actualizarUsuario(id, usuario);
    }
    @DeleteMapping("/usuarios/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioService.borrarUsuario(id);
    }
}
