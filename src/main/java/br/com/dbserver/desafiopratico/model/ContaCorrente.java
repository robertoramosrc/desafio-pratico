package br.com.dbserver.desafiopratico.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name="TB_CONTA")

public class ContaCorrente {

    @Id
    @Column(name = "CD_CONTA")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long conta;

    @Column(name = "NM_CORRENTISTA")
    private String correntista;

    @Column(name = "CD_AGENCIA")
    private Long agencia;

    @Column(name = "VL_SALDO")
    private BigDecimal saldo;

    public String getDescricao(){
        return new StringBuffer().append("Número: ")
                .append(this.getConta())
                .append(" - Agência: ")
                .append(this.getAgencia())
                .append(" - Correntista: ")
                .append(this.getCorrentista())
                .toString();
    }

    public String getCorrentista() {
        return correntista;
    }

    public void setCorrentista(String correntista) {
        this.correntista = correntista;
    }

    public Long getAgencia() {
        return agencia;
    }

    public void setAgencia(Long agencia) {
        this.agencia = agencia;
    }

    public Long getConta() {
        return conta;
    }

    public void setConta(Long conta) {
        this.conta = conta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

}