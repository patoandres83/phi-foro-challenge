package foro.challenge.foro.controller;

import foro.challenge.foro.dto.LoginDTO;
import foro.challenge.foro.dto.RegistroUsuarioDTO;
import foro.challenge.foro.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegistroUsuarioDTO dto) {
        usuarioService.registrar(dto);
        return ResponseEntity.ok().body("Usuario registrado");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO dto) {

        /*
        System.out.println("=== Datos recibidos en /login ===");
        System.out.println("Correo: " + dto.getCorreoElectronico());
        System.out.println("Contraseña: " + dto.getContrasena());
        System.out.println("================================");
         */

        String token = usuarioService.login(dto);
        // devuelve JSON { "token": "..."}
        return ResponseEntity.ok().body(new TokenResponse(token, 30 * 60));
    }

    // Pequeña clase interna para respuesta
    private static record TokenResponse(String token, int expiresInSeconds) {}
}