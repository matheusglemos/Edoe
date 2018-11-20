package com.edoe.models;

/**
 * Classe que representa um usuario como doador.
 * Esta classe e filha da classe usuario.
 * 
 * @author Matheus Gusmao
 * @author Davidson Guedes
 * @author Caroliny Silva
 * @author Almir Crispiniano
 */
public class Doador extends Usuario {

	/**
	 * Construtor de um usuario doador.
	 * 
	 * @param nome
	 * 	String referente ao nome do usuario.
	 * 
	 * @param email
	 * 	String referente ao email do usuario.
	 * 
	 * @param telefone
	 * 	String referente ao telefone do usuario.
	 * 
	 * @param id
	 * 	String referente ao id do usuario.
	 * 
	 * @param classe
	 * 	String referente a classe do usuario.
	 */
	public Doador(String nome, String email, String telefone, String id, String classe) {
		super(nome, email, telefone, id, classe);

	}

	/**
	 * Metodo responsavel por retornar a representacao textual do usuario doador.
	 */
	@Override
	public String toString() {
		return "Doador []";
	}
}
