package br.com.dbserver.desafiopratico.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransferenciaDTO {
    @NotNull(message = "Informe o número da Agência de Origem")
    private Long agenciaOrigem;

    @NotNull(message = "Informe o número da Conta de Origem")
    private Long contaOrigem;

    @NotNull(message = "Informe o número da Agência de Destino")
    private Long agenciaDestino;

    @NotNull(message = "Informe o número da Conta de Destino")
    private Long contaDestino;

    @NotNull(message = "Informe o valor a ser transferido")
    private BigDecimal valor;
}

