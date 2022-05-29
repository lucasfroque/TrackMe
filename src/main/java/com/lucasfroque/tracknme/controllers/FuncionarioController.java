package com.lucasfroque.tracknme.controllers;


import com.lucasfroque.tracknme.entities.Funcionario;
import com.lucasfroque.tracknme.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;


    @PostMapping
    public ResponseEntity<Funcionario> create(@RequestBody Funcionario funcionario){
        Funcionario response = service.create(funcionario);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> findAll(){
        List<Funcionario> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Funcionario> findById(@PathVariable Long id){
        Funcionario response = service.findById(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/cep/{cep}")
    public ResponseEntity<List<Funcionario>> findByCep(@PathVariable String cep){
        List<Funcionario> list = service.findByCep(cep);
        return ResponseEntity.ok().body(list);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<Funcionario> update(@PathVariable Long id, @RequestBody Funcionario funcionario){
        Funcionario response = service.update(id, funcionario);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
