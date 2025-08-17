package foro.challenge.foro.dto;
// ErrorResponse.java

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorResponse {
    private int status;
    private String error;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    public ErrorResponse(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    // Getters...
}