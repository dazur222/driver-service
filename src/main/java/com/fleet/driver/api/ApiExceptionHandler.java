package com.fleet.driver.api;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    
    @ExceptionHandler(DriverNotFoundException.class)
    ProblemDetail notFound(DriverNotFoundException error) { return problem(HttpStatus.NOT_FOUND, "DRIVER_NOT_FOUND", error.getMessage()); }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    ProblemDetail conflict(DataIntegrityViolationException error) { return problem(HttpStatus.CONFLICT, "DRIVER_CONFLICT", "El identificador, la licencia o la asignación ya existe."); }

    @ExceptionHandler({MethodArgumentNotValidException.class, InvalidDriverRequestException.class})
    ProblemDetail invalid(Exception error) { return problem(HttpStatus.BAD_REQUEST, "INVALID_REQUEST", error instanceof InvalidDriverRequestException ? error.getMessage() : "La solicitud contiene campos inválidos."); }

    private ProblemDetail problem(HttpStatus status, String code, String detail) {
        ProblemDetail result = ProblemDetail.forStatusAndDetail(status, detail);
        result.setProperty("code", code);
        return result;
    }
}
