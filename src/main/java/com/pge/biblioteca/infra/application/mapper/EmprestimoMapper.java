package com.pge.biblioteca.infra.application.mapper;

import com.pge.biblioteca.infra.application.dto.EmprestimoDTO;
import com.pge.biblioteca.infra.domain.models.EmprestimoModel;
import com.pge.biblioteca.infra.jpa.entities.EmprestimoEntity;
import org.springframework.stereotype.Component;

@Component
public class EmprestimoMapper {
    public EmprestimoDTO paraDTO(EmprestimoModel e) {
        EmprestimoDTO dto = new EmprestimoDTO();
        dto.setId(e.getId());
        dto.setUsuarioMatricula(e.getUsuarioModel().getMatricula());
        dto.setUsuarioNome(e.getUsuarioModel().getNome());
        dto.setLivroId(e.getLivroModel().getId());
        dto.setLivroTitulo(e.getLivroModel().getTitulo());
        dto.setDataInicio(e.getDataInicio());
        dto.setDataPrevistaDevolucao(e.getDataPrevistaDevolucao());
        dto.setDataDevolucao(e.getDataDevolucao());
        dto.setDevolvido(e.isDevolvido());
        return dto;
    }
}
