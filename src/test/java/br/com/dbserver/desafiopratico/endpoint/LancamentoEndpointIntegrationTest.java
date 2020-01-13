package br.com.dbserver.desafiopratico.endpoint;

import br.com.dbserver.desafiopratico.config.ModelMappingConfig;
import br.com.dbserver.desafiopratico.model.ContaCorrente;
import br.com.dbserver.desafiopratico.model.Lancamento;
import br.com.dbserver.desafiopratico.model.TipoLancamento;
import br.com.dbserver.desafiopratico.service.LancamentoService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;


@WebMvcTest(controllers = LancamentoEndpoint.class)
@ExtendWith(SpringExtension.class)
@Import(ModelMappingConfig.class)

public class LancamentoEndpointIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LancamentoService lancamentoService;

    @Test
    public void deveriaRetornarListaLancamentosVazia() throws Exception {

        mockMvc
                .perform(MockMvcRequestBuilders.get("/lancamentos"))
                .andExpect(MockMvcResultMatchers.status()
                        .is(200))
                .andExpect((MockMvcResultMatchers.content())
                        .string("[]"))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void deveriaRetornarUmLancamentoEspecifico() throws Exception {
        final LocalDateTime DATA_ATUAL = LocalDateTime.now();
        final BigDecimal VALOR_LANCAMENTO = new BigDecimal(20.00);

        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setConta(101020L);
        contaCorrente.setAgencia(1052L);
        contaCorrente.setCorrentista("Fulano da Silva");
        contaCorrente.setSaldo(VALOR_LANCAMENTO);

        Lancamento lancamento = new Lancamento();
        lancamento.setConta(contaCorrente);
        lancamento.setData(DATA_ATUAL);
        lancamento.setValor(VALOR_LANCAMENTO);
        lancamento.setTipoLancamento(TipoLancamento.CREDITO);


        Mockito.doReturn(Arrays.asList(lancamento))
                .when(lancamentoService).listar(Optional.empty());

        mockMvc
                .perform(MockMvcRequestBuilders.get("/lancamentos"))
                .andExpect(MockMvcResultMatchers.status()
                        .is(200))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$[0].conta",
                                CoreMatchers.is(101020)))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$[0].tipoLancamento",
                                CoreMatchers.is("CREDITO")))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$[0].valor",
                                CoreMatchers.is(20)))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$[0].agencia",
                                CoreMatchers.is(1052)))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$[0].correntista",
                                CoreMatchers.is("Fulano da Silva")))
                .andDo(MockMvcResultHandlers.print());
    }



}
