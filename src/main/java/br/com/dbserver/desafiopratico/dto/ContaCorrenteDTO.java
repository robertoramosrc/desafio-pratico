package br.com.dbserver.desafiopratico.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ContaCorrenteDTO {
    @NotNull(message = "Informe o número da Conta Corrente")
    private int conta;

    @NotNull(message = "Informe o nome do correntista")
    private String correntista;

    @NotNull(message = "Informe o código da Agência")
    private int agencia;

    private BigDecimal saldo;

}
