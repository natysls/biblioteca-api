package com.pge.biblioteca.infra.application.services;

import com.pge.biblioteca.infra.application.dto.LivroDTO;
import com.pge.biblioteca.infra.application.mapper.LivroMapper;
import com.pge.biblioteca.infra.domain.models.LivroModel;
import com.pge.biblioteca.infra.domain.repositories.LivroModelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class LivroServiceTest {

    @Mock
    private LivroModelRepository livroModelRepository;

    @Mock
    private LivroMapper mapper;

    @Autowired
    @InjectMocks
    private LivroService livroService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Deve cadastrar livro com ID nulo, mesmo que venha preenchido")
    void deveCadastrarLivroComIdNulo() {
        // Arrange
        LivroDTO dtoEntrada = new LivroDTO();
        dtoEntrada.setId(10L); // ID vindo preenchido indevidamente

        LivroModel model = new LivroModel();
        LivroModel modelSalvo = new LivroModel();
        modelSalvo.setId(1L);

        LivroDTO dtoRetorno = new LivroDTO();
        dtoRetorno.setId(1L);

        Mockito.when(mapper.paraModelo(ArgumentMatchers.any(LivroDTO.class))).thenReturn(model);
        Mockito.when(livroModelRepository.salvar(model)).thenReturn(modelSalvo);
        Mockito.when(mapper.modeloParaDTO(modelSalvo)).thenReturn(dtoRetorno);

        // Act
        LivroDTO resultado = livroService.cadastrar(dtoEntrada);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());

        // Verifica que o ID foi zerado antes de salvar
        ArgumentCaptor<LivroDTO> dtoCaptor = ArgumentCaptor.forClass(LivroDTO.class);
        Mockito.verify(mapper).paraModelo(dtoCaptor.capture());
        assertNull(dtoCaptor.getValue().getId());

        Mockito.verify(livroModelRepository).salvar(model);
    }

    @Test
    @DisplayName("Deve salvar livro com sucesso")
    void deveSalvarLivroComSucesso() {
        // Arrange
        LivroDTO dto = new LivroDTO();
        dto.setId(null);

        LivroModel model = new LivroModel();
        LivroModel modelSalvo = new LivroModel();
        modelSalvo.setId(5L);

        LivroDTO dtoRetorno = new LivroDTO();
        dtoRetorno.setId(5L);

        Mockito.when(mapper.paraModelo(dto)).thenReturn(model);
        Mockito.when(livroModelRepository.salvar(model)).thenReturn(modelSalvo);
        Mockito.when(mapper.modeloParaDTO(modelSalvo)).thenReturn(dtoRetorno);

        // Act
        LivroDTO resultado = livroService.salvar(dto);

        // Assert
        assertNotNull(resultado);
        assertEquals(5L, resultado.getId());

        Mockito.verify(mapper).paraModelo(dto);
        Mockito.verify(livroModelRepository).salvar(model);
        Mockito.verify(mapper).modeloParaDTO(modelSalvo);
    }

}