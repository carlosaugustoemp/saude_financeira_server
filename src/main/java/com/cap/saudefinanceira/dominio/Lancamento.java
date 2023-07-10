package com.cap.saudefinanceira.dominio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Lancamento implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer tipoConta;
	private BigDecimal valor;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date data;
	
	private Integer contaEntrada;
	private Integer contaSaida;
	private String observacao;

	public Lancamento(Integer tipoConta, BigDecimal valor, Date data, Conta contaEntrada, Conta contaSaida,
			String observacao) {
		this.tipoConta = tipoConta;
		this.valor = valor;
		this.data = data;
		this.contaEntrada = contaEntrada.getId();
		if(contaSaida != null && contaSaida.getId() != null) {
			this.contaSaida = contaSaida.getId();
		}
		this.observacao = observacao;
	}
	

	
	public Lancamento() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(Integer tipoConta) {
		this.tipoConta = tipoConta;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	public Integer getContaEntrada() {
		return contaEntrada;
	}
	public void setContaEntrada(Integer contaEntrada) {
		this.contaEntrada = contaEntrada;
	}
	public Integer getContaSaida() {
		return contaSaida;
	}
	public void setContaSaida(Integer contaSaida) {
		this.contaSaida = contaSaida;
	}
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}