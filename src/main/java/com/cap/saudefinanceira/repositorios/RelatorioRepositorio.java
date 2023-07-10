package com.cap.saudefinanceira.repositorios;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cap.saudefinanceira.dominio.Lancamento;
import com.cap.saudefinanceira.projecoes.DadosGraficosProjecao;

public interface RelatorioRepositorio extends JpaRepository<Lancamento, Integer>{
	
	@Query(nativeQuery = true, value ="select sum(valor) as value, "
			+ "conta.descricao as name "
			+ "from lancamento "
			+ "Inner Join Conta ON(conta.id = lancamento.conta_entrada) "
			+ "Where conta.tipo_conta = 2 "
			+ "group by  conta_entrada")
	List<DadosGraficosProjecao> findByContaEntradaAndData(Date dataInicial, Date dataFinal);

	@Query(nativeQuery = true, value ="select sum(valor) as value, "
			+ "tipo_conta.descricao as name "
			+ "from lancamento "
			+ "Inner Join tipo_conta ON(tipo_conta.id = lancamento.tipo_conta) "
			+ "group by Lancamento.tipo_conta")
	List<DadosGraficosProjecao> findSaldoTotal(Date dataInicial, Date dataFinal);
	 
	
}