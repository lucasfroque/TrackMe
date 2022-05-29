package com.lucasfroque.tracknme.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasfroque.tracknme.entities.Funcionario;
import com.lucasfroque.tracknme.services.FuncionarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(FuncionarioController.class)
class FuncionarioControllerTest {

    public static final long ID = 1L;
    public static final String NOME = "Lucas";
    public static final int IDADE = 21;
    public static final String CEP = "1234567";
    public static final String SEXO = "masculino";
    public static final String ENDERECO = "Rua A";
    public static final String BAIRRO = "Bairro B";
    public static final String CIDADE = "Cidade C";
    public static final String ESTADO = "Estado D";

    @MockBean
    private FuncionarioService service;

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper mapper;

    private Funcionario funcionario;
    private Funcionario funcionario2;

    @BeforeEach
    void setUp() {
    startFuncionario();
    }

    @Test
    void whenCreateThenReturnSucess() throws Exception {
        when(service.create(any())).thenReturn(funcionario);

        mockMvc.perform(post("/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(funcionario)))
                .andExpect(status().isCreated())
                .andExpect(header().string(HttpHeaders.LOCATION,"http://localhost/funcionarios/1"));
    }

    @Test
    void whenFindAllThenReturnAnListOfFuncionarios() throws Exception {
        when(service.findAll()).thenReturn(List.of(funcionario, funcionario));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/funcionarios"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{}, {}]"));

    }

    @Test
    void  whenFindByIdThenReturnFuncionario() throws Exception {
        when(service.findById(anyLong())).thenReturn(funcionario);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/funcionarios/{id}", ID))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(funcionario)))
                .andExpect(jsonPath("$.nome").value(NOME));
    }

    @Test
    void whenFindByCepThenReturnFuncionario() throws Exception {
        when(service.findByCep(anyString())).thenReturn(List.of(funcionario));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/funcionarios/cep/{cep}", CEP))
                .andExpect(status().isOk())
                .andExpect(content().json("[{}]"));

    }

    @Test
    void whenUpdateThenReturnFuncionarioUpdated() throws Exception {
        when(service.update(anyLong(), any())).thenReturn(funcionario);

        mockMvc.perform(put("/funcionarios/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(funcionario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(NOME));
    }

    @Test
    void whenDeleteThenReturnSucess() throws Exception {

        mockMvc.perform(delete("/funcionarios/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNoContent());
    }
    private void startFuncionario(){
        funcionario = new Funcionario(ID, NOME, IDADE, CEP, SEXO, ENDERECO, BAIRRO, CIDADE, ESTADO);
    }
}