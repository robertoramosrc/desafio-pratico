package br.com.dbserver.desafiopratico.service;

import br.com.dbserver.desafiopratico.exceptions.NegocioException;
import br.com.dbserver.desafiopratico.model.ContaCorrente;
import br.com.dbserver.desafiopratico.model.Lancamento;
import br.com.dbserver.desafiopratico.model.TipoLancamento;
import br.com.dbserver.desafiopratico.repository.LancamentoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class LancamentoServiceTest {

    private LancamentoService lancamentoService;

    @Mock
    private ContaCorrenteService contaCorrenteService;

    @Mock
    private LancamentoRepository lancamentoRepository;

    @BeforeEach
    public void inicia() {
        lancamentoService = new LancamentoService(lancamentoRepository,
                contaCorrenteService);
    }

    @Test
    public void deveriaGerarLancamentoDeCreditoComSucesso() throws NegocioException{
        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setAgencia(1020L);
        contaCorrente.setSaldo(new BigDecimal(100.00));

        Lancamento lancamento = new Lancamento();
        lancamento.setTipoLancamento(TipoLancamento.CREDITO);
        lancamento.setConta(contaCorrente);
        lancamento.setData(LocalDateTime.now());
        lancamento.setValor(new BigDecimal(18.00));

        lancamentoService.gerarLancamentoEmContaCorrente(lancamento);

    }

    @Test
    public void deveriaFalharAoGerarLancamentoSemDataInformada(){
        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setAgencia(1020L);
        contaCorrente.setSaldo(new BigDecimal(100.00));

        Lancamento lancamento = new Lancamento();
        lancamento.setTipoLancamento(TipoLancamento.CREDITO);
        lancamento.setConta(contaCorrente);
        lancamento.setValor(new BigDecimal(18.00));

        NegocioException e = assertThrows(NegocioException.class,
                () -> lancamentoService.gerarLancamentoEmContaCorrente(lancamento));

        assertEquals("Data do lançamento não preenchida.",
                e.getMessage());

    }

}
