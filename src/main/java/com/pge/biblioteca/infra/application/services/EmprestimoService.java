package com.pge.biblioteca.infra.application.services;
import com.pge.biblioteca.infra.application.dto.EmprestimoDTO;
import com.pge.biblioteca.infra.application.mapper.EmprestimoMapper;
import com.pge.biblioteca.infra.domain.models.EmprestimoModel;
import com.pge.biblioteca.infra.domain.models.LivroModel;
import com.pge.biblioteca.infra.domain.models.UsuarioModel;
import com.pge.biblioteca.infra.domain.repositories.EmprestimoModelRepository;
import com.pge.biblioteca.infra.domain.repositories.LivroModelRepository;
import com.pge.biblioteca.infra.domain.repositories.UsuarioModelRepository;
import com.pge.biblioteca.infra.domain.services.EmprestimoModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmprestimoService {
    @Autowired
    private EmprestimoModelService emprestimoModelService;

    @Autowired
    private EmprestimoModelRepository emprestimoModelRepository;
    @Autowired
    private LivroModelRepository livroModelRepository;
    @Autowired
    private UsuarioModelRepository usuarioModelRepository;
    @Autowired
    private EmprestimoMapper mapper;

    public EmprestimoModel criar(UsuarioModel usuario, LivroModel livro, LocalDate dataInicio, int diasPrevistos, List<EmprestimoModel> emprestimosAtivos) {
        emprestimoModelService.validarDisponibilidade(livro);
        emprestimoModelService.validarLimiteEmprestimos(usuario, emprestimosAtivos);

        EmprestimoModel e = new EmprestimoModel();
        e.setUsuarioModel(usuario);
        e.setLivroModel(livro);
        e.setDataInicio(dataInicio);
        e.setDataPrevistaDevolucao(emprestimoModelService.calcularDataMaximaDevolucao(dataInicio, diasPrevistos));
        e.setDevolvido(false);
        return e;
    }

    @Transactional
    public EmprestimoDTO criarEmprestimo(String matriculaUsuario, Long livroId, int diasPrevistos) {
        UsuarioModel u = usuarioModelRepository.buscarPorMatricula(matriculaUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        LivroModel l = livroModelRepository.buscarPorId(livroId)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        List<EmprestimoModel> ativos = emprestimoModelRepository.listarAtivosPorUsuarioModelMatricula(matriculaUsuario);

        EmprestimoModel emp = criar(u, l, LocalDate.now(), diasPrevistos, ativos);

        // atualizar quantidade
        l.setQuantidadeDisponivel(l.getQuantidadeDisponivel() - 1);
        livroModelRepository.salvar(l);

        EmprestimoModel salvo = emprestimoModelRepository.salvar(emp);
        return mapper.paraDTO(salvo);
    }

    @Transactional
    public EmprestimoDTO devolver(Long idEmprestimo) {
        EmprestimoModel e = emprestimoModelRepository.buscarPorId(idEmprestimo)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));

        if (e.isDevolvido()) throw new RuntimeException("Empréstimo já devolvido");

        e.setDevolvido(true);
        e.setDataDevolucao(LocalDate.now());

        LivroModel l = e.getLivroModel();
        l.setQuantidadeDisponivel(l.getQuantidadeDisponivel() + 1);
        livroModelRepository.salvar(l);

        EmprestimoModel salvo = emprestimoModelRepository.salvar(e);
        return mapper.paraDTO(salvo);
    }

    public List<EmprestimoDTO> listarPorUsuario(String matricula) {
        return emprestimoModelRepository.listarPorUsuarioModelMatricula(matricula)
                .stream().map(mapper::paraDTO).toList();
    }

    public List<EmprestimoDTO> listarEmprestimos() {
        return emprestimoModelRepository.listarEmprestimos()
                .stream().map(mapper::paraDTO).toList();
    }

}
