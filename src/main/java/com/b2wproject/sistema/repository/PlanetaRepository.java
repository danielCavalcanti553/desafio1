package com.b2wproject.sistema.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.b2wproject.sistema.domain.Planeta;

@Repository
public interface PlanetaRepository extends MongoRepository<Planeta, String>{

	Page<Planeta> findByNomeContainingIgnoreCase(String nome,Pageable pageRequest);	
	
}
