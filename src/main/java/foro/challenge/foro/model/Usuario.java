package foro.challenge.foro.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuario")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(name = "correo_electronico", unique = true, nullable = false)
    private String correoElectronico;

    private String contrasena;

    @ManyToOne
    @JoinColumn(name = "perfil_id", nullable = false)
    private Perfil perfil;
}