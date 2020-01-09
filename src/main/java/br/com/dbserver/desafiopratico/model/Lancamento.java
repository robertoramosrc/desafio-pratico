package br.com.dbserver.desafiopratico.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_LANCAMENTO")
public class Lancamento {

    @Id
    @Column(name = "ID_LANCAMENTO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long Id;

    @Column(name = "DS_TIPO_LANCAMENTO")
    private TipoLancamento tipoLancamento;

    @Column(name = "DT_LANCAMENTO")
    private LocalDateTime data;

    @Column(name = "VL_LANCAMENTO")
    private BigDecimal valor;

    @Column(name = "DS_LANCAMENTO")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "ID_CONTA_CORRENTE")
    private ContaCorrente conta;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public TipoLancamento getTipoLancamento() {
        return tipoLancamento;
    }

    public void setTipoLancamento(TipoLancamento tipoLancamento) {
        this.tipoLancamento = tipoLancamento;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public ContaCorrente getConta() {
        return conta;
    }

    public void setConta(ContaCorrente conta) {
        this.conta = conta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
