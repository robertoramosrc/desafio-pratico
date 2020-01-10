package br.com.dbserver.desafiopratico.dto;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

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

    public Long getAgenciaOrigem() {
        return agenciaOrigem;
    }

    public void setAgenciaOrigem(Long agenciaOrigem) {
        this.agenciaOrigem = agenciaOrigem;
    }

    public Long getAgenciaDestino() {
        return agenciaDestino;
    }

    public void setAgenciaDestino(Long agenciaDestino) {
        this.agenciaDestino = agenciaDestino;
    }

    public Long getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(Long contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public Long getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(Long contaDestino) {
        this.contaDestino = contaDestino;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

}

