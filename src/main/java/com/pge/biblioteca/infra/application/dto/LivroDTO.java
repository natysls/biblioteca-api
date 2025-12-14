package com.pge.biblioteca.infra.application.dto;

import lombok.Data;

@Data
public class LivroDTO {
    private Long id;
    private String titulo;
    private String autor;
    private String isbn;
    private Integer quantidadeDisponivel;
}
