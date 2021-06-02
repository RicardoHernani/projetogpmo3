package com.chavesricardo.projetogpmo3.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chavesricardo.projetogpmo3.domain.Cirurgia;
import com.chavesricardo.projetogpmo3.services.CirurgiaService;

@RestController
@RequestMapping(value="/cirurgias")
public class CirurgiaResource {
	
	@Autowired
	private CirurgiaService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Cirurgia> find(@PathVariable Integer id) {
		Cirurgia obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
