package com.cap.saudefinanceira.dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.cap.saudefinanceira.dtos.ContaDTO;

@Entity
public class Conta implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String descricao;
	private Integer tipoConta;
	
	public Conta() {
	}
	
	public Conta(Integer id) {
		this.id = id;
	}
	
	public Conta(String descricao, Integer tipoConta) {
		this.descricao = descricao;
		this.tipoConta = tipoConta;
	}
	
	public Conta(ContaDTO contaDTO) {
		this.id = contaDTO.getId();
		this.descricao = contaDTO.getDescricao();
		this.tipoConta = contaDTO.getTipoConta();
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