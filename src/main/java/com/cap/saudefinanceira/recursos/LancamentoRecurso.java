package com.cap.saudefinanceira.recursos;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cap.saudefinanceira.dominio.Lancamento;
import com.cap.saudefinanceira.dtos.LancamentoDTO;
import com.cap.saudefinanceira.servicos.ContaServico;
import com.cap.saudefinanceira.servicos.LancamentoServico;


@RestController
@RequestMapping(value="/lancamentos")
public class LancamentoRecurso {

	@Autowired
	private LancamentoServico service;
	
	@Autowired
	private ContaServico contaServico;
	

	
	@GetMapping(value = "/{id}")
	public ResponseEntity<LancamentoDTO> findById(@PathVariable Integer id){
		Lancamento obj = service.findById(id);
		return ResponseEntity.ok(new LancamentoDTO(obj, contaServico));
	}
	
	@GetMapping
	public ResponseEntity<List<LancamentoDTO>> findAll(){
		List<Lancamento> list = service.findAll();
		List<LancamentoDTO> listDTO = list.stream().map(obj -> new LancamentoDTO(obj, contaServico)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<LancamentoDTO> create(@Valid @RequestBody LancamentoDTO objDTO){
		Lancamento obj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<LancamentoDTO> update(@PathVariable Integer id, @Valid @RequestBody LancamentoDTO objDTO) {
		Lancamento newObj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new LancamentoDTO(newObj, contaServico));
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<LancamentoDTO> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
