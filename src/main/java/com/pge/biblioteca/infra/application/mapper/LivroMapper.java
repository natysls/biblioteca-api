package com.pge.biblioteca.infra.application.mapper;

import com.pge.biblioteca.infra.application.dto.LivroDTO;
import com.pge.biblioteca.infra.domain.models.LivroModel;
import com.pge.biblioteca.infra.jpa.entities.LivroEntity;
import org.springframework.stereotype.Component;

@Component
public class LivroMapper {

    public LivroDTO modeloParaDTO(LivroModel modelo) {
        if (modelo == null) return null;
        LivroDTO dto = new LivroDTO();
        dto.setId(modelo.getId());
        dto.setTitulo(modelo.getTitulo());
        dto.setAutor(modelo.getAutor());
        dto.setIsbn(modelo.getIsbn());
        dto.setQuantidadeDisponivel(modelo.getQuantidadeDisponivel());
        return dto;
    }

    public LivroModel paraModelo(LivroDTO dto) {
        if (dto == null) return null;
        LivroModel model = new LivroModel();
        model.setId(dto.getId());
        model.setTitulo(dto.getTitulo());
        model.setAutor(dto.getAutor());
        model.setIsbn(dto.getIsbn());
        model.setQuantidadeDisponivel(dto.getQuantidadeDisponivel());
        return model;
    }

}
