package com.edoe.models;

/**
 * Classe que representa um usuario como receptor. Esta classe e filha da classe
 * usuario.
 * 
 * @author Matheus Gusmao
 * @author Davidson Guedes
 * @author Caroliny Silva
 * @author Almir Crispiniano
 */
public class Receptor extends Usuario {

	/**
	 * Construtor de um usuario receptor.
	 * 
	 * @param nome
	 *            String referente ao nome do usuario.
	 * 
	 * @param email
	 *            String referente ao email do usuario.
	 * 
	 * @param telefone
	 *            String referente ao telefone do usuario.
	 * 
	 * @param id
	 *            String referente ao id do usuario.
	 * 
	 * @param classe
	 *            String referente a classe do usuario.
	 * @throws Exception
	 */
	public Receptor(String nome, String email, String telefone, String id, String classe) {
		super(nome, email, telefone, id, classe);
	}

	@Override
	public String toString() {
		return this.getNome() + "/" + this.getId() + ", " + this.getEmail() + ", " + this.getCelular()
				+ ", status: receptor";
	}

}
