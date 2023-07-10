package com.cap.saudefinanceira.dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.cap.saudefinanceira.dtos.ContaDTO;

@Entity
public class TipoConta implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String descricao;
	
	public TipoConta() {
	}
	
	public TipoConta(Integer id) {
		this.id = id;
	}
	
	public TipoConta(String descricao, Integer tipoConta) {
		this.descricao = descricao;
	}
	
	public TipoConta(ContaDTO contaDTO) {
		this.id = contaDTO.getId();
		this.descricao = contaDTO.getDescricao();
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
}