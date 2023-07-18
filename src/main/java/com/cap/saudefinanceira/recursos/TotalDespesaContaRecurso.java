package com.cap.saudefinanceira.recursos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cap.saudefinanceira.projecoes.DadosGraficosProjecao;
import com.cap.saudefinanceira.repositorios.RelatorioRepositorio;


@RestController
@RequestMapping(value="/totaldespesaconta")
public class TotalDespesaContaRecurso {

	@Autowired
	private RelatorioRepositorio relatorioRepositorio;
	
	@GetMapping
	public ResponseEntity<List<DadosGraficosProjecao>> findAll(
	        @RequestParam(required = false) String dtinicio,
	        @RequestParam(required = false) String dtfinal) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dtInicioFiltro = LocalDate.parse(dtinicio, formatter);
		LocalDate dtFinalFiltro = LocalDate.parse(dtfinal, formatter);
		
		System.out.println(dtInicioFiltro);
		System.out.println(dtFinalFiltro);
		
		List<DadosGraficosProjecao> list1 =	relatorioRepositorio.findByContaEntradaAndData(dtInicioFiltro, dtFinalFiltro);
		return ResponseEntity.ok().body(list1);
	}
	
	
	
}
