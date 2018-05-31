package com.b2wproject.sistema.dto;

import java.io.Serializable;

import com.b2wproject.sistema.domain.Planeta;

public class PlanetaDTO  implements Serializable{
	private static final long serialVersionUID = 1L;

	private String id;
	private String nome;
	private String clima;
	private String terreno;
	
	public PlanetaDTO() {
	}

	public PlanetaDTO(Planeta obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.clima = obj.getClima();
		this.terreno = obj.getTerreno();
	}
	
	public Planeta fromPlaneta() {
		return new Planeta(this.getId(), this.getNome(), this.getClima(), this.getTerreno());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}

	public String getTerreno() {
		return terreno;
	}

	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}
	
	
}