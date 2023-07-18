package com.cap.saudefinanceira;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cap.saudefinanceira.repositorios.RelatorioRepositorio;

@SpringBootApplication
public class SaudefinanceiraApplication{ // implements CommandLineRunner {
	
	@Autowired
	private RelatorioRepositorio relatorioRepositorio;

	public static void main(String[] args) {
		SpringApplication.run(SaudefinanceiraApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		LocalDate data = LocalDate.now();
//		List<DadosGraficosProjecao> list1 =	relatorioRepositorio.findByContaEntradaAndData(data, data);
//		
//		for(DadosGraficosProjecao item: list1) {
//			System.out.println(item.getValue());
//			System.out.println(item.getName());
//		}
//		
//	}

}
