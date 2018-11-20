package com.edoe.models;
/**
 * 
 *
 */
public class Doador extends Usuario {

	public Doador(String nome, String email, String telefone, String id, String classe) {
		super(nome, email, telefone, id, classe);
	}

	@Override
	public String toString() {
		return "Doador []";
	}
	
	

}
