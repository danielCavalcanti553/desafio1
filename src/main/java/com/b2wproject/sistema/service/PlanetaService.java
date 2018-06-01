package com.b2wproject.sistema.service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.b2wproject.sistema.domain.Planeta;
import com.b2wproject.sistema.repository.PlanetaRepository;
import com.b2wproject.sistema.service.exception.ObjectNotFoundException;
import com.b2wproject.sistema.service.exception.UnavailableServiceException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PlanetaService {

	@Autowired
	private PlanetaRepository planetaRepository;

	public Page<Planeta> findAll(Integer page, Integer linesPerPage, String order, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), order);
		return planetaRepository.findAll(pageRequest);
	}

	public Planeta findById(String id) {
		Optional<Planeta> obj = planetaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public Planeta insert(Planeta obj) {
		
		obj.setFilmes(0);
		
		try {
			obj.setFilmes(searchFilms(obj.getNome()));
			System.out.println("------>" + searchFilms(obj.getNome()));

		} catch (IOException e) {
			e.printStackTrace();

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return planetaRepository.insert(obj);
	}

	public void delete(String id) {
		findById(id);
		planetaRepository.deleteById(id);
	}

	
	public Integer searchFilms(String namePlanet) throws IOException {

		Integer qtdFilmes;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:55.0) Gecko/20100101 Firefox/55.0");
		String url = "https://swapi.co/api/planets?search=" + namePlanet;

		HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		try {
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(response.getBody());
			JsonNode locatedNode = rootNode.path("results").findValue("films");
			List<String> list = mapper.readValue(locatedNode.toString(), new TypeReference<List<String>>() {
			});
			qtdFilmes = list.size();
			
		}else {
			qtdFilmes = 0;
		}
		
		}catch(HttpClientErrorException e) {
			throw new UnavailableServiceException("Web service Star War não está disponível");
		}catch(NullPointerException e) {
			qtdFilmes = 0;
		}catch(ResourceAccessException e){
			throw new UnavailableServiceException("Web service Star War não está disponível");

		}
		
		return qtdFilmes;
	}
	
	public Page<Planeta> search(
			String nome, 
			Integer page, 
			Integer linesPerPage, 
			String order,
			String direction) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), order);
		return planetaRepository.findByNomeContainingIgnoreCase(nome,pageRequest);
	}
}
