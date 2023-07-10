package com.cap.saudefinanceira.services;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cap.saudefinanceira.dominio.Conta;
import com.cap.saudefinanceira.dominio.Lancamento;
import com.cap.saudefinanceira.dominio.Usuario;
import com.cap.saudefinanceira.enums.TipoConta;
import com.cap.saudefinanceira.repositorios.ContaRepositorio;
import com.cap.saudefinanceira.repositorios.LancamentoRepositorio;
import com.cap.saudefinanceira.repositorios.TipoContaRepositorio;
import com.cap.saudefinanceira.repositorios.UsuarioRepositorio;
import com.cap.saudefinanceira.servicos.ContaServico;

@Service
public class DBService {
	
	
	@Autowired
	private ContaRepositorio contaRepositorio;
	
	@Autowired
	private LancamentoRepositorio lancamentoRepositorio;
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	private TipoContaRepositorio tipoContaRepositorio;
	
	@Autowired
	private ContaServico contaServico;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public void instanciaDB() {
		
		com.cap.saudefinanceira.dominio.TipoConta tipoConta0 = new com.cap.saudefinanceira.dominio.TipoConta("Receitas", 0);
		tipoContaRepositorio.save(tipoConta0);
		
		com.cap.saudefinanceira.dominio.TipoConta tipoConta1 = new com.cap.saudefinanceira.dominio.TipoConta("Despesas", 1);
		tipoContaRepositorio.save(tipoConta1);
		
		com.cap.saudefinanceira.dominio.TipoConta tipoConta2 = new com.cap.saudefinanceira.dominio.TipoConta("Transferências", 2);
		tipoContaRepositorio.save(tipoConta2);
		
		
		Conta contaSalario = new Conta("Salário", 1);
		contaRepositorio.save(contaSalario);
		
		Conta contaPLR = new Conta("PLR", 1);
		contaRepositorio.save(contaPLR);
		
		Conta contaGasolina = new Conta("Gasolina", 2);
		contaRepositorio.save(contaGasolina);
		
		Conta contaBcoCaixa = new Conta("Bco Caixa", 2);
		contaRepositorio.save(contaBcoCaixa);
		
		Conta contaHigiene = new Conta("Higiene", 2);
		contaRepositorio.save(contaHigiene);
		
		Conta contaRoupas = new Conta("Roupas", 2);
		contaRepositorio.save(contaRoupas);
		
		Conta contaSaude = new Conta("Saúde", 2);
		contaRepositorio.save(contaSaude);
		
		Conta contaCarro = new Conta("Carro", 2);
		contaRepositorio.save(contaCarro);
		
		Conta contaEducacao = new Conta("Educação", 2);
		contaRepositorio.save(contaEducacao);
		
		Conta contaAcademia = new Conta("Academia", 2);
		contaRepositorio.save(contaAcademia);
		
		Conta contaPresentes = new Conta("Presentes", 2);
		contaRepositorio.save(contaPresentes);
		
		Conta contaApartamento = new Conta("Apartamento", 2);
		contaRepositorio.save(contaApartamento);
		
		Conta contaPlantas = new Conta("Plantas", 2);
		contaRepositorio.save(contaPlantas);
		
		Conta contaNuInvest = new Conta("NuInvest", 3);
		contaRepositorio.save(contaNuInvest);
		
	
		Conta contaSaidaSal = contaServico.findById(contaSalario.getId());
		Conta contaEntradaGas = contaServico.findById(contaGasolina.getId());
		
		
		Lancamento lancamento = new Lancamento(TipoConta.REMUNERACAO.getCodigo(), new BigDecimal(1800), new Date(), contaSalario, null, "Pagamento");
		Lancamento lancamento2 = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(120), new Date(), contaEntradaGas, contaSaidaSal, "Posto Santos Dumont");
		Lancamento lancamento3 = new Lancamento(TipoConta.TRANSFERENCIA.getCodigo(), new BigDecimal(80), new Date(), contaNuInvest, contaSaidaSal, "Investimento");
		Lancamento lancamento4 = new Lancamento(TipoConta.REMUNERACAO.getCodigo(), new BigDecimal(120), new Date(), contaPLR, null, "PLR");
		Lancamento lancamento5 = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(150), new Date(), contaBcoCaixa, contaSaidaSal, "Parcela");
		Lancamento lancamento6 = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(11.58), new Date(), contaHigiene, contaSaidaSal, "Desodorante");
		Lancamento lancamento7 = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(25.54), new Date(), contaRoupas, contaSaidaSal, "Calça");
		Lancamento lancamento8 = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(10.90), new Date(), contaSaude, contaSaidaSal, "Remédio");
		Lancamento lancamento9 = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(70.88), new Date(), contaCarro, contaSaidaSal, "Óleo");
		Lancamento lancamento10 = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(24.55), new Date(), contaEducacao, contaSaidaSal, "Curso");
		Lancamento lancamento11 = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(45.59), new Date(), contaAcademia, contaSaidaSal, "Academia");
		Lancamento lancamento12 = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(15.87), new Date(), contaPresentes, contaSaidaSal, "Fulano");
		Lancamento lancamento13 = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(7.54), new Date(), contaApartamento, contaSaidaSal, "Torneira");
		Lancamento lancamento14 = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(12.25), new Date(), contaPlantas, contaSaidaSal, "Adubo");
		Lancamento lancamento15 = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(51.55), new Date(), contaPlantas, contaSaidaSal, "Arame");
		
		
		lancamentoRepositorio.save(lancamento);

		lancamentoRepositorio.save(lancamento2);
		lancamentoRepositorio.save(lancamento3);
		lancamentoRepositorio.save(lancamento4);
		lancamentoRepositorio.save(lancamento5);
		lancamentoRepositorio.save(lancamento6);
		lancamentoRepositorio.save(lancamento7);
		lancamentoRepositorio.save(lancamento8);
		lancamentoRepositorio.save(lancamento9);
		lancamentoRepositorio.save(lancamento10);
		lancamentoRepositorio.save(lancamento11);
		lancamentoRepositorio.save(lancamento12);
		lancamentoRepositorio.save(lancamento13);
		lancamentoRepositorio.save(lancamento14);
		lancamentoRepositorio.save(lancamento15);
		
		Usuario usuario = new Usuario("carlos@gmail.com", encoder.encode("123"));
		usuarioRepositorio.save(usuario);
		
		
		
	}
}