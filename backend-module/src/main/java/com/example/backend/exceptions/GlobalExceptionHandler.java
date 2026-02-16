package com.example.backend.exceptions;

import com.example.backend.dtos.ErrorDTO;
import com.example.ejb.domain.exceptions.BaseException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorDTO> handleGeneric(
            BaseException ex,
            HttpServletRequest request
    ) {
        return buildResponse(
                HttpStatus.valueOf(ex.getStatusCode()),
                ex.getMessage(),
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<ErrorDTO> handleGeneric(
            ObjectOptimisticLockingFailureException ex,
            HttpServletRequest request
    ) {
        return buildResponse(
                HttpStatus.BAD_REQUEST,
                "LockError",
                "A conta foi atualizada por outra operação. Por favor, tente novamente.",
                request.getRequestURI()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleGeneric(
            Exception ex,
            HttpServletRequest request
    ) {
        return buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal Server Error",
                "Erro inesperado",
                request.getRequestURI()
        );
    }


    private ResponseEntity<ErrorDTO> buildResponse(
            HttpStatus status,
            String error,
            String message,
            String path
    ) {
        ErrorDTO response = new ErrorDTO(status.value(), error, message, path);

        return ResponseEntity.status(status).body(response);
    }
}
