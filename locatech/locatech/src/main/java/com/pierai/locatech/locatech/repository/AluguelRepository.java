package com.pierai.locatech.locatech.repository;

import com.pierai.locatech.locatech.entities.Aluguel;
import com.pierai.locatech.locatech.entities.Pessoa;

import java.util.List;
import java.util.Optional;

public interface AluguelRepository {

    Optional<Aluguel> findById(Long id);

    List<Aluguel> findAll(int size, int offset);

    Integer save(Aluguel aluguel);

    Integer update(Aluguel aluguel, Long id);

    Integer deleteById(Long id);
}
