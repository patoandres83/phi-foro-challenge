package foro.challenge.foro.controller;

import foro.challenge.foro.model.Usuario;
import foro.challenge.foro.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}