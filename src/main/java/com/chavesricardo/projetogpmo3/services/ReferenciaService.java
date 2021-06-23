package com.chavesricardo.projetogpmo3.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.chavesricardo.projetogpmo3.domain.Referencia;
import com.chavesricardo.projetogpmo3.repositories.ReferenciaRepository;
import com.chavesricardo.projetogpmo3.services.exceptions.ObjectNotFoundException;

@Service
public class ReferenciaService {

	@Autowired
	private ReferenciaRepository referenciaRepository;
	
	public Referencia find(Integer id) {
		Optional<Referencia> obj = referenciaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto Referência não encontrado! Id: " + id + ", Tipo: " + Referencia.class.getName()));
	}
	
	public Page<Referencia> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return referenciaRepository.findAll(pageRequest);
	}
}