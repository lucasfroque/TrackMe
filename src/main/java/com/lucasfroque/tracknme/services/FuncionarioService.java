package com.lucasfroque.tracknme.services;

import com.lucasfroque.tracknme.entities.CepResponse;
import com.lucasfroque.tracknme.entities.Funcionario;
import com.lucasfroque.tracknme.repositories.FuncionarioRepository;
import com.lucasfroque.tracknme.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    RestTemplate restTemplate = new RestTemplate();

    public Funcionario create(Funcionario funcionario) {
        CepResponse info = getInfoCep(funcionario.getCep());
        funcionario.setEstado(info.getUf());
        funcionario.setCidade(info.getLocalidade());
        funcionario.setBairro(info.getBairro());
        funcionario.setEndereco(info.getLogradouro());
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
        if(funcionario.getCep() != novoFuncionario.getCep()){
            CepResponse info = getInfoCep(novoFuncionario.getCep());
            funcionario.setEstado(info.getUf());
            funcionario.setCidade(info.getLocalidade());
            funcionario.setBairro(info.getBairro());
            funcionario.setEndereco(info.getLogradouro());
        }
        funcionario.setNome(novoFuncionario.getNome());
        funcionario.setIdade(novoFuncionario.getIdade());
        funcionario.setCep(novoFuncionario.getCep());
        funcionario.setSexo(novoFuncionario.getSexo());
    }

    public CepResponse getInfoCep(String cep){
        CepResponse response = restTemplate.getForObject("https://viacep.com.br/ws/"+cep+"/json/", CepResponse.class);
        return response;
    }

}
