package com.cap.saudefinanceira.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cap.saudefinanceira.dominio.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer>{
	
	Optional<Usuario> findByEmail(String email);

}