package foro.challenge.foro.exception;// ValidacionIntegridadException.java
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT) // Esto hará que Spring Boot devuelva un 409 Conflict
public class ValidacionIntegridadException extends RuntimeException {

    public ValidacionIntegridadException(String s) {
        super(s);
    }
}
