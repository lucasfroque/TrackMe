package com.lucasfroque.tracknme.repositories;

import com.lucasfroque.tracknme.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
;

public interface FuncionarioRepository extends  JpaRepository<Funcionario, Long>{

}
