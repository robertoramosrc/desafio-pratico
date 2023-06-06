package br.com.dbserver.desafiopratico.repository;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.dbserver.desafiopratico.model.ContaCorrente;

@ExtendWith(SpringExtension.class)
@Sql(value = "classpath:sql/limpa_base.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(value = "classpath:sql/insere_dados_base.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)

public class ContaCorrenteRepositoryIntegrationTest {

        private static final long NUMERO_CONTA_CORRENTE = 101020L;

        @InjectMocks
        private ContaCorrenteRepository contaCorrenteRepository;

        @Test
        public void deveriaBuscarPorCodigosDaAgenciaEConta() {
                Optional<ContaCorrente> contaCorrente = this.contaCorrenteRepository
                                .buscarPorCodigosDaAgenciaEConta(1052L, 101020L);

                Assertions.assertEquals(NUMERO_CONTA_CORRENTE, contaCorrente.get().getConta());
        }
}
