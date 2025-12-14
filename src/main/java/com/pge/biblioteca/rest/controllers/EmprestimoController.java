package com.pge.biblioteca.rest.controllers;

import com.pge.biblioteca.infra.application.dto.EmprestimoDTO;
import com.pge.biblioteca.infra.application.services.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {
    @Autowired
    private EmprestimoService servico;

    @GetMapping
    public ResponseEntity<List<EmprestimoDTO>> listarEmprestimos() {
        return ResponseEntity.ok(servico.listarEmprestimos());
    }
    @PostMapping("/criar")
    public ResponseEntity<EmprestimoDTO> criar(@RequestParam String matricula, @RequestParam Long livroId, @RequestParam int diasPrevistos) throws Exception {
        EmprestimoDTO dto = servico.criarEmprestimo(matricula, livroId, diasPrevistos);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/devolver/{id}")
    public ResponseEntity<EmprestimoDTO> devolver(@PathVariable Long id) {
        EmprestimoDTO dto = servico.devolver(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/usuario/{matricula}")
    public ResponseEntity<List<EmprestimoDTO>> listarPorUsuario(@PathVariable String matricula) {
        return ResponseEntity.ok(servico.listarPorUsuario(matricula));
    }

}
