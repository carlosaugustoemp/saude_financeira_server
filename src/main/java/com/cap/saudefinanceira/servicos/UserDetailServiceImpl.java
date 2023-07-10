package com.cap.saudefinanceira.servicos;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cap.saudefinanceira.dominio.Usuario;
import com.cap.saudefinanceira.repositorios.UsuarioRepositorio;
import com.cap.saudefinanceira.seguranca.UserSS;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepositorio repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Usuario> user = repository.findByEmail(email);
		if (user.isPresent()) {
			return new UserSS(user.get().getId(), user.get().getEmail(), user.get().getSenha());
		}
		throw new UsernameNotFoundException(email);
	}

}
