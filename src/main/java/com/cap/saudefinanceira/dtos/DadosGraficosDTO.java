package com.cap.saudefinanceira.dtos;

import java.math.BigDecimal;

public class DadosGraficosDTO {
	
	private String name;
	private BigDecimal value;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}

	
	

}
