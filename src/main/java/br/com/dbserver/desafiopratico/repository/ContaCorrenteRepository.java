package br.com.dbserver.desafiopratico.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.dbserver.desafiopratico.model.ContaCorrente;

public interface ContaCorrenteRepository extends JpaRepository <ContaCorrente, Long>  {

    @Query("select c from ContaCorrente c where c.agencia = :codigoAgencia and c.conta = :codigoConta ")
    Optional<ContaCorrente> buscarPorCodigosDaAgenciaEConta(@Param("codigoAgencia") Long agencia,
                                                            @Param("codigoConta") Long conta);
}
