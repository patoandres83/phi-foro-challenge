package foro.challenge.foro.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * Util simple para generar y validar tokens usando com.auth0:java-jwt.
 * En producción reemplaza SECRET por una variable de entorno y no lo hardcodees.
 */
public final class JwtUtil {
    private JwtUtil() {}

    // TODO: leer desde application.properties o variable de entorno
    private static final String SECRET = "CAMBIA_ESTA_CLAVE_POR_ALGO_MUY_SEGURO_EN_PROD_!@#";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET.getBytes());
    private static final long EXPIRATION_MILLIS = 30L * 60L * 1000L; // 30 minutos

    public static String generarToken(String correo, String rol) {
        long now = System.currentTimeMillis();
        return JWT.create()
                .withSubject(correo)
                .withClaim("rol", rol)
                .withIssuedAt(new Date(now))
                .withExpiresAt(new Date(now + EXPIRATION_MILLIS))
                .sign(ALGORITHM);
    }

    public static DecodedJWT validarToken(String token) {
        return JWT.require(ALGORITHM).build().verify(token);
    }
}