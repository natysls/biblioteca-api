package com.pge.biblioteca.infra.domain.models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmprestimoModel {
    private Long id;
    private UsuarioModel usuarioModel;
    private LivroModel livroModel;
    private LocalDate dataInicio;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;
    private boolean devolvido;
}