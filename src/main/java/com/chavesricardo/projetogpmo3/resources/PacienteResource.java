package com.chavesricardo.projetogpmo3.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chavesricardo.projetogpmo3.domain.Paciente;

@RestController
@RequestMapping(value="/pacientes")
public class PacienteResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Paciente> listar() {
	
		Paciente pct1 = new Paciente(1, 11111111);
		Paciente pct2 = new Paciente(2, 22222222);
	
		List<Paciente> lista = new ArrayList<>();
		lista.add(pct1);
		lista.add(pct2);
	
		return lista;
	}
}
