package com.cap.saudefinanceira.servicos;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cap.saudefinanceira.dominio.Conta;
import com.cap.saudefinanceira.dominio.Lancamento;
import com.cap.saudefinanceira.dtos.LancamentoDTO;
import com.cap.saudefinanceira.repositorios.LancamentoRepositorio;
import com.cap.saudefinanceira.servicos.excecoes.ObjectNotFoundException;

@Service
public class LancamentoServico {

	@Autowired
	private LancamentoRepositorio repository;
	
	@Autowired
	private ContaServico contaServico;
	
	public Lancamento findById(Integer id) {
		Optional<Lancamento> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID:" + id));
	}

	public List<Lancamento> findAll() {
		return repository.findAll();
	}

	public Lancamento create(@Valid LancamentoDTO objDTO) {
		return repository.save(newLancamento(objDTO));
	}
	
	public Lancamento update(Integer id, @Valid LancamentoDTO objDTO) {
		objDTO.setId(id);
		Lancamento oldObj = findById(id);
		oldObj = newLancamento(objDTO);
		return repository.save(oldObj);
	}
	
	
	public void delete(Integer id) {
		Lancamento obj = findById(id);
		if(obj == null) {
			throw new DataIntegrityViolationException("Lancamento nao encontrado!");
		}
		repository.deleteById(id);
	}
	
	private Lancamento newLancamento(LancamentoDTO obj) {
		Conta contaEntrada = contaServico.findById(obj.getContaEntrada().getId());
		Conta contaSaida = contaServico.findById(obj.getContaSaida().getId());
		
		Lancamento lancamento = new Lancamento();
		if(obj.getId() != null) {
			lancamento.setId(obj.getId());
		}
		lancamento.setContaEntrada(contaEntrada.getId());
		lancamento.setContaSaida(contaSaida.getId());
		lancamento.setData(obj.getData());
		lancamento.setObservacao(obj.getObservacao());
		lancamento.setTipoConta(obj.getTipoConta());
		lancamento.setValor(obj.getValor());
		return lancamento;
	}
}
