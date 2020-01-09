package br.com.dbserver.desafiopratico.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ContaCorrenteDTO {
    @NotNull(message = "Informe o número da Conta Corrente")
    private int conta;

    @NotNull(message = "Informe o nome do correntista")
    private String correntista;

    @NotNull(message = "Informe o código da Agência")
    private int agencia;

    private BigDecimal saldo;

    public String getCorrentista() {
        return correntista;
    }

    public void setCorrentista(String correntista) {
        this.correntista = correntista;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public int getConta() {
        return conta;
    }

    public void setConta(int conta) {
        this.conta = conta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}
