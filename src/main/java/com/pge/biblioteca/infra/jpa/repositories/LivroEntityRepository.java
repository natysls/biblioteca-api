package com.pge.biblioteca.infra.jpa.repositories;

import com.pge.biblioteca.infra.jpa.entities.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroEntityRepository extends JpaRepository<LivroEntity, Long> {
    List<LivroEntity> findByQuantidadeDisponivelGreaterThan(Integer quantidade);
    Optional<LivroEntity> findByIsbn(String isbn);

}
