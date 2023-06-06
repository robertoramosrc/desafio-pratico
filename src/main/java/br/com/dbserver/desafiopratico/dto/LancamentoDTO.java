package br.com.dbserver.desafiopratico.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.dbserver.desafiopratico.model.TipoLancamento;
import lombok.Data;

@Data
public class LancamentoDTO {
    private Long Id;
    private Long conta;
    private TipoLancamento tipoLancamento;
    private LocalDateTime data;
    private BigDecimal valor;
    private Long agencia;
    private String correntista;
    private String descricao;

}
