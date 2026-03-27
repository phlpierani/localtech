package com.pierai.locatech.locatech.repository;

import com.pierai.locatech.locatech.entities.Veiculo;

import java.util.List;
import java.util.Optional;

public interface VeiculoRepository {

    Optional<Veiculo> findById(Long id);

    List<Veiculo> findAll(int size, int offset); // Retorna uma lista de veículos com paginação

    Integer save(Veiculo veiculo); // Retorna o ID do veículo salvo

    Integer update(Veiculo veiculo, Long id); // Retorna o número de registros atualizados

    Integer deleteById(Long id); // Retorna o número de registros deletados


}
