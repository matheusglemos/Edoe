package com.edoe.models;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 *
 */
public class ControllerUsuario {
	/**
	 * 
	 */
	private Map<String, Doador> doadores;
	
	public ControllerUsuario() {
		this.doadores = new HashMap<>();
	}
	
	/**
	 * 
	 * @param nome
	 * @param id
	 * @param email
	 * @param celular
	 * @param classe
	 */
	public void adicionaDoador(String nome, String id, String email, String celular, String classe) {
		Doador doador = new Doador(nome, email, celular, id, classe);
		this.doadores.put(id, doador);

	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public String pesquisaUsuarioPorId(String id) {
		return this.doadores.get(id).toString();
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public String atualizaUsuario(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public void removeUsuario(String id) {
		this.doadores.remove(id);
	}

}
