package com.cap.saudefinanceira.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cap.saudefinanceira.dominio.Conta;

public interface ContaRepositorio extends JpaRepository<Conta, Integer>{
	
	

}