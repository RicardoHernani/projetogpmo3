package com.chavesricardo.projetogpmo3.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chavesricardo.projetogpmo3.domain.Cirurgia;
import com.chavesricardo.projetogpmo3.dto.CirurgiaDTO;
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
	
	/*@RequestMapping(method=RequestMethod.POST)               //fazer um CirurgiaNewDTO????
	public ResponseEntity<Void> insert(@RequestBody Cirurgia obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}*/
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CirurgiaDTO objDto, @PathVariable Integer id) {
		Cirurgia obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CirurgiaDTO>> findAll() {
		 List<Cirurgia> list = service.findAll();
		 List<CirurgiaDTO> listDto = list.stream().map(obj -> new CirurgiaDTO(obj)).collect(Collectors.toList());
		 return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<CirurgiaDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="data") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Cirurgia> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CirurgiaDTO> listDto = list.map(obj -> new CirurgiaDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
}
