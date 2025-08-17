package foro.challenge.foro.service.impl;

import foro.challenge.foro.dto.TopicoDTO;
import foro.challenge.foro.exception.ValidacionIntegridadException;
import foro.challenge.foro.model.Topico;
import foro.challenge.foro.model.Usuario;
import foro.challenge.foro.repository.TopicoRepository;
import foro.challenge.foro.repository.UsuarioRepository;
import foro.challenge.foro.service.TopicoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TopicoServiceImpl implements TopicoService {

    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;

    public TopicoServiceImpl(TopicoRepository topicoRepository, UsuarioRepository usuarioRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Topico crear(TopicoDTO dto, String correoAutor) {

        // 1. Validar título repetido
        if (topicoRepository.findByTitulo(dto.getTitulo()).isPresent()) {
            throw new ValidacionIntegridadException("Ya existe un tópico con este título.");
        }

        // 2. Validar mensaje repetido
        if (topicoRepository.findByMensaje(dto.getMensaje()).isPresent()) {
            throw new ValidacionIntegridadException("Ya existe un tópico con este mensaje.");
        }

        Usuario autor = usuarioRepository.findByCorreoElectronico(correoAutor)
                .orElseThrow(() -> new IllegalArgumentException("Autor no encontrado"));

        Topico t = new Topico();
        t.setTitulo(dto.getTitulo());
        t.setMensaje(dto.getMensaje());
        t.setCurso(dto.getCurso());
        t.setFechaCreacion(LocalDateTime.now());
        t.setStatus("ACTIVO");
        t.setAutor(autor);

        return topicoRepository.save(t);
    }

    @Override
    public List<Topico> listarTodos() {
        return topicoRepository.findAll();
    }

    /*@Override
    public Topico obtener(Long id) {
        return topicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tópico no encontrado"));
    }*/

    @Override
    public Topico obtener(Long id) {
        System.out.println("obteniendo id...");
        return topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico con ID " + id + " no encontrado."));
    }

    @Override
    public Topico actualizar(Long id, TopicoDTO dto) {
        Topico t = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico con ID "+id+" no encontrado"));
        t.setTitulo(dto.getTitulo());
        t.setMensaje(dto.getMensaje());
        t.setStatus(dto.getStatus());
        t.setCurso(dto.getCurso());
        return topicoRepository.save(t);
    }



    @Override
    public void desactivar(Long id) {
        Topico t = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));
        t.setStatus("INACTIVO");
        topicoRepository.save(t);
    }

    @Override
    @Transactional // It's good practice to mark methods that modify data with @Transactional
    public void eliminar(Long id) { // Changed method name to 'eliminar' for clarity
        // First, check if the topic exists to throw 404 if not found
        if (!topicoRepository.existsById(id)) {
            throw new EntityNotFoundException("Tópico con ID " + id + " no encontrado para eliminación.");
        }
        // If it exists, proceed with the deletion
        topicoRepository.deleteById(id);
    }
}
