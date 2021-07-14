package com.chavesricardo.projetogpmo3.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chavesricardo.projetogpmo3.domain.Procedimento;
import com.chavesricardo.projetogpmo3.repositories.ProcedimentoRepository;
import com.chavesricardo.projetogpmo3.services.exceptions.ObjectNotFoundException;

@Service
public class ProcedimentoService {

	@Autowired
	private ProcedimentoRepository procedimentoRepository;
	
	public Procedimento find(Integer id) {
		Optional<Procedimento> obj = procedimentoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto Procedimento não encontrado! Id: " + id + ", Tipo: " + Procedimento.class.getName()));
	}
	
	@Transactional
	public Procedimento insert(Procedimento obj) {
		obj.setId(null);
		
		obj.setTipo(obj.getTipo());
		obj.setPremio(obj.getPremio());
		obj.getReferencia();
		obj.getCirurgia();
		procedimentoRepository.save(obj);
		return obj;
	}
	
	@Transactional
	public Procedimento update(Procedimento obj) {
		find(obj.getId());
		return procedimentoRepository.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		procedimentoRepository.deleteById(id);
		}
}
