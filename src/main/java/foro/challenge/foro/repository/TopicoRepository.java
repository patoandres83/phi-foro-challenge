package foro.challenge.foro.repository;

import foro.challenge.foro.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    // Método para buscar un tópico por título
    Optional<Topico> findByTitulo(String titulo);

    // Método para buscar un tópico por mensaje
    Optional<Topico> findByMensaje(String mensaje);



}