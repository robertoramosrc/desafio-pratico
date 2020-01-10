package br.com.dbserver.desafiopratico.service;

import br.com.dbserver.desafiopratico.exceptions.NegocioException;
import br.com.dbserver.desafiopratico.model.ContaCorrente;
import br.com.dbserver.desafiopratico.model.Lancamento;
import br.com.dbserver.desafiopratico.model.TipoLancamento;
import br.com.dbserver.desafiopratico.repository.ContaCorrenteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ContaCorrenteServiceTest {

    private ContaCorrenteService contaCorrenteService;

    @Mock
    private ContaCorrenteRepository contaCorrenteRepository;

    @BeforeEach
    public void inicia() {
        contaCorrenteService = new ContaCorrenteService(contaCorrenteRepository);
    }

    @Test
    public void deveriaSalvarComSucessoIndicandoApenasAgencia() throws NegocioException {
        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setAgencia(1020L);

        this.contaCorrenteService.salvar(contaCorrente);

    }

    @Test
    public void deveriaSalvarComSucessoIndicandoAgenciaeNumerodaConta() throws NegocioException {
        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setAgencia(1020L);
        contaCorrente.setConta(1L);

        this.contaCorrenteService.salvar(contaCorrente);
    }

    @Test
    public void deveriaFalharAoSalvarComSaldoInicialNegativo() {
        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setAgencia(1020L);
        contaCorrente.setSaldo(new BigDecimal(-1.00));

        NegocioException e = assertThrows(NegocioException.class,
                () -> contaCorrenteService.salvar(contaCorrente));

        assertEquals("O valor do Saldo Inicial não pode ser negativo",
                e.getMessage());
    }


    @Test
    public void deveriaFalharAoTentarDebitarUmValorMaiorQueSaldo() {

        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setAgencia(1020L);
        contaCorrente.setSaldo(new BigDecimal(10.00));

        Lancamento lancamento = new Lancamento();
        lancamento.setTipoLancamento(TipoLancamento.DEBITO);
        lancamento.setConta(contaCorrente);
        lancamento.setValor(new BigDecimal(30.00));

        NegocioException e = assertThrows(NegocioException.class,
                () -> contaCorrenteService.atualizarSaldoContaCorrente(lancamento));

        assertEquals("Saldo insuficiente para realização do débito.",
                e.getMessage());

    }

    @Test
    public void deveriaDiminuirSaldoAoRealizarUmLancamentoDeDebito() {
        final BigDecimal VALOR_ESPERADO_APOS_DEBITO = new BigDecimal(70.00);

        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setAgencia(1020L);
        contaCorrente.setSaldo(new BigDecimal(100.00));

        Lancamento lancamento = new Lancamento();
        lancamento.setTipoLancamento(TipoLancamento.DEBITO);
        lancamento.setConta(contaCorrente);
        lancamento.setValor(new BigDecimal(30.00));

        contaCorrenteService.atualizarSaldoContaCorrente(lancamento);

        assertEquals(VALOR_ESPERADO_APOS_DEBITO, contaCorrente.getSaldo());
    }

    @Test
    public void deveriaAumentarSaldoAoRealizarUmLancamentoDeCredito() {
        final BigDecimal VALOR_ESPERADO_APOS_CREDITO = new BigDecimal(130.00);

        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setAgencia(1020L);
        contaCorrente.setSaldo(new BigDecimal(100.00));

        Lancamento lancamento = new Lancamento();
        lancamento.setTipoLancamento(TipoLancamento.CREDITO);
        lancamento.setConta(contaCorrente);
        lancamento.setValor(new BigDecimal(30.00));

        contaCorrenteService.atualizarSaldoContaCorrente(lancamento);

        assertEquals(VALOR_ESPERADO_APOS_CREDITO, contaCorrente.getSaldo());
    }

}
