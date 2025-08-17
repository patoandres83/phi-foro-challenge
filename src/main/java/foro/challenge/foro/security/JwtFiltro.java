package foro.challenge.foro.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * Valida header Authorization Bearer <token>, verifica token y carga Authentication.
 * Asume que el claim "rol" contiene exactamente 'persona' o 'administrador'.
 */
public class JwtFiltro extends OncePerRequestFilter {

    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith(BEARER_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(BEARER_PREFIX.length());
        try {
            DecodedJWT decoded = JwtUtil.validarToken(token);
            String correo = decoded.getSubject();
            String rol = decoded.getClaim("rol").asString();
            if (correo != null && rol != null) {
                var authorities = List.of(new SimpleGrantedAuthority(rol));
                var auth = new UsernamePasswordAuthenticationToken(correo, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            // token inválido o expirado -> devolver 401
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"Token inválido o expirado\"}");
        }
    }
}