package foro.challenge.foro.controller;

import foro.challenge.foro.dto.TopicoDTO;
import foro.challenge.foro.model.Topico;
import foro.challenge.foro.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topicos")
public class TopicoController {

    private final TopicoService topicoService;

    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    // crear un nuevo tópico (perfil persona, administrador)
    @PreAuthorize("hasAnyAuthority('persona','administrador')")
    @PostMapping
    public ResponseEntity<Topico> crear(@Valid @RequestBody TopicoDTO dto, Authentication auth) {
        String correoAutor = auth.getName();
        Topico creado = topicoService.crear(dto, correoAutor);
        return ResponseEntity.ok(creado);
    }

    // mostrar todos los tópicos creados (perfil persona, administrador)
    @PreAuthorize("hasAnyAuthority('persona','administrador')")
    @GetMapping
    public ResponseEntity<List<Topico>> listar() {
        return ResponseEntity.ok(topicoService.listarTodos());
    }

    // mostrar un tópico específico (perfil persona, administrador)
    @PreAuthorize("hasAnyAuthority('persona','administrador')")
    @GetMapping("/{id}")
    public ResponseEntity<Topico> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(topicoService.obtener(id));
    }

    // actualizar un tópico (perfil administrador)
    @PreAuthorize("hasAuthority('administrador')")
    @PutMapping("/{id}")
    public ResponseEntity<Topico> actualizar(@PathVariable Long id, @Valid @RequestBody TopicoDTO dto) {
        return ResponseEntity.ok(topicoService.actualizar(id, dto));
    }

    // eliminar un tópico (desactivar)(perfil administrador)
    @PreAuthorize("hasAuthority('administrador')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> desactivar(@PathVariable Long id) {
        topicoService.desactivar(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('administrador')") // Typically, only admins should hard delete
    @DeleteMapping("/eliminar/{id}") // Use @DeleteMapping
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        topicoService.eliminar(id);
        return ResponseEntity.noContent().build(); // Return 204 No Content for successful deletion
    }

}