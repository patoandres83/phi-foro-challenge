package foro.challenge.foro.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TopicoDTO {
    @NotBlank
    private String titulo;

    @NotBlank
    private String mensaje;

    @NotBlank
    private String curso;

    private String status;
}