package com.b2wproject.sistema.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.b2wproject.sistema.domain.Planeta;

public class PlanetaDTO  implements Serializable{
	private static final long serialVersionUID = 1L;

	private String id;
	@NotEmpty(message="Nome é obrigatório")
	private String nome;
	@NotEmpty(message="Clima é obrigatório")
	private String clima;
	@NotEmpty(message="Terreno é obrigatório")
	private String terreno;

	private Integer filmes;
	
	public PlanetaDTO() {
	}

	public PlanetaDTO(Planeta obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.clima = obj.getClima();
		this.terreno = obj.getTerreno();
		this.filmes = obj.getFilmes();
	}
	
	public Planeta fromPlaneta() {
		return new Planeta(this.getId(), this.getNome(), this.getClima(), this.getTerreno(),this.filmes);
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
	
	public Integer getFilmes() {
		return filmes;
	}

	public void setFilmes(Integer filmes) {
		this.filmes = filmes;
	}
}
