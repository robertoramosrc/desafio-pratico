package br.com.dbserver.desafiopratico.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.dbserver.desafiopratico.dto.TransferenciaDTO;
import br.com.dbserver.desafiopratico.exceptions.RegistroNaoExisteException;
import br.com.dbserver.desafiopratico.model.Lancamento;
import br.com.dbserver.desafiopratico.model.TipoLancamento;
import br.com.dbserver.desafiopratico.model.Transferencia;

@Service
public class TransferenciaService {
    private final LancamentoService lancamentoService;
    private final ContaCorrenteService contaCorrenteService;

    public TransferenciaService(LancamentoService lancamentoService, ContaCorrenteService contaCorrenteService) {
        this.lancamentoService = lancamentoService;
        this.contaCorrenteService = contaCorrenteService;
    }

    public Transferencia realizarTransferencia(TransferenciaDTO transferenciaDTO) throws RegistroNaoExisteException {

        Transferencia transferencia = new Transferencia();

        transferencia.setContaOrigem(
                this.contaCorrenteService.buscarPorCodigosDaAgenciaEContaCorrente(
                        transferenciaDTO.getAgenciaOrigem(),
                        transferenciaDTO.getContaOrigem()));
        transferencia.setContaDestino(
                this.contaCorrenteService.buscarPorCodigosDaAgenciaEContaCorrente(
                        transferenciaDTO.getAgenciaDestino(),
                        transferenciaDTO.getContaDestino()));

        transferencia.setData(LocalDateTime.now());
        transferencia.setValor(transferenciaDTO.getValor());

        this.gerarLancamentoDebitoNaContaOrigem(transferencia);
        this.gerarLancamentoCreditoNaContaDestino(transferencia);

        return transferencia;
    }

    private void gerarLancamentoDebitoNaContaOrigem(Transferencia transferencia) {

        Lancamento lancamento = new Lancamento();

        lancamento.setTipoLancamento(TipoLancamento.DEBITO);
        lancamento.setConta(transferencia.getContaOrigem());
        lancamento.setValor(transferencia.getValor());
        lancamento.setData(LocalDateTime.now());
        lancamento.setDescricao(new StringBuffer()
                .append("Débito referente transferência para a Conta (")
                .append(transferencia.getContaDestino().getDescricao())
                .append(")")
                .toString());

        this.lancamentoService.gerarLancamentoEmContaCorrente(lancamento);

    }

    private void gerarLancamentoCreditoNaContaDestino(Transferencia transferencia) {
        Lancamento lancamento = new Lancamento();

        lancamento.setTipoLancamento(TipoLancamento.CREDITO);
        lancamento.setConta(transferencia.getContaDestino());
        lancamento.setValor(transferencia.getValor());
        lancamento.setData(transferencia.getData());
        lancamento.setDescricao(new StringBuffer()
                .append("Crédito referente transferência da Conta (")
                .append(transferencia.getContaOrigem().getDescricao())
                .append(")")
                .toString());

        this.lancamentoService.gerarLancamentoEmContaCorrente(lancamento);

    }

}
