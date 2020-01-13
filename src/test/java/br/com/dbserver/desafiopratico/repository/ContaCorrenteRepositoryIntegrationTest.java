package br.com.dbserver.desafiopratico.repository;

import br.com.dbserver.desafiopratico.model.ContaCorrente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@Sql(value="classpath:sql/limpa_base.sql",
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(value="classpath:sql/insere_dados_base.sql",
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ContaCorrenteRepositoryIntegrationTest {

    @Autowired
    private ContaCorrenteRepository contaCorrenteRepository;

    @Test
    public void deveriaBuscarPorCodigosDaAgenciaEConta(){
        Optional<ContaCorrente> contaCorrente =
                this.contaCorrenteRepository.buscarPorCodigosDaAgenciaEConta(1052L,101020L);

        Assertions.assertEquals(101020L, contaCorrente.get().getConta());

    }


}
