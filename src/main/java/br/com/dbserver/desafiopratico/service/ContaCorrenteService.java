package br.com.dbserver.desafiopratico.service;

import br.com.dbserver.desafiopratico.model.ContaCorrente;
import br.com.dbserver.desafiopratico.model.Lancamento;
import br.com.dbserver.desafiopratico.model.TipoLancamento;
import br.com.dbserver.desafiopratico.repository.ContaCorrenteRepository;
import exceptions.NegocioException;
import exceptions.RegistroNaoExisteException;
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

    public ContaCorrente buscarPorCodigoContaCorrente(Long codigo){

        return this.contaCorrenteRepository.buscarPorCodigoDaConta(codigo)
                .orElseThrow(() -> new RegistroNaoExisteException(
                        new StringBuffer()
                                .append("Conta Corrente: ")
                                .append(codigo)
                                .append(" não encontrada.").toString()));
    }

    public ContaCorrente salvar(ContaCorrente contaCorrente) {

        if( contaCorrente.getSaldo() == null ){
            contaCorrente.setSaldo(BigDecimal.ZERO);
        }

        this.validarSaldoInicial(contaCorrente);
        return this.contaCorrenteRepository.save(contaCorrente);
    }

    public void atualizarSaldoContaCorrente(Lancamento lancamento){
        ContaCorrente contaCorrente = lancamento.getConta();

        if(lancamento.getTipoLancamento() == TipoLancamento.CREDITO) {
            contaCorrente.setSaldo(contaCorrente.getSaldo().add(lancamento.getValor()));
        }else if(lancamento.getTipoLancamento() == TipoLancamento.DEBITO) {
            contaCorrente.setSaldo(contaCorrente.getSaldo().subtract(lancamento.getValor()));
        }
    }

    private void validarSaldoInicial(ContaCorrente contaCorrente) {
        if( contaCorrente.getSaldo().compareTo(BigDecimal.ZERO) < 0 ) {
            throw new NegocioException("O valor do Saldo Inicial não pode ser negativo");
        }
    }

}
