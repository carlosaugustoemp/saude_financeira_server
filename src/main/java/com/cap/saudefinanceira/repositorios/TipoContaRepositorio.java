package com.cap.saudefinanceira.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cap.saudefinanceira.dominio.Conta;
import com.cap.saudefinanceira.dominio.TipoConta;

public interface TipoContaRepositorio extends JpaRepository<TipoConta, Integer>{
	
	

}