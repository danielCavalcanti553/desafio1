package com.b2wproject.sistema.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.b2wproject.sistema.domain.Planeta;
import com.b2wproject.sistema.dto.PlanetaDTO;
import com.b2wproject.sistema.resources.util.URLDecode;
import com.b2wproject.sistema.service.PlanetaService;

@RestController
@RequestMapping(value="/planetas")
public class PlanetaResource {

	@Autowired
	private PlanetaService planetaService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<PlanetaDTO>> findAll(){
		
		List<Planeta> list = planetaService.findAll();
		List<PlanetaDTO> dto = list.stream().map(obj -> new PlanetaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(dto);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<PlanetaDTO> findById(@PathVariable String id){	
		Planeta planeta = planetaService.findById(id);
		return ResponseEntity.ok().body(new PlanetaDTO(planeta));
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody PlanetaDTO dto){
		Planeta planeta = planetaService.insert(dto.fromPlaneta());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(planeta.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id){	
		planetaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public ResponseEntity<List<PlanetaDTO>> findByName(@RequestParam(value="nome", defaultValue="") String nome){
		
		nome = URLDecode.decodeParam(nome);
		List<Planeta> list = planetaService.findByNome(nome);
		List<PlanetaDTO> dto = list.stream().map(obj -> new PlanetaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(dto);
	}
	
	
}
