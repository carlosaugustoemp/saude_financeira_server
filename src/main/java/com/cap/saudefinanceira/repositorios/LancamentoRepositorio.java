package com.cap.saudefinanceira.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cap.saudefinanceira.dominio.Lancamento;

public interface LancamentoRepositorio extends JpaRepository<Lancamento, Integer>{
	
	public List<Lancamento> findByContaEntrada(Integer contaEntrada);
	public List<Lancamento> findByContaSaida(Integer contaSaida);
	
}