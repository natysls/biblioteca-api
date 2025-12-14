package com.pge.biblioteca.infra.application.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmprestimoDTO {
    private Long id;
    private String usuarioMatricula;
    private String usuarioNome;
    private Long livroId;
    private String livroTitulo;
    private LocalDate dataInicio;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;
    private boolean devolvido;
}
