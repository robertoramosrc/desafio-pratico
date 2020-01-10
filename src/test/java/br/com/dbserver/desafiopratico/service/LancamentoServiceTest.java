package br.com.dbserver.desafiopratico.service;

import br.com.dbserver.desafiopratico.repository.LancamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LancamentoServiceTest {

    private LancamentoService lancamentoService;

    @Mock
    private LancamentoRepository lancamentoRepository;

    @BeforeEach
    public void inicia() {
        lancamentoService = new ContaCorrenteService(contaCorrenteRepository);
    }


}
