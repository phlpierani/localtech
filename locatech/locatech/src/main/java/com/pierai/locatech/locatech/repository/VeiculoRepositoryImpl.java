package com.pierai.locatech.locatech.repository;

import com.pierai.locatech.locatech.entities.Veiculo;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class VeiculoRepositoryImpl implements VeiculoRepository {

    private final JdbcClient jdbcClient;

    public VeiculoRepositoryImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Veiculo> findById(Long id) {
        return this.jdbcClient.sql("SELECT * FROM veiculo WHERE id = :id")
                .param("id", id)
                .query(Veiculo.class)
                .optional();
        // Aqui você pode usar jdbcClient para executar a consulta SQL e mapear o resultado para um objeto Veiculo
    }

    @Override
    public List<Veiculo> findAll(int size, int offset) {
        return this.jdbcClient
                .sql("SELECT * FROM veiculo LIMIT :size OFFSET :offset")
                .param("size", size)
                .param("offset", offset)
                .query(Veiculo.class)
                .list();
                // Aqui você pode usar jdbcClient para executar a consulta SQL e mapear os resultados para uma lista de objetos Veiculo, aplicando a paginação com LIMIT e OFFSET
    }

    @Override
    public Integer save(Veiculo veiculo) {
        return this.jdbcClient
                .sql("INSERT INTO veiculo (marca, modelo, placa, cor, ano, valor_diaria)" +
                        " VALUES (:marca, :modelo, :placa, :cor, :ano, :valorDiaria)")
                .param("marca", veiculo.getMarca())
                .param("modelo", veiculo.getModelo())
                .param("placa", veiculo.getPlaca())
                .param("cor", veiculo.getCor())
                .param("ano", veiculo.getAno())
                .param("valorDiaria", veiculo.getValorDiaria())
                .update();
        // Aqui você pode usar jdbcClient para executar a consulta SQL de inserção e retornar o ID do veículo salvo.
    }

    @Override
    public Integer update(Veiculo veiculo, Long id) {
        return this.jdbcClient
                .sql("UPDATE veiculo SET marca = :marca, modelo = :modelo, placa = :placa, cor = :cor, ano = :ano, valor_diaria = :valorDiaria WHERE id = :id")
                .param("marca", veiculo.getMarca())
                .param("modelo", veiculo.getModelo())
                .param("placa", veiculo.getPlaca())
                .param("cor", veiculo.getCor())
                .param("ano", veiculo.getAno())
                .param("valorDiaria", veiculo.getValorDiaria())
                .param("id", id)
                .update();
        // Aqui você pode usar jdbcClient para executar a consulta SQL de atualização e retornar o número
    }

    @Override
    public Integer deleteById(Long id) {
        return this.jdbcClient
                .sql("DELETE FROM veiculo WHERE id = :id")
                .param("id", id)
                .update();
        // Aqui você pode usar jdbcClient para executar a consulta SQL de exclusão e retornar o número de registros deletados.
    }
}
