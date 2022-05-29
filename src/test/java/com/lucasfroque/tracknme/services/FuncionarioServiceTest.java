package com.lucasfroque.tracknme.services;

import com.lucasfroque.tracknme.entities.Funcionario;
import com.lucasfroque.tracknme.repositories.FuncionarioRepository;
import com.lucasfroque.tracknme.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class FuncionarioServiceTest {

    public static final long ID = 1L;
    public static final String NOME = "Lucas";
    public static final int IDADE = 21;
    public static final String CEP = "22793-270";
    public static final String SEXO = "masculino";
    public static final String ENDERECO = "Rua Abel da Silva";
    public static final String BAIRRO = "Barra da Tijuca";
    public static final String CIDADE = "Rio de Janeiro";
    public static final String ESTADO = "RJ";

    @Mock
    private FuncionarioRepository repository;

    @InjectMocks
    private FuncionarioService service;

    private Funcionario funcionario;
    private Optional<Funcionario> optionalFuncionario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startFuncionario();
    }

    @Test
    void whenCreateThenReturnSucess() {
        when(repository.save(any())).thenReturn(funcionario);
        Funcionario response = service.create(funcionario);

        assertNotNull(response);
        assertEquals(Funcionario.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(CEP, response.getCep());
        assertEquals(SEXO, response.getSexo());
        assertEquals(ENDERECO, response.getEndereco());
        assertEquals(BAIRRO, response.getBairro());
        assertEquals(CIDADE, response.getCidade());
        assertEquals(ESTADO, response.getEstado());
    }

    @Test
    void whenFindAllThenReturnAnListOfFuncionarios() {
        when(repository.findAll()).thenReturn(List.of(funcionario));

        List<Funcionario> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Funcionario.class, response.get(0).getClass());
        assertEquals(ID, response.get(0).getId());
        assertEquals(NOME, response.get(0).getNome());
        assertEquals(CEP, response.get(0).getCep());
        assertEquals(SEXO, response.get(0).getSexo());
        assertEquals(ENDERECO, response.get(0).getEndereco());
        assertEquals(BAIRRO, response.get(0).getBairro());
        assertEquals(CIDADE, response.get(0).getCidade());
        assertEquals(ESTADO, response.get(0).getEstado());
    }

    @Test
    void whenFindByIdThenReturnFuncionario() {
        when(repository.findById(anyLong())).thenReturn(optionalFuncionario);

        Funcionario response = service.findById(ID);

        assertNotNull(response);
        assertEquals(Funcionario.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(CEP, response.getCep());
        assertEquals(SEXO, response.getSexo());
        assertEquals(ENDERECO, response.getEndereco());
        assertEquals(BAIRRO, response.getBairro());
        assertEquals(CIDADE, response.getCidade());
        assertEquals(ESTADO, response.getEstado());
    }

    @Test
    void whenFindByIdThenReturnResourceNotFoundException(){
        try{
            service.findById(ID);
        }catch (Exception ex){
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals("id " + ID + " nao encontrado.", ex.getMessage());
        }
    }

    @Test
    void whenFindByCepThenReturnFuncionario() {
        when(repository.findByCep(anyString())).thenReturn(List.of(funcionario));

        List<Funcionario> response = service.findByCep(CEP);

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Funcionario.class, response.get(0).getClass());
        assertEquals(ID, response.get(0).getId());
        assertEquals(NOME, response.get(0).getNome());
        assertEquals(CEP, response.get(0).getCep());
        assertEquals(SEXO, response.get(0).getSexo());
        assertEquals(ENDERECO, response.get(0).getEndereco());
        assertEquals(BAIRRO, response.get(0).getBairro());
        assertEquals(CIDADE, response.get(0).getCidade());
        assertEquals(ESTADO, response.get(0).getEstado());
    }

    @Test
    void whenDeleteThenReturnSucess() {
        when(repository.findById(anyLong())).thenReturn(optionalFuncionario);
        doNothing().when(repository).deleteById(anyLong());
        service.delete(ID);
        verify(repository, times(1)).deleteById(anyLong());
    }

    @Test
    void whenDeleteThenReturnObjectNotFoundException() {
        when(repository.findById(anyLong())).thenThrow(new ResourceNotFoundException(ID));
        try {
            service.delete(ID);
        } catch (Exception ex) {
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals("id " + ID + " nao encontrado.", ex.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnFuncionarioUpdated() {
        Funcionario newFuncionario = new Funcionario(ID, NOME, 25, "78095150", SEXO, ENDERECO, BAIRRO, CIDADE, ESTADO);

        when(repository.findById(anyLong())).thenReturn(optionalFuncionario);
        Funcionario response = service.update(1L, newFuncionario);

        Assertions.assertEquals(Funcionario.class, response.getClass());
        Assertions.assertEquals(funcionario.getNome(), response.getNome());
        Assertions.assertNotEquals(funcionario.getIdade(), response.getIdade());
        Assertions.assertNotEquals(funcionario.getCep(), response.getCep());

    }

    private void startFuncionario(){
        funcionario = new Funcionario(ID, NOME, IDADE, CEP, SEXO, ENDERECO, BAIRRO, CIDADE, ESTADO);
        optionalFuncionario = Optional.of(new Funcionario(1L, NOME, IDADE, CEP, SEXO, ENDERECO, BAIRRO, CIDADE, ESTADO));
    }
}