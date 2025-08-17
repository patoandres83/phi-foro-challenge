package foro.challenge.foro.service;

import foro.challenge.foro.dto.TopicoDTO;
import foro.challenge.foro.model.Topico;

import java.util.List;

public interface TopicoService {
    Topico crear(TopicoDTO dto, String correoAutor);
    List<Topico> listarTodos();
    Topico obtener(Long id);
    Topico actualizar(Long id, TopicoDTO dto);
    void desactivar(Long id);
    void eliminar(Long id);
}