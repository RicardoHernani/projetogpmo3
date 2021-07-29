package com.chavesricardo.projetogpmo3.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chavesricardo.projetogpmo3.domain.Usuario;
import com.chavesricardo.projetogpmo3.repositories.UsuarioRepository;
import com.chavesricardo.projetogpmo3.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario find(Integer id) {
		Optional<Usuario> obj = usuarioRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto usuário não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}
	
		
}
