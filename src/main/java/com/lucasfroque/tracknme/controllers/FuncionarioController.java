package com.lucasfroque.tracknme.controllers;


import com.lucasfroque.tracknme.entities.Funcionario;
import com.lucasfroque.tracknme.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;



    @GetMapping
    public ResponseEntity<List<Funcionario>> findAll(){
        List<Funcionario> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

}
