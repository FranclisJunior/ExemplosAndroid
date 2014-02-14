package com.galdino.universitycalculator;

public class Disciplina {
	
	private int id;
	private String nome;
	private double media;
	private int creditos;
	private int horas;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public double getMedia() {
		return media;
	}
	public void setMedia(double media) {
		this.media = media;
	}
	public int getCreditos() {
		return creditos;
	}
	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}
	public int getHoras() {
		return horas;
	}
	public void setHoras(int horas) {
		this.horas = horas;
	}
	public void setNome(String nome) {
		this.nome=nome;
		
	}
	
	

}
