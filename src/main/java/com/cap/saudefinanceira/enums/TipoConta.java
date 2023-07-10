package com.cap.saudefinanceira.enums;

public enum TipoConta {

	REMUNERACAO(1, "REMUNERACAO"), DESPESA(2, "DESPESA"), TRANSFERENCIA(3, "TRANSFERENCIA");

	private Integer codigo;
	private String descricao;
	
	private TipoConta(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoConta toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(TipoConta x :TipoConta.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Tipo conta inválida");
	}
	
	
}
