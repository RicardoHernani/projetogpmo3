package com.chavesricardo.projetogpmo3.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.chavesricardo.projetogpmo3.domain.Procedimento;
import com.chavesricardo.projetogpmo3.services.ProcedimentoService;

@RestController
@RequestMapping(value="/procedimentos")
public class ProcedimentoResource {
	
	@Autowired
	private ProcedimentoService procedimentoService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Procedimento> find(@PathVariable Integer id) {
		Procedimento obj = procedimentoService.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Procedimento obj){
		obj = procedimentoService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Procedimento obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = procedimentoService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		procedimentoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
