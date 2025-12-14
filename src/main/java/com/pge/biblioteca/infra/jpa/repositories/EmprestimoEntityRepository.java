package com.pge.biblioteca.infra.jpa.repositories;

import com.pge.biblioteca.infra.jpa.entities.EmprestimoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoEntityRepository extends JpaRepository<EmprestimoEntity, Long> {
    List<EmprestimoEntity> findByUsuarioEntityMatricula(String matricula);
    List<EmprestimoEntity> findByUsuarioEntityMatriculaAndDevolvidoFalse(String matricula);
}
