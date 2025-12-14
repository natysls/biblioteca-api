package com.pge.biblioteca.infra.domain.repositories;

import com.pge.biblioteca.infra.domain.models.UsuarioModel;

import java.util.Optional;

public interface UsuarioModelRepository {
    UsuarioModel salvar(UsuarioModel usuarioModel);
    Optional<UsuarioModel> buscarPorMatricula(String matricula);
    Optional<UsuarioModel> listarTodos();
}
