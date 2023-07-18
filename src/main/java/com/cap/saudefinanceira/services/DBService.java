package com.cap.saudefinanceira.services;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date data = sdf.parse("04/07/2023");
			Lancamento lancamento = new Lancamento(TipoConta.REMUNERACAO.getCodigo(), new BigDecimal(1800), data, contaSalario, null, "Pagamento");
			lancamentoRepositorio.save(lancamento);
			
			data = sdf.parse("05/07/2023");
			lancamento = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(120), data, contaEntradaGas, contaSaidaSal, "Posto Santos Dumont");
			lancamentoRepositorio.save(lancamento);
			
			data = sdf.parse("06/07/2023");
			lancamento = new Lancamento(TipoConta.TRANSFERENCIA.getCodigo(), new BigDecimal(80), data, contaNuInvest, contaSaidaSal, "Investimento");
			lancamentoRepositorio.save(lancamento);
			
			data = sdf.parse("07/07/2023");
			lancamento = new Lancamento(TipoConta.REMUNERACAO.getCodigo(), new BigDecimal(120), data, contaPLR, null, "PLR");
			lancamentoRepositorio.save(lancamento);
			
			data = sdf.parse("08/07/2023");
			lancamento = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(150), data, contaBcoCaixa, contaSaidaSal, "Parcela");
			lancamentoRepositorio.save(lancamento);
			
			data = sdf.parse("09/07/2023");
			lancamento = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(11.58), data, contaHigiene, contaSaidaSal, "Desodorante");
			lancamentoRepositorio.save(lancamento);
			
			data = sdf.parse("10/07/2023");
			lancamento = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(25.54), data, contaRoupas, contaSaidaSal, "Calça");
			lancamentoRepositorio.save(lancamento);
			
			data = sdf.parse("11/07/2023");
			lancamento = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(10.90), data, contaSaude, contaSaidaSal, "Remédio");
			lancamentoRepositorio.save(lancamento);
			
			data = sdf.parse("12/07/2023");
			lancamento = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(70.88), data, contaCarro, contaSaidaSal, "Óleo");
			lancamentoRepositorio.save(lancamento);
			
			data = sdf.parse("13/07/2023");
			lancamento = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(24.55), data, contaEducacao, contaSaidaSal, "Curso");
			lancamentoRepositorio.save(lancamento);
			
			data = sdf.parse("14/07/2023");
			lancamento = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(45.59), data, contaAcademia, contaSaidaSal, "Academia");
			lancamentoRepositorio.save(lancamento);
			
			data = sdf.parse("15/07/2023");
			lancamento = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(15.87), data, contaPresentes, contaSaidaSal, "Fulano");
			lancamentoRepositorio.save(lancamento);
			
			data = sdf.parse("16/07/2023");
			lancamento = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(7.54), data, contaApartamento, contaSaidaSal, "Torneira");
			lancamentoRepositorio.save(lancamento);
			
			data = sdf.parse("17/07/2023");
			lancamento = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(12.25), data, contaPlantas, contaSaidaSal, "Adubo");
			lancamentoRepositorio.save(lancamento);
			
			data = sdf.parse("18/07/2023");
			lancamento = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(51.55), data, contaPlantas, contaSaidaSal, "Arame");
			lancamentoRepositorio.save(lancamento);
		
			data = sdf.parse("18/07/2023");
			lancamento = new Lancamento(TipoConta.TRANSFERENCIA.getCodigo(), new BigDecimal(30), data, contaSalario, contaNuInvest, "Resgate 30");
			lancamentoRepositorio.save(lancamento);
			
			data = sdf.parse("18/07/2023");
			lancamento = new Lancamento(TipoConta.TRANSFERENCIA.getCodigo(), new BigDecimal(1), data, contaNuInvest, contaSalario, "Investimento 1 real");
			lancamentoRepositorio.save(lancamento);
			
			data = sdf.parse("04/06/2023");
			lancamento = new Lancamento(TipoConta.REMUNERACAO.getCodigo(), new BigDecimal(1800), data, contaSalario, null, "Pagamento");
			lancamentoRepositorio.save(lancamento);	
			
			data = sdf.parse("05/06/2023");
			lancamento = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(120), data, contaEntradaGas, contaSaidaSal, "Posto Santos Dumont");
			lancamentoRepositorio.save(lancamento);
			
			data = sdf.parse("06/06/2023");
			lancamento = new Lancamento(TipoConta.TRANSFERENCIA.getCodigo(), new BigDecimal(80), data, contaNuInvest, contaSaidaSal, "Investimento");
			lancamentoRepositorio.save(lancamento);
			
			data = sdf.parse("06/06/2023");
			lancamento = new Lancamento(TipoConta.REMUNERACAO.getCodigo(), new BigDecimal(120), data, contaPLR, null, "PLR");
			lancamentoRepositorio.save(lancamento);
			
			data = sdf.parse("08/06/2023");
			lancamento = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(150), data, contaBcoCaixa, contaSaidaSal, "Parcela");
			lancamentoRepositorio.save(lancamento);
			
			data = sdf.parse("09/06/2023");
			lancamento = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(11.58), data, contaHigiene, contaSaidaSal, "Desodorante");
			lancamentoRepositorio.save(lancamento);
			
			data = sdf.parse("10/06/2023");
			lancamento = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(25.54), data, contaRoupas, contaSaidaSal, "Calça");
			lancamentoRepositorio.save(lancamento);
			
			data = sdf.parse("11/06/2023");
			lancamento = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(10.90), data, contaSaude, contaSaidaSal, "Remédio");
			lancamentoRepositorio.save(lancamento);
			
			data = sdf.parse("12/06/2023");
			lancamento = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(70.88), data, contaCarro, contaSaidaSal, "Óleo");
			lancamentoRepositorio.save(lancamento);
			
			data = sdf.parse("13/06/2023");
			lancamento = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(24.55), data, contaEducacao, contaSaidaSal, "Curso");
			lancamentoRepositorio.save(lancamento);
			
			data = sdf.parse("14/06/2023");
			lancamento = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(45.59), data, contaAcademia, contaSaidaSal, "Academia");
			lancamentoRepositorio.save(lancamento);
			
			data = sdf.parse("15/06/2023");
			lancamento = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(15.87), data, contaPresentes, contaSaidaSal, "Fulano");
			lancamentoRepositorio.save(lancamento);
			
			data = sdf.parse("16/06/2023");
			lancamento = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(7.54), data, contaApartamento, contaSaidaSal, "Torneira");
			lancamentoRepositorio.save(lancamento);
			
			data = sdf.parse("17/06/2023");
			lancamento = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(12.25), data, contaPlantas, contaSaidaSal, "Adubo");
			lancamentoRepositorio.save(lancamento);
			
			data = sdf.parse("18/06/2023");
			lancamento = new Lancamento(TipoConta.DESPESA.getCodigo(), new BigDecimal(51.55), data, contaPlantas, contaSaidaSal, "Arame");
			lancamentoRepositorio.save(lancamento);		
			
			
			
			
			
			
			
			
			
			Usuario usuario = new Usuario("carlos@gmail.com", encoder.encode("123"));
			usuarioRepositorio.save(usuario);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
		
	}
}