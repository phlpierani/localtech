package com.pierai.locatech.locatech.repository;

import com.pierai.locatech.locatech.entities.Pessoa;
import com.pierai.locatech.locatech.entities.Veiculo;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PessoaRepositoryImpl implements  PessoaRepository {

    private final JdbcClient jdbcClient;

    public PessoaRepositoryImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Pessoa> findById(Long id) {
        return this.jdbcClient.sql("SELECT * FROM pessoa WHERE id = :id")
                .param("id", id)
                .query(Pessoa.class)
                .optional();
        // Aqui você pode usar jdbcClient para executar a consulta SQL e mapear o resultado para um objeto Veiculo
    }

    @Override
    public List<Pessoa> findAll(int size, int offset) {
        return this.jdbcClient
                .sql("SELECT * FROM pessoa LIMIT :size OFFSET :offset")
                .param("size", size)
                .param("offset", offset)
                .query(Pessoa.class)
                .list();
        // Aqui você pode usar jdbcClient para executar a consulta SQL e mapear os resultados para uma lista de objetos Veiculo, aplicando a paginação com LIMIT e OFFSET
    }

    @Override
    public Integer save(Pessoa pessoa) {
        return this.jdbcClient
                .sql("INSERT INTO pessoa (nome, cpf, telefone, email)" +
                        " VALUES (:nome, :cpf, :telefone, :email)")
                .param("nome", pessoa.getNome())
                .param("cpf", pessoa.getCpf())
                .param("telefone", pessoa.getTelefone())
                .param("email", pessoa.getEmail())
                .update();
        // Aqui você pode usar jdbcClient para executar a consulta SQL de inserção e retornar o ID do veículo salvo.
    }

    @Override
    public Integer update(Pessoa pessoa, Long id) {
        return this.jdbcClient
                .sql("UPDATE pessoa SET nome = :nome, cpf = :cpf, telefone = :telefone, email = :email WHERE id = :id")
                .param("id", id)
                .param("nome", pessoa.getNome())
                .param("cpf", pessoa.getCpf())
                .param("telefone", pessoa.getTelefone())
                .param("email", pessoa.getEmail())
                .update();
        // Aqui você pode usar jdbcClient para executar a consulta SQL de atualização e retornar o número
    }

    @Override
    public Integer deleteById(Long id) {
        return this.jdbcClient
                .sql("DELETE FROM pessoa WHERE id = :id")
                .param("id", id)
                .update();
        // Aqui você pode usar jdbcClient para executar a consulta SQL de exclusão e retornar o número de registros deletados.
    }
}
