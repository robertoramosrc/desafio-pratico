package br.com.dbserver.desafiopratico.repository;

import br.com.dbserver.desafiopratico.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

    @Query("select l from Lancamento l order by l.data desc")
    List<Lancamento> findAllOrderByData();

}
