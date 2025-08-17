package foro.challenge.foro.service.impl;

import foro.challenge.foro.dto.LoginDTO;
import foro.challenge.foro.dto.RegistroUsuarioDTO;
import foro.challenge.foro.model.Perfil;
import foro.challenge.foro.model.Usuario;
import foro.challenge.foro.repository.PerfilRepository;
import foro.challenge.foro.repository.UsuarioRepository;
import foro.challenge.foro.security.JwtUtil;
import foro.challenge.foro.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository,
                              PerfilRepository perfilRepository,
                              PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.perfilRepository = perfilRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registrar(RegistroUsuarioDTO dto) {
        if (usuarioRepository.findByCorreoElectronico(dto.getCorreoElectronico()).isPresent()) {
            throw new IllegalArgumentException("Correo ya registrado");
        }

        // buscar perfil por nombre (se usan nombres en minúscula 'persona' o 'administrador')
        Perfil perfil = perfilRepository.findByNombre(dto.getPerfil().toLowerCase())
                .orElseThrow(() -> new IllegalArgumentException("Perfil inválido"));

        Usuario u = new Usuario();
        u.setNombre(dto.getNombre());
        u.setCorreoElectronico(dto.getCorreoElectronico());
        u.setContrasena(passwordEncoder.encode(dto.getContrasena()));
        u.setPerfil(perfil);

        usuarioRepository.save(u);
    }

    @Override
    public String login(LoginDTO dto) {
        var opt = usuarioRepository.findByCorreoElectronico(dto.getCorreoElectronico());
        if (opt.isEmpty()) throw new IllegalArgumentException("Usuario no encontrado");

        Usuario u = opt.get();

        // 🔍 Debug: imprimir contraseñas
        /*System.out.println("=== Debug login ===");
        System.out.println("Contraseña enviada (texto plano): " + dto.getContrasena());
        System.out.println("Contraseña almacenada (BCrypt hash): " + u.getContrasena());
        System.out.println("===================");*/

        if (!passwordEncoder.matches(dto.getContrasena(), u.getContrasena())) {
            throw new IllegalArgumentException("Credenciales inválidas");
        }
        String rol = u.getPerfil().getNombre(); // 'persona' o 'administrador'
        return JwtUtil.generarToken(u.getCorreoElectronico(), rol);
    }
}
