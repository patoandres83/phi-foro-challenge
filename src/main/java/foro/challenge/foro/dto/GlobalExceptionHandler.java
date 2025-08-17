package foro.challenge.foro.dto;
import jakarta.persistence.EntityNotFoundException; // ¡Importa esta excepción!
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // ... (Tu manejador para ValidacionIntegridadException si lo tienes) ...

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(), // Esto será "Not Found"
                "Error, no se ha encontrado el tópico solicitado." // ¡Tu mensaje específico para 404!
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // --- Nuevo manejador para IDs inválidos ---
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        // Puedes personalizar el mensaje basado en el nombre del parámetro si lo deseas
        String parameterName = ex.getName();
        String requiredType = ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "desconocido";

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), // "Bad Request"
                String.format("El ID proporcionado '%s' no es un valor válido. Se esperaba un número entero.", ex.getValue())
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // ... (Otros manejadores de excepciones, si los tienes, como para validaciones de @Valid) ...
}
