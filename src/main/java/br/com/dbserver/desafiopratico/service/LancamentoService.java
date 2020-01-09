package br.com.dbserver.desafiopratico.service;

import br.com.dbserver.desafiopratico.model.Lancamento;
import br.com.dbserver.desafiopratico.repository.LancamentoRepository;
import exceptions.NegocioException;
import exceptions.RegistroNaoExisteException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LancamentoService {

    private final LancamentoRepository lancamentoRepository;
    private final ContaCorrenteService contaCorrenteService;
    private final ModelMapper mapper;


    public LancamentoService(LancamentoRepository lancamentoRepository, ContaCorrenteService contaCorrenteService, ModelMapper mapper) {
        this.lancamentoRepository = lancamentoRepository;
        this.contaCorrenteService = contaCorrenteService;
        this.mapper = mapper;
    }

    public List<Lancamento> listar(){
        return this.lancamentoRepository.findAllOrderByData();
    }

    public Lancamento buscarPorId(Long Id){
        return this.lancamentoRepository.findById(Id)
                .orElseThrow(() -> new RegistroNaoExisteException("Lançamento não encontrado."));
    }

    public Lancamento gerarLancamentoEmContaCorrente(Lancamento lancamento){
        this.validarDataLancamento(lancamento);

        this.contaCorrenteService.atualizarSaldoContaCorrente(lancamento);
        return this.lancamentoRepository.save(lancamento);
    }

    private void validarDataLancamento(Lancamento lancamento){
        if(lancamento.getData() == null){
            throw new NegocioException("Data do lançamento não preenchida.");
        }

        if (lancamento.getData().isBefore(LocalDate.now())) {
            throw new NegocioException("A data do lançamento não deve ser anterior a data atual.");
        }

    }

}
