package com.pierai.locatech.locatech.repository;

import com.pierai.locatech.locatech.entities.Pessoa;
import com.pierai.locatech.locatech.entities.Veiculo;

import java.util.List;
import java.util.Optional;

public interface PessoaRepository {

    Optional<Pessoa> findById(Long id);

    List<Pessoa> findAll(int size, int offset);

    Integer save(Pessoa pessoa);

    Integer update(Pessoa pessoa, Long id);

    Integer deleteById(Long id);

}
