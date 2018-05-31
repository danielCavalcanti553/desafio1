package com.b2wproject.sistema.config;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.b2wproject.sistema.domain.Planeta;
import com.b2wproject.sistema.repository.PlanetaRepository;

@Configuration
public class MockData implements CommandLineRunner{
	
	@Autowired
	private PlanetaRepository planetaRepository;

	@Override
	public void run(String... args) throws Exception {
		
		planetaRepository.deleteAll();
		Planeta p1 = new Planeta(null,"Terra","Tropical","Tropical Terreno");
		Planeta p2 = new Planeta(null,"Marte","Árido","Árido Terreno");
		planetaRepository.saveAll(Arrays.asList(p1,p2));
		
	}

}
