package br.com.dbserver.desafiopratico.endpoint;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ContaCorrenteControllerE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql(value="classpath:sql/insere_dados_base.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void deveriaRetornarUmaContaCadastradaDadoUmaAgencia() throws Exception {

        mockMvc
                .perform(MockMvcRequestBuilders.get("/contas?agencia=1052"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("[0].conta").value(CoreMatchers.is(101020)))
                .andExpect(jsonPath("[0].correntista").value(CoreMatchers.is("Fulano da Silva")))
                .andExpect(jsonPath("[0].agencia").value(CoreMatchers.is(1052)))
                .andExpect(jsonPath("[0].saldo").value(CoreMatchers.is(0.00)));
    }

}
