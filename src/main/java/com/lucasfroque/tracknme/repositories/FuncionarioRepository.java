package com.lucasfroque.tracknme.repositories;

import com.lucasfroque.tracknme.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
;import java.util.List;

public interface FuncionarioRepository extends  JpaRepository<Funcionario, Long>{

    public List<Funcionario> findByCep(String cep);
}
