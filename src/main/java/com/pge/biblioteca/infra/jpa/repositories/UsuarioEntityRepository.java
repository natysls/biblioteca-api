package com.pge.biblioteca.infra.jpa.repositories;

import com.pge.biblioteca.infra.jpa.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioEntityRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByMatricula(String matricula);
}
