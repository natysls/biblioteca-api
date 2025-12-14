package com.pge.biblioteca.rest.controllers;

import com.pge.biblioteca.infra.application.dto.LivroDTO;
import com.pge.biblioteca.infra.application.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private LivroService servico;

    @GetMapping
    public ResponseEntity<List<LivroDTO>> listarTodosLivros() {
        return ResponseEntity.ok(servico.listarTodosLivros());
    }
    @PostMapping("/criar")
    public ResponseEntity<LivroDTO> criar(@RequestBody LivroDTO livroDTO) {
        LivroDTO dto = servico.cadastrar(livroDTO);
        return ResponseEntity.ok(dto);
    }

    @PatchMapping("{isbn}")
    public ResponseEntity<LivroDTO> atualizarLivro(@PathVariable("isbn") String isbn, @RequestBody Map<String, Object> campos) {
        LivroDTO dto = servico.atualizarParcial(isbn, campos);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<LivroDTO>> listarLivrosDisponiveis() {
        return ResponseEntity.ok(servico.listarLivrosDisponiveis());
    }


}
