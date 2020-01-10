package br.com.dbserver.desafiopratico.service;

import br.com.dbserver.desafiopratico.model.Lancamento;
import br.com.dbserver.desafiopratico.repository.LancamentoRepository;
import br.com.dbserver.desafiopratico.exceptions.NegocioException;
import br.com.dbserver.desafiopratico.exceptions.RegistroNaoExisteException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LancamentoService {

    private final LancamentoRepository lancamentoRepository;
    private final ContaCorrenteService contaCorrenteService;


    public LancamentoService(LancamentoRepository lancamentoRepository,
                             ContaCorrenteService contaCorrenteService) {
        this.lancamentoRepository = lancamentoRepository;
        this.contaCorrenteService = contaCorrenteService;

    }

    public List<Lancamento> listar(Optional<Long> conta){
        Lancamento lancamento = new Lancamento();

        if(conta.isPresent()){
            lancamento.setConta(this.contaCorrenteService.buscarPorId(conta.get()));
        }

        return this.lancamentoRepository.findAll(Example.of(lancamento));
    }

    public Lancamento buscarPorId(Long Id){
        return this.lancamentoRepository.findById(Id)
                .orElseThrow(() -> new RegistroNaoExisteException("Lançamento não encontrado."));
    }

    public Lancamento gerarLancamentoEmContaCorrente(Lancamento lancamento) throws NegocioException{
        this.validarDataLancamento(lancamento);

        this.contaCorrenteService.atualizarSaldoContaCorrente(lancamento);
        return this.lancamentoRepository.save(lancamento);
    }

    private void validarDataLancamento(Lancamento lancamento) throws NegocioException{
        if(lancamento.getData() == null){
            throw new NegocioException("Data do lançamento não preenchida.");
        }

    }

}
