package com.chavesricardo.projetogpmo3.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chavesricardo.projetogpmo3.domain.Cirurgia;
import com.chavesricardo.projetogpmo3.domain.Paciente;
import com.chavesricardo.projetogpmo3.domain.Procedimento;
import com.chavesricardo.projetogpmo3.dto.PacienteDTO;
import com.chavesricardo.projetogpmo3.repositories.CirurgiaRepository;
import com.chavesricardo.projetogpmo3.repositories.PacienteRepository;
import com.chavesricardo.projetogpmo3.repositories.ProcedimentoRepository;
import com.chavesricardo.projetogpmo3.services.exceptions.DataIntegrityException;
import com.chavesricardo.projetogpmo3.services.exceptions.ObjectNotFoundException;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repo;
	
	@Autowired
	private CirurgiaRepository cirurgiaRepository;
	
	@Autowired
	private ProcedimentoRepository procedimentoRepository;
	
	public Paciente find(Integer id) {
		Optional<Paciente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto Paciente não encontrado! Id: " + id + ", Tipo: " + Paciente.class.getName()));
	}
	
	public Page<Paciente> search(String dataInicial, String dataFinal, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.search(dataInicial, dataFinal, pageRequest);
	}

	public Paciente insertProntuario(Paciente obj) {	
		return repo.save(obj);
	}
	
	@Transactional
	public Paciente insert(Paciente obj) {
		obj.setId(null);
		obj.setProntuario(obj.getProntuario());
		obj.getProntuario();
		
		for (Cirurgia cirur: obj.getCirurgias()) {
			cirur.setData(cirur.getData());
			cirur.setPaciente(obj);
			cirurgiaRepository.save(cirur);

				for (Procedimento proc: cirur.getProcedimentos()) {
					proc.setTipo(proc.getTipo());
					proc.setPremio(proc.getPremio());
					proc.setCirurgia(cirur);
					procedimentoRepository.save(proc);
				}						
		}
		obj = repo.save(obj);
		return obj;		
	}
	
	public Paciente update(Paciente obj) {
		Paciente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
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
	
	public List<Paciente> findAll() {
		return repo.findAll();
	}
	
	public Page<Paciente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	/*
	public Paciente fromDTO(PacienteDTO objDto) {
		return new Paciente(objDto.getId(), objDto.getProntuario());
	}
	*/
	
	private void updateData(Paciente newObj, Paciente obj) {
		newObj.setId(obj.getId());
		newObj.setProntuario(obj.getProntuario());
	}
	
}
