package com.pierai.locatech.locatech.controllers;

import com.pierai.locatech.locatech.entities.Veiculo;
import com.pierai.locatech.locatech.services.VeiculoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(VeiculoController.class);
    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    // http://localhost:8080/veiculos?page=1&size=10
    @GetMapping
        public ResponseEntity<List<Veiculo>> findAllVeiculos(
                @RequestParam("page") int page,
                @RequestParam("size") int size
    ){
        LOGGER.info("Recebendo requisição para listar veículos - Página: {}, Tamanho: {}", page, size);
        var veiculos = this.veiculoService.findAllVeiculos(page, size);

        return ResponseEntity.ok(veiculos);

    }

    // http://localhost:8080/veiculos/1
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Veiculo>> findVeiculo(@PathVariable("id") Long id) {

        LOGGER.info("Recebendo requisição para buscar veículo por ID: {}", id);
        var veiculo = this.veiculoService.findVeiculoById(id);

        return ResponseEntity.ok(veiculo);
    }

    @PostMapping
    public ResponseEntity<Void> saveVeiculo(
            @RequestBody Veiculo veiculo)
    {
        LOGGER.info("Recebendo requisição para salvar veículo: {}", veiculo);
        this.veiculoService.saveVeiculo(veiculo);

        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateVeiculo(@PathVariable ("id") Long id,
                                              @RequestBody Veiculo veiculo)
    {
        LOGGER.info("Recebendo requisição para atualizar veículo com ID: {}, Dados: {}", id, veiculo);
        this.veiculoService.updateVeiculo(veiculo, id);

        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeiculo(@PathVariable Long id) {

        LOGGER.info("Recebendo requisição para deletar veículo com id: {}", id);
        this.veiculoService.deleteVeiculo(id);

        return ResponseEntity.noContent().build();
    }
}
