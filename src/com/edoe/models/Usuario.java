package com.edoe.models;

/**
 * 
 * 
 *
 */
public abstract class Usuario {

	/**
	 * 
	 */

	private String nome;
	private String email;
	private String celular;
	private String id;
	private String classe;

	/**
	 * 
	 * @param nome
	 * @param email
	 * @param telefone
	 * @param id
	 * @param classe
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getId() {
		return id;
	}

	public String getClasse() {
		return classe;
	}
	
	/**
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	/**
	 * 
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
	 * 
	 */
	@Override
	public String toString() {
		return this.getNome() + "/" + this.getId() + ", " + this.getEmail() + ", " + this.getCelular() + ", status: ";
	}

}
