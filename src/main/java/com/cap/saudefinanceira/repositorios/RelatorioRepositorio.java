package com.cap.saudefinanceira.repositorios;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cap.saudefinanceira.dominio.Lancamento;
import com.cap.saudefinanceira.projecoes.DadosGraficosProjecao;
import com.cap.saudefinanceira.projecoes.TotalMovimentacaoMensalProjecao;

public interface RelatorioRepositorio extends JpaRepository<Lancamento, Integer>{
	
	@Query(nativeQuery = true, value ="select sum(valor) as value, "
			+ "conta.descricao as name "
			+ "from lancamento "
			+ "Inner Join Conta ON(conta.id = lancamento.conta_entrada) "
			+ "Where conta.tipo_conta = 2 "
			+ " and data between :data1 and :data2 "
			+ "group by  conta_entrada")
	List<DadosGraficosProjecao> findByContaEntradaAndData(@Param("data1") LocalDate data1, @Param("data2") LocalDate data2);

	@Query(nativeQuery = true, value ="select sum(valor) as value, "
			+ "tipo_conta.descricao as name "
			+ "from lancamento "
			+ "Inner Join tipo_conta ON(tipo_conta.id = lancamento.tipo_conta) "
			+ " where data between :data1 and :data2 "
			+ "group by Lancamento.tipo_conta")
	List<DadosGraficosProjecao> findSaldoTotal(@Param("data1") LocalDate data1, @Param("data2") LocalDate data2);
	 
	
	@Query(nativeQuery = true, value ="Select cAliasTT.* FROM ("
				+ "	SELECT 'transferências' AS conta,"
				+ "			   sum(lancamento.valor) - ifnull("
				+ "												(SELECT COALESCE(lc2.valor)"
				+ "												 FROM lancamento lc2"
				+ "												 WHERE (lancamento.tipo_conta = 3"
				+ "														AND lc2.tipo_conta = 3)"
				+ "												   AND lancamento.conta_saida = lc2.conta_entrada"
				+ "												   AND lancamento.conta_entrada = lc2.conta_saida"
				+ "												   AND YEAR(lc2.data) = YEAR(lancamento.data)"
				+ "												   AND MONTH(lc2.data) = MONTH(lancamento.data) ), 0) AS 'saldo',"
				+ "			   DATE_FORMAT(DATA, '%Y-%m') AS anomes"
				+ "		FROM lancamento"
				+ "		INNER JOIN conta ON(conta.id = lancamento.conta_entrada)"
				+ "		INNER JOIN tipo_conta ON(tipo_conta.id = lancamento.tipo_conta)"
				+ "		WHERE lancamento.tipo_conta = 3 AND conta.tipo_conta <> 1"
				+ "		GROUP BY YEAR(DATA),MONTH(DATA),lancamento.conta_entrada"
				+ "	UNION"
				+ "		SELECT 'despesas' AS conta,sum(lancamento.valor),DATE_FORMAT(DATA, '%Y-%m') AS anomes"
				+ "		FROM lancamento"
				+ "		INNER JOIN conta ON(conta.id = lancamento.conta_entrada)"
				+ "		INNER JOIN tipo_conta ON(tipo_conta.id = lancamento.tipo_conta)"
				+ "		WHERE lancamento.tipo_conta = 2 AND conta.tipo_conta <> 1"
				+ "		GROUP BY YEAR(DATA),MONTH(DATA)"
				+ "	UNION"
				+ "		SELECT 'carteira' AS carteira,"
				+ "			   sum(lancamento.valor) -" 
				+ "				   ifnull("
				+ "							(SELECT COALESCE(sum(lc2.valor))"
				+ "								FROM lancamento lc2"
				+ "								WHERE (lancamento.tipo_conta = 1"
				+ "									AND lc2.tipo_conta <> 1"
				+ "									AND lancamento.conta_entrada = lc2.conta_saida"
				+ "									AND YEAR(lc2.data) = YEAR(lancamento.data)"
				+ "									AND MONTH(lc2.data) = MONTH(lancamento.data))"
				+ "							GROUP BY YEAR(DATA), MONTH(DATA), lancamento.conta_saida), 0) +"
				+ "					 ifnull("
				+ "							(SELECT COALESCE(sum(lc3.valor))"
				+ "								FROM lancamento lc3"
											+ "	WHERE lc3.tipo_conta = 3"
												+ "	AND lc3.conta_entrada = lancamento.conta_entrada"
				+ "									AND YEAR(lc3.data) = YEAR(lancamento.data)"
				+ "									AND MONTH(lc3.data) = MONTH(lancamento.data)"
				+ "								GROUP BY YEAR(DATA), MONTH(DATA), lc3.conta_entrada), 0) AS 'saldo',"
				+ "			   DATE_FORMAT(DATA, '%Y-%m') AS anomes"
				+ "			FROM lancamento"
				+ "				INNER JOIN conta ON(conta.id = lancamento.conta_entrada)"
				+ "				INNER JOIN tipo_conta ON(tipo_conta.id = lancamento.tipo_conta)"
				+ "					WHERE lancamento.tipo_conta = 1 AND conta.tipo_conta = 1"
				+ "				GROUP BY YEAR(DATA),MONTH(DATA),lancamento.conta_saida"
				+ "	UNION"
				+ " 	SELECT 'receitas' AS carteira, sum(lancamento.valor) AS 'saldo', DATE_FORMAT(DATA, '%Y-%m') AS anomes"
				+ " 		FROM lancamento"
				+ "				WHERE lancamento.tipo_conta = 1 AND lancamento.conta_saida IS NULL"
				+ "		GROUP BY YEAR(DATA), MONTH(DATA)"
				+ "		) cAliasTT"
				+ "	order by anomes")
List<TotalMovimentacaoMensalProjecao> findMovimentacaoMensal(@Param("data1") LocalDate data1, @Param("data2") LocalDate data2);

	
	
	
}