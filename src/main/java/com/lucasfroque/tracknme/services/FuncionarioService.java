package com.lucasfroque.tracknme.services;

import com.lucasfroque.tracknme.entities.Funcionario;
import com.lucasfroque.tracknme.repositories.FuncionarioRepository;
import com.lucasfroque.tracknme.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    public Funcionario create(Funcionario funcionario) {
        return repository.save(funcionario);
    }

    public List<Funcionario> findAll() {
        return repository.findAll();
    }

    public Funcionario findById(Long id) {
        Optional<Funcionario> response = repository.findById(id);
        return response.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Funcionario> findByCep(String cep){
        return repository.findByCep(cep);
    }

    public void delete(Long id){
        try {
            repository.deleteById(id);
        }
        catch(EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
    }
    public Funcionario update(Long id, Funcionario novoFuncionario) {
        try {
            Funcionario funcionario = repository.findById(id).get();
            updateData(funcionario, novoFuncionario);
            repository.save(funcionario);
            return funcionario;
        }
        catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
    private void updateData(Funcionario funcionario, Funcionario novoFuncionario) {
        funcionario.setNome(novoFuncionario.getNome());
        funcionario.setIdade(novoFuncionario.getIdade());
        funcionario.setCep(novoFuncionario.getCep());
        funcionario.setSexo(novoFuncionario.getSexo());
    }


}
