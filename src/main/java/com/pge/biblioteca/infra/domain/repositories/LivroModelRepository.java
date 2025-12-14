package com.pge.biblioteca.infra.domain.repositories;

import com.pge.biblioteca.infra.domain.models.LivroModel;

import java.util.List;
import java.util.Optional;
public interface LivroModelRepository {
    LivroModel salvar(LivroModel livroModel);
    Optional<LivroModel> buscarPorId(Long id);
    Optional<LivroModel> buscarPorISBN(String isbn);
    List<LivroModel> listarLivrosDisponiveis();
    List<LivroModel> listarTodos();
}
