package com.pierai.locatech.locatech.controllers;

import com.pierai.locatech.locatech.entities.Aluguel;
import com.pierai.locatech.locatech.entities.AluguelRequest;
import com.pierai.locatech.locatech.entities.Pessoa;
import com.pierai.locatech.locatech.services.AluguelService;
import com.pierai.locatech.locatech.services.PessoaService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alugueis")
public class AluguelController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VeiculoController.class);
    private final AluguelService aluguelService;

    public AluguelController(AluguelService aluguelService) {
        this.aluguelService = aluguelService;
    }

    // http://localhost:8080/alugueis?page=1&size=10
    @GetMapping
    public ResponseEntity<List<Aluguel>> findAllAlugueis(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ){
        LOGGER.info("Recebendo requisição para listar alugueis - Página: {}, Tamanho: {}", page, size);
        var pessoa = this.aluguelService.findAllAlugueis(page, size);

        return ResponseEntity.ok(pessoa);

    }

    // http://localhost:8080/alugueis/1
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Aluguel>> findAluguelById(@PathVariable("id") Long id) {

        LOGGER.info("Recebendo requisição para buscar veículo por ID: {}", id);
        var pessoa = this.aluguelService.findAluguelById(id);

        return ResponseEntity.ok(pessoa);
    }

    @PostMapping
    public ResponseEntity<Void> saveAluguel(
            @RequestBody @Valid AluguelRequest aluguel)
    {
        LOGGER.info("Recebendo requisição para salvar veículo: {}", aluguel);
        this.aluguelService.saveAluguel(aluguel);

        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAluguel(@PathVariable ("id") Long id,
                                              @RequestBody Aluguel aluguel)
    {
        LOGGER.info("Recebendo requisição para atualizar aluguel com ID: {}, Dados: {}", id, aluguel);
        this.aluguelService.updateAluguel(aluguel, id);

        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluguel(@PathVariable Long id) {

        LOGGER.info("Recebendo requisição para deletar aluguel com id: {}", id);
        this.aluguelService.deleteAluguel(id);

        return ResponseEntity.noContent().build();
    }
}
