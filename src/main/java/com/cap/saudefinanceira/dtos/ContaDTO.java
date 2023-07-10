package com.cap.saudefinanceira.dtos;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import com.cap.saudefinanceira.dominio.Conta;

public class ContaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	protected Integer id;
	
	@NotNull(message = "O campo DESCRICAO é requerido")
	protected String descricao;
	@NotNull(message = "O campo TIPO CONTA é requerido")
	protected Integer tipoConta;
	
		
	public ContaDTO(Conta conta) {
		this.id = conta.getId();
		System.out.println(conta.getId());
		this.descricao = conta.getDescricao();
		this.tipoConta = conta.getTipoConta();
		
		System.out.println(this.descricao);
		System.out.println(this.tipoConta);
	}
	
	public ContaDTO() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(Integer tipoConta) {
		this.tipoConta = tipoConta;
	}

	
	
	
	
	
}
