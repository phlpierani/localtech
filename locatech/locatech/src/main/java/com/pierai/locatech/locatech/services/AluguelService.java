package com.pierai.locatech.locatech.services;

import com.pierai.locatech.locatech.entities.Aluguel;
import com.pierai.locatech.locatech.entities.AluguelRequest;
import com.pierai.locatech.locatech.entities.Pessoa;
import com.pierai.locatech.locatech.exception.ResourceNotFoundException;
import com.pierai.locatech.locatech.repository.AluguelRepository;
import com.pierai.locatech.locatech.repository.PessoaRepository;
import com.pierai.locatech.locatech.repository.VeiculoRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AluguelService {

    private final AluguelRepository aluguelRepository;

    private final VeiculoRepository veiculoRepository;

    public AluguelService(AluguelRepository aluguelRepository, VeiculoRepository veiculoRepository) {
        this.aluguelRepository = aluguelRepository;
        this.veiculoRepository = veiculoRepository;
    }

    public List<Aluguel> findAllAlugueis(int page, int size) {
        int offset = (page - 1) * size;
        return aluguelRepository.findAll(size, offset);
    }

    public Optional<Aluguel> findAluguelById(Long id) {
        return Optional.of(this.aluguelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aluguel não encontrado com id: " + id)));
    }

    public void saveAluguel(AluguelRequest aluguel) {
        var aluguelEntity = calcularValorAluguel(aluguel);
        var save = this.aluguelRepository.save(aluguelEntity);
        Assert.state(save == 1, "Erro ao salvar o Aluguel" + aluguel.pessoaId());
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

    private Aluguel calcularValorAluguel(AluguelRequest AluguelRequest) {
        var veiculo = this.veiculoRepository.findById(AluguelRequest.veiculoId())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado para aluguel"));

        var quantidadesDias = BigDecimal.valueOf(AluguelRequest.dataFim().getDayOfYear() - AluguelRequest.dataInicio().getDayOfYear());
        var valor = veiculo.getValorDiaria().multiply(quantidadesDias);

        return new Aluguel(AluguelRequest, valor);
    }
}
