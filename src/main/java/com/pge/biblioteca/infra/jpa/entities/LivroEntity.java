package com.pge.biblioteca.infra.jpa.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@NoArgsConstructor
@Entity
@Table(
        name = "livros",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_livros_isbn",
                        columnNames = "isbn"
                )
        }
)
@Check(
        name = "ck_livros_qtd_nao_negativa",
        constraints = "quantidade_disponivel >= 0"
)
public class LivroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String autor;

    @Column(unique = true)
    private String isbn;

    @Column(nullable = false)
    private Integer quantidadeDisponivel;

}
