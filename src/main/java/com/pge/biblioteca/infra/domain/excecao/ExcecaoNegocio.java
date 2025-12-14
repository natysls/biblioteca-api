package com.pge.biblioteca.infra.domain.excecao;

public class ExcecaoNegocio extends RuntimeException {
    public ExcecaoNegocio(String mensagem) {
        super(mensagem);
    }
}
