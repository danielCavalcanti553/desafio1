package com.b2wproject.sistema.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.b2wproject.sistema.domain.Planeta;

@Repository
public interface PlanetaRepository extends MongoRepository<Planeta, String>{

	List<Planeta> findByNomeContainingIgnoreCase(String texto);	
	
}
