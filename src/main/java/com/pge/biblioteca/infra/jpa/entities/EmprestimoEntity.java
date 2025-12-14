package com.pge.biblioteca.infra.jpa.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "emprestimos")
public class EmprestimoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_usuario")
    private UsuarioEntity usuarioEntity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_livro")
    private LivroEntity livroEntity;

    @Column(nullable = false)
    private LocalDate dataInicio;

    @Column(nullable = false)
    private LocalDate dataPrevistaDevolucao;

    @Column(nullable = false)
    private LocalDate dataDevolucao;

    @Column(nullable = false)
    private boolean devolvido;
}