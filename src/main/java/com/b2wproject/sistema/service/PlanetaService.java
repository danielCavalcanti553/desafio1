package com.b2wproject.sistema.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2wproject.sistema.domain.Planeta;
import com.b2wproject.sistema.repository.PlanetaRepository;
import com.b2wproject.sistema.service.exception.ObjectNotFoundException;

@Service
public class PlanetaService {
	
	@Autowired
	private PlanetaRepository planetaRepository;

	public List<Planeta> findAll(){
		return planetaRepository.findAll();
	}
	
	public Planeta findById(String id) {
		Optional<Planeta> obj = planetaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public Planeta insert(Planeta obj) {
		return planetaRepository.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		planetaRepository.deleteById(id);
	}
	
	public List<Planeta> findByNome(String nome){
		return planetaRepository.findByNomeContainingIgnoreCase(nome);
	}
}
