package com.pge.biblioteca.infra.jpa.adaptars;

import com.pge.biblioteca.infra.domain.models.LivroModel;
import com.pge.biblioteca.infra.domain.models.UsuarioModel;
import com.pge.biblioteca.infra.domain.repositories.UsuarioModelRepository;
import com.pge.biblioteca.infra.jpa.entities.LivroEntity;
import com.pge.biblioteca.infra.jpa.entities.UsuarioEntity;
import com.pge.biblioteca.infra.jpa.repositories.UsuarioEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioEntityAdapter implements UsuarioModelRepository {
    @Autowired
    private UsuarioEntityRepository usuarioEntityRepository;

    @Override
    public UsuarioModel salvar(UsuarioModel usuarioModel) {
        UsuarioEntity ent = paraEntidade(usuarioModel);
        UsuarioEntity salvo = usuarioEntityRepository.save(ent);
        return paraModelo(salvo);
    }

    @Override
    public Optional<UsuarioModel> buscarPorMatricula(String matricula) {
        return usuarioEntityRepository.findByMatricula(matricula)
                .map(this::paraModelo);
    }

    @Override
    public Optional<UsuarioModel> listarTodos() {
        return usuarioEntityRepository.findAll().stream()
                .map(this::paraModelo)
                .findFirst();
    }

    private UsuarioEntity paraEntidade(UsuarioModel e) {
        if(e == null) return null;
        UsuarioEntity ent = new UsuarioEntity();
        ent.setId(e.getId());
        ent.setNome(e.getNome());
        ent.setMatricula(e.getMatricula());

        return ent;
    }

    private UsuarioModel paraModelo(UsuarioEntity ent) {
        if (ent == null) return null;
        UsuarioModel e = new UsuarioModel();
        e.setId(ent.getId());
        e.setNome(ent.getNome());
        e.setMatricula(ent.getMatricula());

        return e;
    }

}
