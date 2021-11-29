package ar.edu.unq.desapp.grupoD022021.backenddesappapi.dto;

import org.springframework.http.HttpStatus;

public class ResponseDTO {
    private String message;
    private HttpStatus status;

    public ResponseDTO(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public ResponseDTO(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
