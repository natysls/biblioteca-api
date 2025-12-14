package com.pge.biblioteca.infra.jpa.adaptars;

import com.pge.biblioteca.infra.domain.models.EmprestimoModel;
import com.pge.biblioteca.infra.domain.models.LivroModel;
import com.pge.biblioteca.infra.domain.models.UsuarioModel;
import com.pge.biblioteca.infra.domain.repositories.EmprestimoModelRepository;
import com.pge.biblioteca.infra.jpa.entities.EmprestimoEntity;
import com.pge.biblioteca.infra.jpa.entities.LivroEntity;
import com.pge.biblioteca.infra.jpa.entities.UsuarioEntity;
import com.pge.biblioteca.infra.jpa.repositories.EmprestimoEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmprestimoEntityAdapter implements EmprestimoModelRepository {
    @Autowired
    private EmprestimoEntityRepository emprestimoEntityRepository;

    @Override
    public EmprestimoModel salvar(EmprestimoModel emprestimoModel) {
        EmprestimoEntity ent = modelParaEntidade(emprestimoModel);
        EmprestimoEntity salvo = emprestimoEntityRepository.save(ent);
        return entityParaModelo(salvo);
    }

    @Override
    public Optional<EmprestimoModel> buscarPorId(Long id) {
        return emprestimoEntityRepository.findById(id).map(this::entityParaModelo);
    }

    @Override
    public List<EmprestimoModel> listarPorUsuarioModelMatricula(String matricula) {
        return emprestimoEntityRepository.findByUsuarioEntityMatricula(matricula).stream()
                .map(this::entityParaModelo)
                .toList();
    }

    @Override
    public List<EmprestimoModel> listarAtivosPorUsuarioModelMatricula(String matricula) {
        return emprestimoEntityRepository.findByUsuarioEntityMatriculaAndDevolvidoFalse(matricula).stream()
                .map(this::entityParaModelo)
                .toList();
    }

    @Override
    public List<EmprestimoModel> listarEmprestimos() {
        return emprestimoEntityRepository.findAll().stream()
                .map(this::entityParaModelo)
                .toList();
    }

    private EmprestimoEntity modelParaEntidade(EmprestimoModel e) {
        if(e == null) return null;
        EmprestimoEntity ent = new EmprestimoEntity();
        ent.setId(e.getId());
        ent.setDataPrevistaDevolucao(e.getDataPrevistaDevolucao());
        ent.setDataDevolucao(e.getDataDevolucao());
        ent.setDevolvido(e.isDevolvido());

        UsuarioEntity ue = new UsuarioEntity();
        ue.setId(e.getUsuarioModel().getId());
        ue.setMatricula(e.getUsuarioModel().getMatricula());
        ue.setNome(e.getUsuarioModel().getNome());
        ue.setEmail(e.getUsuarioModel().getEmail());
        ent.setUsuarioEntity(ue);

        LivroEntity le = new LivroEntity();
        le.setId(e.getLivroModel().getId());
        le.setTitulo(e.getLivroModel().getTitulo());
        le.setAutor(e.getLivroModel().getAutor());
        le.setIsbn(e.getLivroModel().getIsbn());
        le.setQuantidadeDisponivel(e.getLivroModel().getQuantidadeDisponivel());
        ent.setLivroEntity(le);

        return ent;
    }

    private EmprestimoModel entityParaModelo(EmprestimoEntity ent) {
        if (ent == null) return null;
        EmprestimoModel e = new EmprestimoModel();
        e.setId(ent.getId());
        e.setDataInicio(ent.getDataInicio());
        e.setDataPrevistaDevolucao(ent.getDataPrevistaDevolucao());
        e.setDataDevolucao(ent.getDataDevolucao());
        e.setDevolvido(ent.isDevolvido());

        UsuarioModel u = new UsuarioModel();
        u.setId(ent.getUsuarioEntity().getId());
        u.setMatricula(ent.getUsuarioEntity().getMatricula());
        u.setNome(ent.getUsuarioEntity().getNome());
        u.setEmail(ent.getUsuarioEntity().getEmail());
        e.setUsuarioModel(u);

        LivroModel l = new LivroModel();
        l.setId(ent.getLivroEntity().getId());
        l.setTitulo(ent.getLivroEntity().getTitulo());
        l.setAutor(ent.getLivroEntity().getAutor());
        l.setIsbn(ent.getLivroEntity().getIsbn());
        l.setQuantidadeDisponivel(ent.getLivroEntity().getQuantidadeDisponivel());
        e.setLivroModel(l);

        return e;
    }
}
