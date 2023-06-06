package br.com.dbserver.desafiopratico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dbserver.desafiopratico.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

}
