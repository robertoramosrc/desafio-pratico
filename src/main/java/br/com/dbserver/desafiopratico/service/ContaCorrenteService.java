package br.com.dbserver.desafiopratico.service;

import br.com.dbserver.desafiopratico.model.ContaCorrente;
import br.com.dbserver.desafiopratico.model.Lancamento;
import br.com.dbserver.desafiopratico.model.TipoLancamento;
import br.com.dbserver.desafiopratico.repository.ContaCorrenteRepository;
import br.com.dbserver.desafiopratico.exceptions.NegocioException;
import br.com.dbserver.desafiopratico.exceptions.RegistroNaoExisteException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class ContaCorrenteService {

    private final ContaCorrenteRepository contaCorrenteRepository;

    public ContaCorrenteService(ContaCorrenteRepository contaCorrenteRepository) {
        this.contaCorrenteRepository = contaCorrenteRepository;
    }

    public List<ContaCorrente> listar(Optional<Long> agencia, Optional<Long> conta) {

        ContaCorrente contaCorrente = new ContaCorrente();

        if(agencia.isPresent()){
            contaCorrente.setAgencia(agencia.get());
        }

        if(conta.isPresent()){
            contaCorrente.setAgencia(conta.get());
        }

        return this.contaCorrenteRepository.findAll(Example.of(contaCorrente));

    }

    public ContaCorrente buscarPorId(Long Id) {

        return this.contaCorrenteRepository.findById(Id)
                .orElseThrow(() -> new RegistroNaoExisteException("Conta Corrente não encontrada."));

    }

    public ContaCorrente buscarPorCodigosDaAgenciaEContaCorrente(Long agencia, Long conta ) throws RegistroNaoExisteException{

        return this.contaCorrenteRepository.buscarPorCodigosDaAgenciaEConta(agencia, conta)
                .orElseThrow(() -> new RegistroNaoExisteException(
                        new StringBuffer()
                                .append("Agência: ")
                                .append(agencia)
                                .append(" Conta Corrente: ")
                                .append(conta)
                                .append(" não encontrada.").toString()));
    }

    public ContaCorrente salvar(ContaCorrente contaCorrente) throws NegocioException {

        if( contaCorrente.getSaldo() == null ){
            contaCorrente.setSaldo(BigDecimal.ZERO);
        }

        this.validarSaldoInicial(contaCorrente);
        return this.contaCorrenteRepository.save(contaCorrente);
    }

    public void atualizarSaldoContaCorrente(Lancamento lancamento) throws NegocioException{
        ContaCorrente contaCorrente = lancamento.getConta();

        if(lancamento.getTipoLancamento() == TipoLancamento.CREDITO) {
            contaCorrente.setSaldo(contaCorrente.getSaldo().add(lancamento.getValor()));

        }else if(lancamento.getTipoLancamento() == TipoLancamento.DEBITO) {
            this.validarSaldoDisponivelParaRealizarDebito(contaCorrente, lancamento);
            contaCorrente.setSaldo(contaCorrente.getSaldo().subtract(lancamento.getValor()));
        }
    }

    private void validarSaldoInicial(ContaCorrente contaCorrente) throws NegocioException  {
        if( contaCorrente.getSaldo().compareTo(BigDecimal.ZERO) < 0 ) {
            throw new NegocioException("O valor do Saldo Inicial não pode ser negativo");
        }
    }

    private void validarSaldoDisponivelParaRealizarDebito(ContaCorrente contaCorrente,
                                                          Lancamento lancamento) throws NegocioException  {

        if (contaCorrente.getSaldo().compareTo(lancamento.getValor()) < 0) {
            throw new NegocioException("Saldo insuficiente para realização do débito.");
        }

    }



}
