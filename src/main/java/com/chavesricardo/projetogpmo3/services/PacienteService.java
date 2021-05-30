package com.chavesricardo.projetogpmo3.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.chavesricardo.projetogpmo3.domain.Paciente;
import com.chavesricardo.projetogpmo3.repositories.PacienteRepository;
import com.chavesricardo.projetogpmo3.services.exceptions.DataIntegrityException;
import com.chavesricardo.projetogpmo3.services.exceptions.ObjectNotFoundException;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repo;
	
	public Paciente find(Integer id) {
		Optional<Paciente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Paciente.class.getName()));
	}
	
	public Paciente insert(Paciente obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Paciente update(Paciente obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um paciente que possui cirurgias");
		}
	
	}
}
