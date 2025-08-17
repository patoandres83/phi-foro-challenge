package foro.challenge.foro.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegistroUsuarioDTO {
    @NotBlank
    private String nombre;

    @Email @NotBlank
    private String correoElectronico;

    @NotBlank
    private String contrasena;

    @NotBlank
    private String perfil; // persona o administrador
}