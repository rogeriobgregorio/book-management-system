package com.rogeriogregorio.bookmanagementsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookManagementExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> genericErrorHandler(Exception ex) {
        Erro detalhe = new Erro()
            .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .message(ex.getMessage())
            .detail(String.format("A exceção lançada foi: ", ex.getMessage()));

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(detalhe);
    }

    @ExceptionHandler(InvalidParametersException.class)
    public ResponseEntity<Erro> InvalidParametersExceptionHandler(InvalidParametersException ex) {
        Erro detalhe = new Erro()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .detail(String.format("A exceção lançada foi: ", ex.getMessage()));

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(detalhe);
    }
}
