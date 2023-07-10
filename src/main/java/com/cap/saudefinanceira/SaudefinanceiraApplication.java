package com.cap.saudefinanceira;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cap.saudefinanceira.projecoes.DadosGraficosProjecao;
import com.cap.saudefinanceira.repositorios.RelatorioRepositorio;

@SpringBootApplication
public class SaudefinanceiraApplication implements CommandLineRunner {
	
	@Autowired
	private RelatorioRepositorio relatorioRepositorio;

	public static void main(String[] args) {
		SpringApplication.run(SaudefinanceiraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Carlosssss");
		List<DadosGraficosProjecao> list1 =	relatorioRepositorio.findByContaEntradaAndData(new Date(), new Date());
		
		for(DadosGraficosProjecao item: list1) {
			System.out.println(item.getValue());
			System.out.println(item.getName());
		}
		
	}

}
