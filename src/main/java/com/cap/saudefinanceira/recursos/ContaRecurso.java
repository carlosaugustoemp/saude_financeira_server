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

import com.cap.saudefinanceira.dominio.Conta;
import com.cap.saudefinanceira.dtos.ContaDTO;
import com.cap.saudefinanceira.servicos.ContaServico;


@RestController
@RequestMapping(value="/contas")
public class ContaRecurso {

	@Autowired
	private ContaServico service;
	

	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ContaDTO> findById(@PathVariable Integer id){
		Conta obj = service.findById(id);
		return ResponseEntity.ok(new ContaDTO(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<ContaDTO>> findAll(){
		List<Conta> list = service.findAll();
		List<ContaDTO> listDTO = list.stream().map(obj -> new ContaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<ContaDTO> create(@Valid @RequestBody ContaDTO objDTO){
		Conta newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ContaDTO> update(@PathVariable Integer id, @Valid @RequestBody ContaDTO objDTO){
		Conta obj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new ContaDTO(obj));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ContaDTO> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
		
		
		
	}
}
