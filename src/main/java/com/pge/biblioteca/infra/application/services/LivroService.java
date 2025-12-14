package com.pge.biblioteca.infra.application.services;

import com.pge.biblioteca.infra.application.dto.LivroDTO;
import com.pge.biblioteca.infra.application.mapper.LivroMapper;
import com.pge.biblioteca.infra.domain.models.LivroModel;
import com.pge.biblioteca.infra.domain.repositories.LivroModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroModelRepository livroModelRepository;

    @Autowired
    private LivroMapper mapper;

    @Transactional
    public LivroDTO cadastrar(LivroDTO dto) {
        dto.setId(null); // Garantir que o ID seja nulo para criação
        LivroModel l = mapper.paraModelo(dto);
        LivroModel salvo = livroModelRepository.salvar(l);
        return mapper.modeloParaDTO(salvo);
    }

    public List<LivroDTO> listarLivrosDisponiveis() {
        return livroModelRepository.listarLivrosDisponiveis()
                .stream()
                .map(mapper::modeloParaDTO)
                .toList();
    }

    public List<LivroDTO> listarTodosLivros() {
        return livroModelRepository.listarTodos()
                .stream()
                .map(mapper::modeloParaDTO)
                .toList();
    }
}
