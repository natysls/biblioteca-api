package com.pge.biblioteca.infra.domain.services;

import com.pge.biblioteca.infra.domain.excecao.ExcecaoNegocio;
import com.pge.biblioteca.infra.domain.models.EmprestimoModel;
import com.pge.biblioteca.infra.domain.models.LivroModel;
import com.pge.biblioteca.infra.domain.models.UsuarioModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmprestimoModelService {
    private static final int MAX_DIAS = 14;
    private static final int MAX_EMPRESTIMOS_POR_USUARIO = 5;

    public void validarDisponibilidade(LivroModel livro) {
        if (livro.getQuantidadeDisponivel() == null || livro.getQuantidadeDisponivel() <= 0) {
            throw new ExcecaoNegocio("Livro sem unidades disponíveis");
        }
    }

    public void validarLimiteEmprestimos(UsuarioModel usuario, List<EmprestimoModel> emprestimosAtivosDoUsuario) {
        if (emprestimosAtivosDoUsuario.size() >= MAX_EMPRESTIMOS_POR_USUARIO) {
            throw new ExcecaoNegocio("Usuário já possui o limite de empréstimos ativos (5)");
        }
    }

    public LocalDate calcularDataMaximaDevolucao(LocalDate dataInicio, int diasSolicitados) {
        if (diasSolicitados > MAX_DIAS) {
            throw new ExcecaoNegocio("A data prevista de devolução não pode ser superior a 14 dias");
        }
        return dataInicio.plusDays(Math.min(diasSolicitados, MAX_DIAS));
    }
}
