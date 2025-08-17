package foro.challenge.foro.repository;
import foro.challenge.foro.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    Optional<Perfil> findByNombre(String nombre);
}