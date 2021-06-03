package com.chavesricardo.projetogpmo3.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chavesricardo.projetogpmo3.domain.Referencia;
import com.chavesricardo.projetogpmo3.services.ReferenciaService;

@RestController
@RequestMapping(value="/referencias")
public class ReferenciaResource {
	
	@Autowired
	private ReferenciaService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Referencia> find(@PathVariable Integer id) {
		Referencia obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<Referencia>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="codigo") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Referencia> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
}
