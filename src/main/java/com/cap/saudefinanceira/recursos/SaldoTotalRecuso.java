package com.cap.saudefinanceira.recursos;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cap.saudefinanceira.projecoes.DadosGraficosProjecao;
import com.cap.saudefinanceira.repositorios.RelatorioRepositorio;


@RestController
@RequestMapping(value="/saldototal")
public class SaldoTotalRecuso {

	@Autowired
	private RelatorioRepositorio relatorioRepositorio;
	
	@GetMapping
	public ResponseEntity<List<DadosGraficosProjecao>> findAll(){
		List<DadosGraficosProjecao> list1 =	relatorioRepositorio.findSaldoTotal(new Date(), new Date());
		return ResponseEntity.ok().body(list1);
	}
}
