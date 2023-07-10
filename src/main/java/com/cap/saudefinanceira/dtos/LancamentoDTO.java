package com.cap.saudefinanceira.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.cap.saudefinanceira.dominio.Conta;
import com.cap.saudefinanceira.dominio.Lancamento;
import com.cap.saudefinanceira.servicos.ContaServico;
import com.fasterxml.jackson.annotation.JsonFormat;

public class LancamentoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotNull(message = "O campo TIPO CONTA é requerido")
	private Integer tipoConta;
	@NotNull(message = "O campo VALOR é requerido")
	private BigDecimal valor;
	
	@NotNull(message = "O campo DATA é requerido")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date data;
	
	@NotNull(message = "O campo CONTA ENTRADA é requerido")
	private Conta contaEntrada;

	private Conta contaSaida;
	
	@NotNull(message = "O campo OBSERVACAO é requerido")
	private String observacao;
	

	
		
	public LancamentoDTO(Lancamento lancamento, ContaServico contaServico) {
		this.id = lancamento.getId();
		this.tipoConta = lancamento.getTipoConta();
		this.valor = lancamento.getValor();
		this.data = lancamento.getData();
		if (lancamento.getContaEntrada() != null) {
			this.setContaEntrada(contaServico.findById(lancamento.getContaEntrada()));
		}
		if (lancamento.getContaSaida() != null) {
			this.setContaSaida(contaServico.findById(lancamento.getContaSaida()));
		}
		this.observacao = lancamento.getObservacao();
	}
	
	public LancamentoDTO() {
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



	public Conta getContaEntrada() {
		return contaEntrada;
	}

	public void setContaEntrada(Conta contaEntrada) {
		this.contaEntrada = contaEntrada;
	}

	public Conta getContaSaida() {
		return contaSaida;
	}

	public void setContaSaida(Conta contaSaida) {
		this.contaSaida = contaSaida;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}


	
	
	
	
	
}
