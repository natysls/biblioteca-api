package com.pge.biblioteca.infra.application.services;

import com.pge.biblioteca.infra.application.dto.LivroDTO;
import com.pge.biblioteca.infra.application.dto.UsuarioDTO;
import com.pge.biblioteca.infra.application.mapper.LivroMapper;
import com.pge.biblioteca.infra.application.mapper.UsuarioMapper;
import com.pge.biblioteca.infra.domain.repositories.UsuarioModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioModelRepository usuarioModelRepository;
    @Autowired
    private UsuarioMapper mapper;

    public List<UsuarioDTO> listarUsuarios() {
        return usuarioModelRepository.listarTodos()
                .stream()
                .map(mapper::modeloParaDTO)
                .toList();
    }
}
