package br.com.dbserver.desafiopratico.dto;

import br.com.dbserver.desafiopratico.model.TipoLancamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LancamentoDTO {
    private Long Id;
    private TipoLancamento tipoLancamento;
    private LocalDate data;
    private BigDecimal valor;
    private Long conta;
    private Long agencia;
    private String correntista;
    private String descricao;

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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Long getConta() {
        return conta;
    }

    public void setConta(Long conta) {
        this.conta = conta;
    }

    public Long getAgencia() {
        return agencia;
    }

    public void setAgencia(Long agencia) {
        this.agencia = agencia;
    }

    public String getCorrentista() {
        return correntista;
    }

    public void setCorrentista(String correntista) {
        this.correntista = correntista;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
