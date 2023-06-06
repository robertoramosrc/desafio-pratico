package br.com.dbserver.desafiopratico.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="TB_CONTA")
@Data
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
}