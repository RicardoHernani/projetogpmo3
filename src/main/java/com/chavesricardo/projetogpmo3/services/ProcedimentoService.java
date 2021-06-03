package com.chavesricardo.projetogpmo3.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chavesricardo.projetogpmo3.domain.Procedimento;
import com.chavesricardo.projetogpmo3.repositories.ProcedimentoRepository;
import com.chavesricardo.projetogpmo3.services.exceptions.ObjectNotFoundException;

@Service
public class ProcedimentoService {

	@Autowired
	private ProcedimentoRepository repo;
	
	public Procedimento find(Integer id) {
		Optional<Procedimento> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto Procedimento não encontrado! Id: " + id + ", Tipo: " + Procedimento.class.getName()));
	}
	
	public Procedimento insert(Procedimento obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Procedimento update(Procedimento obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		repo.deleteById(id);
		}
}
