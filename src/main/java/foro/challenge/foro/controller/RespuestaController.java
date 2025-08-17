package foro.challenge.foro.controller;

import foro.challenge.foro.model.Respuesta;
import foro.challenge.foro.repository.RespuestaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/respuestas")
public class RespuestaController {

    private final RespuestaRepository respuestaRepository;

    public RespuestaController(RespuestaRepository respuestaRepository) {
        this.respuestaRepository = respuestaRepository;
    }

    @GetMapping
    public List<Respuesta> listar() {
        return respuestaRepository.findAll();
    }

    @PostMapping
    public Respuesta crear(@RequestBody Respuesta respuesta) {
        return respuestaRepository.save(respuesta);
    }
}