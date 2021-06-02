package com.chavesricardo.projetogpmo3.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chavesricardo.projetogpmo3.domain.Cirurgia;
import com.chavesricardo.projetogpmo3.repositories.CirurgiaRepository;
import com.chavesricardo.projetogpmo3.services.exceptions.ObjectNotFoundException;

@Service
public class CirurgiaService {

	@Autowired
	private CirurgiaRepository repo;
	
	public Cirurgia find(Integer id) {
		Optional<Cirurgia> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cirurgia.class.getName()));
	}
	
	public void delete(Integer id) {
		find(id);
		repo.deleteById(id);
		}
}
