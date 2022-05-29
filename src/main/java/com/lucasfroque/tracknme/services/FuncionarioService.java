package com.lucasfroque.tracknme.services;

import com.lucasfroque.tracknme.entities.Funcionario;
import com.lucasfroque.tracknme.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;


    public List<Funcionario> findAll() {
        return repository.findAll();
    }

}
