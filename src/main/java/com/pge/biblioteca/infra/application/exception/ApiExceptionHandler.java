package com.pge.biblioteca.infra.application.exception;

import com.pge.biblioteca.infra.application.dto.ApiErrorDTO;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorDTO> handleDataIntegrityViolation(
            DataIntegrityViolationException ex) {

        Throwable cause = ex.getCause();
        if (cause instanceof ConstraintViolationException cve) {
            String constraint = cve.getConstraintName();
            assert constraint != null;
            if (constraint.contains("uk_livros_isbn")) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(new ApiErrorDTO("Já existe um livro cadastrado com este ISBN."));
            } else if (constraint.contains("ck_livros_qtd_nao_negativa")) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(new ApiErrorDTO("A quantidade de livros não pode ser menos de zero."));
            }
        }

        // Qualquer outra violação de integridade
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorDTO("Violação de integridade de dados."));
    }
}

