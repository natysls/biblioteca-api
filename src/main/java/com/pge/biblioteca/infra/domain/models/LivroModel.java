package com.pge.biblioteca.infra.domain.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class LivroModel {
    private Long id;
    private String titulo;
    private String autor;
    private String isbn;
    private Integer quantidadeDisponivel;
}
