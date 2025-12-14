package com.pge.biblioteca.infra.application.services;

import com.pge.biblioteca.infra.application.dto.LivroDTO;
import com.pge.biblioteca.infra.application.mapper.LivroMapper;
import com.pge.biblioteca.infra.domain.models.LivroModel;
import com.pge.biblioteca.infra.domain.repositories.LivroModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class LivroService {

    @Autowired
    private LivroModelRepository livroModelRepository;

    @Autowired
    private LivroMapper mapper;

    @Transactional
    public LivroDTO cadastrar(LivroDTO dto) {
        dto.setId(null); // Garantir que o ID seja nulo para criação
        return salvar(dto);
    }

    public LivroDTO salvar(LivroDTO dto){
        LivroModel l = mapper.paraModelo(dto);
        LivroModel salvo = livroModelRepository.salvar(l);
        return mapper.modeloParaDTO(salvo);
    }

    @Transactional
    public LivroDTO atualizarParcial(String isbn, Map<String, Object> campos) {
        LivroModel livro = livroModelRepository.buscarPorISBN(isbn)
                .orElseThrow(() -> new RuntimeException("Livro com ISBN " + isbn + " não encontrado."));

        if (campos.containsKey("quantidadeDisponivel")) {
            Integer qtd = (Integer) campos.get("quantidadeDisponivel");
            livro.setQuantidadeDisponivel(qtd);
        }
        if(campos.containsKey("titulo")) {
            String titulo = (String) campos.get("titulo");
            livro.setTitulo(titulo);
        }
        if(campos.containsKey("autor")) {
            String autor = (String) campos.get("autor");
            livro.setAutor(autor);
        }

        return mapper.modeloParaDTO(livroModelRepository.salvar(livro));
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
