package com.pierai.locatech.locatech.repository;

import com.pierai.locatech.locatech.entities.Aluguel;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class AluguelRepositoryImpl implements AluguelRepository {
    private final JdbcClient jdbcClient;

    public AluguelRepositoryImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Aluguel> findById(Long id) {
        return this.jdbcClient.sql("SELECT a.id, a.pessoa_id, a.veiculo_id, a.data_inicio, a.data_fim, a.valor_total " +
                        "p.nome AS pessoa_nome, p.cpf AS pessoas_cpf, " +
                        "v.modelo AS veiculo_modelo, v.placa AS veiculo_placa " +
                        "FROM alugueis a " +
                        "INNER JOIN pessoa p ON a.pessoa_id = p.id " +
                        "INNER JOIN veiculo v ON a.veiculo_id = v.id " +
                        "WHERE a.id = :id")
                .param("id", id)
                .query(Aluguel.class)
                .optional();
        // Aqui você pode usar jdbcClient para executar a consulta SQL e mapear o resultado para um objeto Veiculo
    }

    @Override
    public List<Aluguel> findAll(int size, int offset) {
        return this.jdbcClient
                .sql("SELECT a.id, a.pessoa_id, a.veiculo_id, a.data_inicio, a.data_fim, a.valor_total " +
                        "p.nome AS pessoa_nome, p.cpf AS pessoas_cpf, " +
                        "v.modelo AS veiculo_modelo, v.placa AS veiculo_placa " +
                        "FROM alugueis a " +
                        "INNER JOIN pessoa p ON a.pessoa_id = p.id " +
                        "INNER JOIN veiculo v ON a.veiculo_id = v.id " +
                        "LIMIT :size OFFSET :offset")
                .param("size", size)
                .param("offset", offset)
                .query(Aluguel.class)
                .list();
        // Aqui você pode usar jdbcClient para executar a consulta SQL e mapear os resultados para uma lista de objetos Veiculo, aplicando a paginação com LIMIT e OFFSET
    }

    @Override
    public Integer save(Aluguel aluguel) {
        return this.jdbcClient
                .sql("INSERT INTO alugueis (pessoa_id, veiculo_id, data_inicio, data_fim, valor_total)" +
                        "VALUES (:pessoa_id, :veiculo_id, :data_inicio, :data_fim, :valor_total)")
                .param("pessoa_id", aluguel.getPessoaId())
                .param("veiculo_id", aluguel.getVeiculoId())
                .param("data_inicio", aluguel.getDataInicio())
                .param("data_fim", aluguel.getDataFim())
                .param("valor_total", aluguel.getValorTotal())
                .update();
        // Aqui você pode usar jdbcClient para executar a consulta SQL de inserção e retornar o ID do veículo salvo.
    }

    @Override
    public Integer update(Aluguel aluguel, Long id) {
        return this.jdbcClient
                .sql("UPDATE alugueis SET pessoa_id = :pessoa_id, veiculo_id = :veiculo_id, data_inicio = :data_inicio, data_fim = :data_fim, valor_total = :valor_total WHERE id = :id")
                .param("pessoa_id", aluguel.getPessoaId())
                .param("veiculo_id", aluguel.getVeiculoId())
                .param("data_inicio", aluguel.getDataInicio())
                .param("data_fim", aluguel.getDataFim())
                .param("valor_total", aluguel.getValorTotal())
                .update();
        // Aqui você pode usar jdbcClient para executar a consulta SQL de atualização e retornar o número
    }

    @Override
    public Integer deleteById(Long id) {
        return this.jdbcClient
                .sql("DELETE FROM alugueis WHERE id = :id")
                .param("id", id)
                .update();
        // Aqui você pode usar jdbcClient para executar a consulta SQL de exclusão e retornar o número de registros deletados.
    }
}
