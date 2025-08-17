package foro.challenge.foro.controller;

import foro.challenge.foro.dto.TopicoDTO;
import foro.challenge.foro.model.Topico;
import foro.challenge.foro.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String, String>> desactivar(@PathVariable Long id) {
        topicoService.desactivar(id);
        //return ResponseEntity.noContent().build();
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "registro desactivado correctamente");
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('administrador')") // Typically, only admins should hard delete
    @DeleteMapping("/eliminar/{id}") // Use @DeleteMapping
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Long id) {
        topicoService.eliminar(id);
        //return ResponseEntity.noContent().build(); // Return 204 No Content for successful deletion
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "registro eliminado correctamente");
        return ResponseEntity.ok(response);
    }

}
