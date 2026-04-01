package com.pierai.locatech.locatech.controllers;

import com.pierai.locatech.locatech.entities.Pessoa;
import com.pierai.locatech.locatech.entities.Veiculo;
import com.pierai.locatech.locatech.services.PessoaService;
import com.pierai.locatech.locatech.services.VeiculoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VeiculoController.class);
    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    // http://localhost:8080/veiculos?page=1&size=10
    @GetMapping
    public ResponseEntity<List<Pessoa>> findAllPessoas(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ){
        LOGGER.info("Recebendo requisição para listar veículos - Página: {}, Tamanho: {}", page, size);
        var pessoa = this.pessoaService.findAllPessoas(page, size);

        return ResponseEntity.ok(pessoa);

    }

    // http://localhost:8080/veiculos/1
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pessoa>> findVeiculo(@PathVariable("id") Long id) {

        LOGGER.info("Recebendo requisição para buscar veículo por ID: {}", id);
        var pessoa = this.pessoaService.findVeiculoById(id);

        return ResponseEntity.ok(pessoa);
    }

    @PostMapping
    public ResponseEntity<Void> save(
            @RequestBody Pessoa pessoa)
    {
        LOGGER.info("Recebendo requisição para salvar veículo: {}", pessoa);
        this.pessoaService.savePessoa(pessoa);

        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateVeiculo(@PathVariable ("id") Long id,
                                              @RequestBody Pessoa pessoa)
    {
        LOGGER.info("Recebendo requisição para atualizar veículo com ID: {}, Dados: {}", id, pessoa);
        this.pessoaService.updatePessoa(pessoa, id);

        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeiculo(@PathVariable Long id) {

        LOGGER.info("Recebendo requisição para deletar veículo com id: {}", id);
        this.pessoaService.deletePessoa(id);

        return ResponseEntity.noContent().build();
    }
}
