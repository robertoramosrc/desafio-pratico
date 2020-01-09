package br.com.dbserver.desafiopratico.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transferencia {
    ContaCorrente contaOrigem;
    ContaCorrente contaDestino;
    BigDecimal valor;
    LocalDate data;

    public ContaCorrente getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(ContaCorrente contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public ContaCorrente getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(ContaCorrente contaDestino) {
        this.contaDestino = contaDestino;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
