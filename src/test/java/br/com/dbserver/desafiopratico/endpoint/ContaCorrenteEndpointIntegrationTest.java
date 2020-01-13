package br.com.dbserver.desafiopratico.endpoint;

import br.com.dbserver.desafiopratico.config.ModelMappingConfig;
import br.com.dbserver.desafiopratico.model.ContaCorrente;
import br.com.dbserver.desafiopratico.service.ContaCorrenteService;
import br.com.dbserver.desafiopratico.service.LancamentoService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

@WebMvcTest(controllers = ContaCorrenteEndpoint.class)
@ExtendWith(SpringExtension.class)
@Import(ModelMappingConfig.class)
public class ContaCorrenteEndpointIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContaCorrenteService contaCorrenteService;

    @Test
    public void deveriaRetornarListaContasVazia() throws Exception {

        mockMvc
                .perform(MockMvcRequestBuilders.get("/contas"))
                .andExpect(MockMvcResultMatchers.status()
                        .is(200))
                .andExpect((MockMvcResultMatchers.content())
                        .string("[]"))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void deveriaRetornarUmaContaEspecifica() throws Exception {

        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setConta(101020L);
        contaCorrente.setAgencia(1052L);
        contaCorrente.setCorrentista("Fulano da Silva");
        contaCorrente.setSaldo(BigDecimal.ZERO);

        Mockito.doReturn(Arrays.asList(contaCorrente))
                .when(contaCorrenteService).listar(Optional.empty(),
                Optional.empty());

        mockMvc
                .perform(MockMvcRequestBuilders.get("/contas"))
                .andExpect(MockMvcResultMatchers.status()
                        .is(200))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$[0].conta",
                                CoreMatchers.is(101020)))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$[0].agencia",
                                CoreMatchers.is(1052)))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$[0].correntista",
                                CoreMatchers.is("Fulano da Silva")))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$[0].saldo",
                                CoreMatchers.is(0)))
                .andDo(MockMvcResultHandlers.print());
    }


}




