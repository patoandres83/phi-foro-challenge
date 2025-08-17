package foro.challenge.foro.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "perfil")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
}