package com.pierai.locatech.locatech.services;

import com.pierai.locatech.locatech.entities.Aluguel;
import com.pierai.locatech.locatech.entities.Pessoa;
import com.pierai.locatech.locatech.repository.AluguelRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class AluguelService {

    private final AluguelRepository aluguelRepository;

    public AluguelService(AluguelRepository aluguelRepository) {
        this.aluguelRepository = aluguelRepository;
     }

    public List<Aluguel> findAllAlugueis(int page, int size) {
        int offset = (page - 1) * size;
        return aluguelRepository.findAll(size, offset);
    }

    public Optional<Aluguel> findAluguelById(Long id) {
        return this.aluguelRepository.findById(id);
    }

    public void saveAluguel(Aluguel aluguel) {
        var save = this.aluguelRepository.save(aluguel);
        Assert.state(save == 1, "Erro ao salvar o Aluguel" + aluguel.getPessoaId());
    }

    public void updateAluguel(Aluguel aluguel,
                             Long id) {
        var update = this.aluguelRepository.update(aluguel, id);
        if (update == 0){
            throw new RuntimeException("Aluguel com id: " + id + " não encontrado para atualização");
        }
    }

    public void deleteAluguel(Long id) {
        var delete = this.aluguelRepository.deleteById(id);
        if (delete == 0) {
            throw new RuntimeException("Aluguel não encontrado para deleção");
        }
    }
}
