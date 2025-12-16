package com.pge.biblioteca.infra.jpa.repositories;

import com.pge.biblioteca.infra.application.dto.LivroDTO;
import com.pge.biblioteca.infra.jpa.entities.LivroEntity;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
class LivroEntityRepositoryTest {
    @Autowired
    EntityManager entityManager;

    @Autowired
    LivroEntityRepository livroEntityRepository;

    @Test
    @DisplayName("Deve encontrar um livro pelo ISBN")
    void findByIsbnCase1() {
        String isbn = "1234567890";
        LivroDTO dto = new LivroDTO();
        dto.setIsbn(isbn);
        dto.setTitulo("Teste de Livro");
        dto.setAutor("Autor de Teste");
        dto.setQuantidadeDisponivel(5);
        LivroEntity livroCriado = criarLivroEntity(dto);

        Optional<LivroEntity> result = livroEntityRepository.findByIsbn(isbn);
        Assertions.assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Não deve encontrar um livro com ISBN inexistente")
    void findByIsbnCase2() {
        String isbn = "1234567890";

        Optional<LivroEntity> result = livroEntityRepository.findByIsbn(isbn);
        Assertions.assertThat(result.isEmpty()).isTrue();
    }

    private LivroEntity criarLivroEntity(LivroDTO dto) {
        LivroEntity livro = new LivroEntity();
        livro.setIsbn(dto.getIsbn());
        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setQuantidadeDisponivel(dto.getQuantidadeDisponivel());
        entityManager.persist(livro);
        return livro;
    }


}