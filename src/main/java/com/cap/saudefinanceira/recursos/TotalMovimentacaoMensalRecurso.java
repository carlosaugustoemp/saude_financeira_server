package com.cap.saudefinanceira.recursos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cap.saudefinanceira.dtos.DadosGraficosDTO;
import com.cap.saudefinanceira.dtos.MovimentacaoMensalDTO;
import com.cap.saudefinanceira.projecoes.TotalMovimentacaoMensalProjecao;
import com.cap.saudefinanceira.repositorios.RelatorioRepositorio;

@RestController
@RequestMapping(value = "/totalmovimentacaomensal")
public class TotalMovimentacaoMensalRecurso {

	@Autowired
	private RelatorioRepositorio relatorioRepositorio;

	@GetMapping
	public ResponseEntity<List<MovimentacaoMensalDTO>> findAll(@RequestParam(required = false) String dtinicio,
			@RequestParam(required = false) String dtfinal) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dtInicioFiltro = LocalDate.parse(dtinicio, formatter);
		LocalDate dtFinalFiltro = LocalDate.parse(dtfinal, formatter);

		System.out.println(dtInicioFiltro);
		System.out.println(dtFinalFiltro);

		List<TotalMovimentacaoMensalProjecao> list1 = relatorioRepositorio.findMovimentacaoMensal(dtInicioFiltro,
				dtFinalFiltro);
		List<MovimentacaoMensalDTO> retorno = parseProjecao(list1);
		return ResponseEntity.ok().body(retorno);
	}

	private List<MovimentacaoMensalDTO> parseProjecao(List<TotalMovimentacaoMensalProjecao> list) {
		List<MovimentacaoMensalDTO> result = new ArrayList<MovimentacaoMensalDTO>();
		
		for(TotalMovimentacaoMensalProjecao item: list) {
	        Optional<MovimentacaoMensalDTO> itemFiltrado = result.stream()
	                .filter(i -> i.getName().equals(item.getConta()))
	                .findAny();	
	        if (itemFiltrado.isPresent()) {
				DadosGraficosDTO dadosGraficosDTO = new DadosGraficosDTO();
				dadosGraficosDTO.setName(item.getAnomes());
				dadosGraficosDTO.setValue(item.getSaldo());
	        	itemFiltrado.get().getSeries().add(dadosGraficosDTO);
	        	
	        }else {
				MovimentacaoMensalDTO dto = new MovimentacaoMensalDTO();
				dto.setName(item.getConta());
				DadosGraficosDTO dadosGraficosDTO = new DadosGraficosDTO();
				dadosGraficosDTO.setName(item.getAnomes());
				dadosGraficosDTO.setValue(item.getSaldo());
				dto.getSeries().add(dadosGraficosDTO);
				result.add(dto);      	
	        }
		}
		return result;
	}
}