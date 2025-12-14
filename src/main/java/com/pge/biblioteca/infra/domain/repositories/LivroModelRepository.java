package com.pge.biblioteca.infra.domain.repositories;

import com.pge.biblioteca.infra.domain.models.LivroModel;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
public interface LivroModelRepository {
    LivroModel salvar(LivroModel livroModel);
    Optional<LivroModel> buscarPorId(Long id);
    List<LivroModel> listarPorISBN(String isbn);
    List<LivroModel> listarLivrosDisponiveis();
    List<LivroModel> listarTodos();
}
