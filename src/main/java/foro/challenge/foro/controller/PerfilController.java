package foro.challenge.foro.controller;

import foro.challenge.foro.model.Perfil;
import foro.challenge.foro.repository.PerfilRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfiles")
public class PerfilController {

    private final PerfilRepository perfilRepository;

    public PerfilController(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    @GetMapping
    public List<Perfil> listar() {
        return perfilRepository.findAll();
    }

    @PostMapping
    public Perfil crear(@RequestBody Perfil perfil) {
        return perfilRepository.save(perfil);
    }
}