package com.chavesricardo.projetogpmo3.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chavesricardo.projetogpmo3.domain.Cirurgia;
import com.chavesricardo.projetogpmo3.domain.Procedimento;
import com.chavesricardo.projetogpmo3.dto.CirurgiaDTO;
import com.chavesricardo.projetogpmo3.repositories.CirurgiaRepository;
import com.chavesricardo.projetogpmo3.repositories.ProcedimentoRepository;
import com.chavesricardo.projetogpmo3.services.exceptions.ObjectNotFoundException;

@Service
public class CirurgiaService {
	
	@Autowired
	private CirurgiaRepository cirurgiaRepository;
	
	@Autowired
	private CirurgiaService cirurgiaService;
	
	@Autowired
	private PacienteService pacienteService;
	
	@Autowired
	private ProcedimentoRepository procedimentoRepository;
	
	@Autowired
	private ProcedimentoService procedimentoService;

	public Cirurgia find(Integer id) {
		Optional<Cirurgia> obj = cirurgiaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto Cirurgia não encontrado! Id: " + id + ", Tipo: " + Cirurgia.class.getName()));
	}
	
	@Transactional
	public Cirurgia insert(Cirurgia obj){
		obj.setId(null);
		obj.setData(obj.getData());
			
			for (Procedimento proc: obj.getProcedimentos()) {
				proc.setTipo(proc.getTipo());
				proc.setPremio(proc.getPremio());
				proc.setCirurgia(obj);
				procedimentoRepository.save(proc);
			}
		
		obj = cirurgiaRepository.save(obj);
		
		return obj;
		
	}
	
	@Transactional
	public Cirurgia update(Cirurgia obj) {
		Cirurgia newObj = find(obj.getId());
		updateData(newObj, obj);
		return cirurgiaRepository.save(newObj);
	}
	
	@Transactional
	public Cirurgia update2(Cirurgia obj) {
		find(obj.getId());	
		obj.setPaciente(pacienteService.find(obj.getId2()));  
		obj.setData(cirurgiaService.find(obj.getId()).getData());
		
		for(Procedimento proced: obj.getProcedimentos()) {
			proced.setTipo(procedimentoService.find(proced.getTipo()).getTipo());
			proced.setPremio(procedimentoService.find(proced.getPremio()).getPremio());		
		}
			
		for (Procedimento proc: obj.getProcedimentos()) {
			proc.setTipo(proc.getTipo());
			proc.setPremio(proc.getPremio());
			proc.setCirurgia(obj);
			procedimentoRepository.save(proc);
		}
		
		obj = cirurgiaRepository.save(obj);	
		return obj;	
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
