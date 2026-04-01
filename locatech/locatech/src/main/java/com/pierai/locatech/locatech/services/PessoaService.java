package com.pierai.locatech.locatech.services;

import com.pierai.locatech.locatech.entities.Pessoa;
import com.pierai.locatech.locatech.entities.Veiculo;
import com.pierai.locatech.locatech.repository.PessoaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<Pessoa> findAllPessoas(int page, int size) {
        int offset = (page - 1) * size;
        return pessoaRepository.findAll(size, offset);
    }

    public Optional<Pessoa> findVeiculoById(Long id) {
        return this.pessoaRepository.findById(id);
    }

    public void savePessoa(Pessoa pessoa) {
        var save = this.pessoaRepository.save(pessoa);
        Assert.state(save == 1, "Erro ao salvar o pessoa" + pessoa.getNome());
    }

    public void updatePessoa(Pessoa pessoa,
                              Long id) {
        var update = this.pessoaRepository.update(pessoa, id);
        if (update == 0){
            throw new RuntimeException("Pessoa com id: " + id + " não encontrado para atualização");
        }
    }

    public void deletePessoa(Long id) {
        var delete = this.pessoaRepository.deleteById(id);
        if (delete == 0) {
            throw new RuntimeException("Pessoa não encontrado para deleção");
        }
    }
}
