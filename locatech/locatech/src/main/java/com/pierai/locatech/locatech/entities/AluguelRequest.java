package com.pierai.locatech.locatech.entities;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AluguelRequest(
                             @NotNull(message = "O campo id é obrigatório")
                             Long id,
                             @NotNull(message = "O campo pessoaId é obrigatório")
                             Long pessoaId,
                             @NotNull(message = "O campo veiculoId é obrigatório")
                             Long veiculoId,
                             LocalDate dataInicio,
                             LocalDate dataFim) {
}
