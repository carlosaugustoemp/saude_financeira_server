package com.cap.saudefinanceira.servicos;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cap.saudefinanceira.dominio.Conta;
import com.cap.saudefinanceira.dtos.ContaDTO;
import com.cap.saudefinanceira.repositorios.ContaRepositorio;
import com.cap.saudefinanceira.repositorios.LancamentoRepositorio;
import com.cap.saudefinanceira.servicos.excecoes.ObjectNotFoundException;

@Service
public class ContaServico {

	@Autowired
	private ContaRepositorio contaRepositorio;
	
	@Autowired
	private LancamentoRepositorio lancamentoRepositorio;
	
	public Conta findById(Integer id) {
		Optional<Conta> obj = contaRepositorio.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado" + id));
	}

	public List<Conta> findAll() {
		return contaRepositorio.findAll();
	}

	public Conta create(ContaDTO objDTO) {
		objDTO.setId(null);
		Conta newObj = new Conta(objDTO);
		return contaRepositorio.save(newObj);
	}

	public Conta update(Integer id, @Valid ContaDTO objDTO) {
		objDTO.setId(id);
		Conta oldObj = findById(id);
		oldObj = new Conta(objDTO);
		return contaRepositorio.save(oldObj);
	}

	public void delete(Integer id) {
		Optional<Conta> obj = contaRepositorio.findById(id);
		
		if(obj.isPresent() && lancamentoRepositorio.findByContaEntrada(id).size() > 0) {
			throw new DataIntegrityViolationException("Conta utilizada em lancamento e não pode ser deletada!");
		}
		if(obj.isPresent() &&  lancamentoRepositorio.findByContaSaida(id).size() > 0) {
			throw new DataIntegrityViolationException("Conta utilizada em lancamento e não pode ser deletada!");
		}
		contaRepositorio.deleteById(id);
	}

}
