package br.com.dbserver.desafiopratico.endpoint;

import br.com.dbserver.desafiopratico.dto.TransferenciaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(value = "classpath:sql/limpa_base.sql",
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:sql/insere_dados_base.sql",
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class TransferenciaControllerE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveriaTransferirValorEntreContasComSucesso() throws Exception {
        TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
        transferenciaDTO.setAgenciaOrigem(1080L);
        transferenciaDTO.setContaOrigem(5L);
        transferenciaDTO.setAgenciaDestino(1090L);
        transferenciaDTO.setContaDestino(6L);
        transferenciaDTO.setValor(new BigDecimal(30.00));

        String jsonBodyTransferencia = new ObjectMapper().writeValueAsString(transferenciaDTO);

        mockMvc
                .perform(MockMvcRequestBuilders.post("/transferencias")
                        .content(jsonBodyTransferencia)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status()
                .is(200))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void deveriaFalharTransferenciaPorFaltaDeSaldo() throws Exception {
        TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
        transferenciaDTO.setAgenciaOrigem(1080L);
        transferenciaDTO.setContaOrigem(5L);
        transferenciaDTO.setAgenciaDestino(1090L);
        transferenciaDTO.setContaDestino(6L);
        transferenciaDTO.setValor(new BigDecimal(5000.00));

        String jsonBodyTransferencia = new ObjectMapper().writeValueAsString(transferenciaDTO);

        mockMvc
                .perform(MockMvcRequestBuilders.post("/transferencias")
                        .content(jsonBodyTransferencia)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status()
                        .is(400))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("descricao",
                                CoreMatchers.is("Saldo insuficiente na conta")))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void deveriaFalharTransferenciaPorContaOrigemInvalida() throws Exception {
        TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
        transferenciaDTO.setAgenciaOrigem(1080L);
        transferenciaDTO.setContaOrigem(7L);
        transferenciaDTO.setAgenciaDestino(1090L);
        transferenciaDTO.setContaDestino(6L);
        transferenciaDTO.setValor(new BigDecimal(5000.00));

        String jsonBodyTransferencia = new ObjectMapper().writeValueAsString(transferenciaDTO);

        mockMvc
                .perform(MockMvcRequestBuilders.post("/transferencias")
                        .content(jsonBodyTransferencia)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status()
                        .is(404))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("codigo",
                                CoreMatchers.is("registro_nao_existe")))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void deveriaFalharTransferenciaPorContaDestinoInvalida() throws Exception {
        TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
        transferenciaDTO.setAgenciaOrigem(1080L);
        transferenciaDTO.setContaOrigem(5L);
        transferenciaDTO.setAgenciaDestino(1090L);
        transferenciaDTO.setContaDestino(8L);
        transferenciaDTO.setValor(new BigDecimal(5000.00));

        String jsonBodyTransferencia = new ObjectMapper().writeValueAsString(transferenciaDTO);

        mockMvc
                .perform(MockMvcRequestBuilders.post("/transferencias")
                        .content(jsonBodyTransferencia)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status()
                        .is(404))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("codigo",
                                CoreMatchers.is("registro_nao_existe")))
                .andDo(MockMvcResultHandlers.print());

    }

}
