package com.chavesricardo.projetogpmo3.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.chavesricardo.projetogpmo3.domain.Cirurgia;
import com.chavesricardo.projetogpmo3.dto.CirurgiaDTO;
import com.chavesricardo.projetogpmo3.repositories.CirurgiaRepository;
import com.chavesricardo.projetogpmo3.services.exceptions.ObjectNotFoundException;

@Service
public class CirurgiaService {

	@Autowired
	private CirurgiaRepository cirurgiaRepository;
	
	
	public Cirurgia find(Integer id) {
		Optional<Cirurgia> obj = cirurgiaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto Cirurgia não encontrado! Id: " + id + ", Tipo: " + Cirurgia.class.getName()));
	}
	
	/*public Cirurgia insert(Cirurgia obj) {
		obj.setId(null);
		
		for?
		obj.setData(obj.getData());
				
		
		obj.
		
		
		obj = repo.save(obj);
		
	}*/
	
	
	public Cirurgia update(Cirurgia obj) {
		Cirurgia newObj = find(obj.getId());
		updateData(newObj, obj);
		return cirurgiaRepository.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		cirurgiaRepository.deleteById(id);
		}
	
	public List<Cirurgia> findAll() {
		return cirurgiaRepository.findAll();
	}
	
	public Page<Cirurgia> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return cirurgiaRepository.findAll(pageRequest);
	}
	
	public Cirurgia fromDTO(CirurgiaDTO objDto) {
		
		return new Cirurgia(objDto.getId(), objDto.getData(), null);
	}
	
	private void updateData(Cirurgia newObj, Cirurgia obj) {
		newObj.setData(obj.getData());
	
	}
}
