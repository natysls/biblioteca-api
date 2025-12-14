package com.pge.biblioteca.infra.application.dto;

public class ApiErrorDTO {
    private String mensagem;

    public ApiErrorDTO(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}

