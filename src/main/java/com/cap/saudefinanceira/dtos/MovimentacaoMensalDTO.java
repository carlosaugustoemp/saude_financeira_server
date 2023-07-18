package com.cap.saudefinanceira.dtos;

import java.util.ArrayList;
import java.util.List;

public class MovimentacaoMensalDTO {

	private String name;
	private List<DadosGraficosDTO> series;
	
	public MovimentacaoMensalDTO() {
		series = new ArrayList<DadosGraficosDTO>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<DadosGraficosDTO> getSeries() {
		return series;
	}
	public void setSeries(List<DadosGraficosDTO> series) {
		this.series = series;
	}
	
	
}
