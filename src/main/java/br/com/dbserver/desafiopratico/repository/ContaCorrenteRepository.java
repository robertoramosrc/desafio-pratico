package br.com.dbserver.desafiopratico.repository;

import br.com.dbserver.desafiopratico.model.ContaCorrente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ContaCorrenteRepository extends JpaRepository <ContaCorrente, Long>  {

    @Query("select c from ContaCorrente c where c.conta = :codigoConta ")
    Optional<ContaCorrente> buscarPorCodigoDaConta(@Param("codigoConta") Long conta);

}
