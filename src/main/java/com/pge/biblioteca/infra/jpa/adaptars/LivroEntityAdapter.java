package com.pge.biblioteca.infra.jpa.adaptars;

import com.pge.biblioteca.infra.domain.models.LivroModel;
import com.pge.biblioteca.infra.domain.repositories.LivroModelRepository;
import com.pge.biblioteca.infra.jpa.entities.LivroEntity;
import com.pge.biblioteca.infra.jpa.repositories.LivroEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LivroEntityAdapter implements LivroModelRepository {
    @Autowired
    private LivroEntityRepository livroEntityRepository;

    @Override
    public LivroModel salvar(LivroModel livroModel) {
        LivroEntity ent = paraEntidade(livroModel);
        LivroEntity salvo = livroEntityRepository.save(ent);
        return paraModelo(salvo);
    }

    @Override
    public Optional<LivroModel> buscarPorId(Long id) {
        return livroEntityRepository.findById(id).map(this::paraModelo);
    }

    @Override
    public List<LivroModel> listarPorISBN(String isbn) {
        return livroEntityRepository.findByIsbn(isbn).stream()
                .map(this::paraModelo)
                .toList();
    }

    @Override
    public List<LivroModel> listarLivrosDisponiveis() {
        return livroEntityRepository.findByQuantidadeDisponivelGreaterThan(0).stream()
                .map(this::paraModelo)
                .toList();
    }

    @Override
    public List<LivroModel> listarTodos() {
        return livroEntityRepository.findAll().stream()
                .map(this::paraModelo)
                .toList();
    }

    private LivroEntity paraEntidade(LivroModel e) {
        if(e == null) return null;
        LivroEntity ent = new LivroEntity();
        ent.setId(e.getId());
        ent.setAutor(e.getAutor());
        ent.setIsbn(e.getIsbn());
        ent.setTitulo(e.getTitulo());
        ent.setQuantidadeDisponivel(e.getQuantidadeDisponivel());

        return ent;
    }

    private LivroModel paraModelo(LivroEntity ent) {
        if (ent == null) return null;
        LivroModel e = new LivroModel();
        e.setId(ent.getId());
        e.setAutor(ent.getAutor());
        e.setTitulo(ent.getTitulo());
        e.setIsbn(ent.getIsbn());
        e.setQuantidadeDisponivel(ent.getQuantidadeDisponivel());

        return e;
    }
}
