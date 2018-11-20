package com.edoe.models;

/**
 * Classe abstrata que representa um usuario no sistema. 
 * Cada usuario possui nome, email, celular, id e uma classe.
 * 
 * @author Matheus Gusmao
 * @author Davidson Guedes
 * @author Almir Crispiniano
 * @author Caroliny Silva
 *
 */
public abstract class Usuario {

	/**
	 * Atributo que representa o nome do usuario.
	 */
	private String nome;
	
	/**
	 * Atributo que representa o email do usuario.
	 */
	private String email;
	
	/**
	 * Atributo que representa o celular do usuario.
	 */
	private String celular;
	
	/**
	 * Atributo que representa o id do usuario.
	 */
	private String id;
	
	/**
	 * Atributo que representa a classe do usuario.
	 */
	private String classe;

	/**
	 * Construtor de um usuario.
	 * 
	 * @param nome 
	 * 			String que representa o nome do usuario.
	 * 
	 * @param email 
	 * 			String que representa o email do usuario.
	 * 
	 * @param telefone 
	 * 			String que representa o telefone do usuario.
	 * 
	 * @param id 
	 * 			String que representa a identificacap do usuario.
	 * 
	 * @param classe 
	 * 			String que representa a classe correspondente ao usuario.
	 */
	public Usuario(String nome, String email, String celular, String id, String classe) {

		if (nome == null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: nome nao pode ser vazio ou nulo.");
		}
		if (email == null || email.trim().isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: email nao pode ser vazio ou nulo.");
		}
		if (celular == null || celular.trim().isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: celular nao pode ser vazio ou nulo.");
		}
		if (id == null || id.trim().isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.id = id;
		this.classe = classe;
	}

	/**
	 * Metodo que retorna o nome do usuario.
	 * 
	 * @return String contendo o nome do usuario.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Metodo responsavel por alterar o nome do usuario.
	 * 
	 * @param nome 
	 * 			String que representa o nome do usuario.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Metodo que retorna o email do usuario.
	 * 
	 * @return String contendo o email do usuario.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Metodo responsavel por alterar o email do usuario.
	 * 
	 * @param email
	 * 			String que representa o email do usuario.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Metodo que retorna o celular de um usuario.
	 * 
	 * @return String contendo o celular do usuario.
	 */
	public String getCelular() {
		return celular;
	}

	/**
	 * Metodo responsavel por alterar o celular do usuario.
	 * 
	 * @param celular
	 * 			String que representa o celular do usuario.
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}

	/**
	 * Metodo que retorna o id do usuario.
	 * 
	 * @return String contendo o id do usuario.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Metodo que retorna a classe do usuario.
	 * 
	 * @return String contendo a classe do usuario.
	 */
	public String getClasse() {
		return classe;
	}
	
	/**
	 * Metodo que gera o hashcode do usuario.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	/**
	 * Metodo responsavel por comparar um usuario com outro a partir de
	 * sua identificacao (id).
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	/**
	 * Metodo responsavel que retorna a representacao textual do usuario.
	 */
	@Override
	public String toString() {
		return this.getNome() + "/" + this.getId() + ", " + this.getEmail() + ", " + this.getCelular() + ", status: ";
	}

}
