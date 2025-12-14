package com.pge.biblioteca.rest.controllers;

import com.pge.biblioteca.infra.application.dto.LivroDTO;
import com.pge.biblioteca.infra.application.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<LivroDTO> criar(@RequestBody LivroDTO livroDTO) throws Exception {
        LivroDTO dto = servico.cadastrar(livroDTO);
        return ResponseEntity.ok(dto);
    }


}
