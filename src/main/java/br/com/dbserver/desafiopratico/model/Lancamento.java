package br.com.dbserver.desafiopratico.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "TB_LANCAMENTO")
@Data
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

}
