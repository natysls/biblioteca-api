package com.pge.biblioteca.infra.application.mapper;

import com.pge.biblioteca.infra.application.dto.LivroDTO;
import com.pge.biblioteca.infra.application.dto.UsuarioDTO;
import com.pge.biblioteca.infra.domain.models.LivroModel;
import com.pge.biblioteca.infra.domain.models.UsuarioModel;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public UsuarioDTO modeloParaDTO(UsuarioModel modelo) {
        if (modelo == null) return null;
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(modelo.getId());
        dto.setNome(modelo.getNome());
        dto.setMatricula(modelo.getMatricula());

        return dto;
    }

    public UsuarioModel paraModelo(UsuarioDTO dto) {
        if (dto == null) return null;
        UsuarioModel model = new UsuarioModel();
        model.setId(dto.getId());
        model.setNome(dto.getNome());
        model.setMatricula(dto.getMatricula());

        return model;
    }

}
