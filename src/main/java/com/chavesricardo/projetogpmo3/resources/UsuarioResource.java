package com.chavesricardo.projetogpmo3.resources;

import java.net.URI;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.chavesricardo.projetogpmo3.domain.Usuario;
import com.chavesricardo.projetogpmo3.dto.UsuarioDTO;
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
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<UsuarioDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Usuario> list = usuarioService.findPage(page, linesPerPage, orderBy, direction);
		Page<UsuarioDTO> listDto = list.map(obj -> new UsuarioDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		usuarioService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Usuario obj){
		obj = usuarioService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/datas", method=RequestMethod.GET)
	public ResponseEntity<Page<Usuario>> findPage(
			@RequestParam(value="usuario", defaultValue="") String usuario,			//Parâmetros de URL são sempre Strings
			@RequestParam(value="dataInicial", defaultValue="") String dataInicial, //Parâmetros de URL são sempre Strings
			@RequestParam(value="dataFinal", defaultValue="") String dataFinal,     //Parâmetros de URL são sempre Strings
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="data") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Usuario> list = usuarioService.search(usuario, dataInicial, dataFinal, page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
	
/*
@RequestMapping(method = RequestMethod.GET)
public ResponseEntity<Page<ProdutoDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
@RequestParam(value = "direction", defaultValue = "ASC") String direction,
@RequestParam(value = "nome", defaultValue = "") String nome,
@RequestParam(value = "categorias") Integer[] categorias) {
List<Integer> listCategorieIds = Objects.nonNull(categorias) ? Arrays.asList(categorias): Collections.emptyList();
Page<ProdutoDTO> categoriasDto = service.search(name, listCategorieIds, page, linesPerPage, orderBy, direction).map(ProdutoDTO::new);
return ResponseEntity.ok().body(categoriasDto);
}

*/

}
