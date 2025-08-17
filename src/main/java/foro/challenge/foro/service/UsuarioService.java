package foro.challenge.foro.service;


import foro.challenge.foro.dto.LoginDTO;
import foro.challenge.foro.dto.RegistroUsuarioDTO;

public interface UsuarioService {
    void registrar(RegistroUsuarioDTO dto);
    /**
     * @return token JWT válido por 30 minutos
     */
    String login(LoginDTO dto);
}