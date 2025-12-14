package com.pge.biblioteca.infra.domain.repositories;

import com.pge.biblioteca.infra.domain.models.EmprestimoModel;

import java.util.List;
import java.util.Optional;

public interface EmprestimoModelRepository {
    EmprestimoModel salvar(EmprestimoModel emprestimoModel);
    Optional<EmprestimoModel> buscarPorId(Long id);
    List<EmprestimoModel> listarPorUsuarioModelMatricula(String matricula);
    List<EmprestimoModel> listarAtivosPorUsuarioModelMatricula(String matricula);
    List<EmprestimoModel> listarEmprestimos();
}
