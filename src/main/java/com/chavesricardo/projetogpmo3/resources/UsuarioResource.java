package com.chavesricardo.projetogpmo3.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chavesricardo.projetogpmo3.domain.Usuario;
import com.chavesricardo.projetogpmo3.services.UsuarioService;

@RestController
@RequestMapping(value="/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Usuario> find(@PathVariable Integer id) {
		Usuario obj = usuarioService.find(id);
		return ResponseEntity.ok().body(obj);
	}
/*	
	@RequestMapping(value="/datas", method=RequestMethod.GET)
	public ResponseEntity<Page<Paciente>> findPage(
			@RequestParam(value="dataInicial", defaultValue="") String dataInicial, //Parâmetros de URL são sempre Strings
			@RequestParam(value="dataFinal", defaultValue="") String dataFinal,     //Parâmetros de URL são sempre Strings
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="prontuario") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Paciente> list = service.search(dataInicial, dataFinal, page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/prontuario", method=RequestMethod.POST)
	public ResponseEntity<Void> insertProntuario(@Valid @RequestBody PacienteDTO objDto){
		Paciente obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Paciente obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody PacienteDTO objDto, @PathVariable Integer id) {
		Paciente obj = service.fromDTO(objDto);
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
	public ResponseEntity<List<PacienteDTO>> findAll() {
		 List<Paciente> list = service.findAll();
		 List<PacienteDTO> listDto = list.stream().map(obj -> new PacienteDTO(obj)).collect(Collectors.toList());
		 return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<PacienteDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="prontuario") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Paciente> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<PacienteDTO> listDto = list.map(obj -> new PacienteDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	*/
}
