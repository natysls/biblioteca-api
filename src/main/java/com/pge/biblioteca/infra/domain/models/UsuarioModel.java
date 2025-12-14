package com.pge.biblioteca.infra.domain.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UsuarioModel {
    private Long id;
    private String nome;
    private String matricula;
    private String email;
    private Boolean ativo;
}