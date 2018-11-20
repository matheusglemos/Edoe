package com.edoe.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe que controla o usuario. Adiciona, pesquisa
 * atualiza e remove usuarios.
 * 
 * @author Matheus Gusmao
 * @author Davidson Guedes
 * @author Caroliny Silva
 * @author Almir Crispiniano
 *
 */
public class ControllerUsuario {
	
	/**
	 * Atributo que representa os doadores.
	 */
	private Map<String, Doador> doadores;
	
	/**
	 * Construtor do controlador de usuarios.
	 */
	public ControllerUsuario() {
		this.doadores = new HashMap<>();
	}
	
	/**
	 * Metodo responsavel por adicionar um usuario doador.
	 * 
	 * @param nome
	 * 		String que representa o nome do usuario doador.
	 * 
	 * @param id
	 * 		String que representa o id do usuario doador.
	 * 
	 * @param email
	 * 		String que representa o email do usuario doador.
	 * 
	 * @param celular
	 * 		String que representa o celular do usuario doador.
	 * 
	 * @param classe
	 * 		String que representa a classe do usuario doador.
	 */
	public void adicionaDoador(String nome, String id, String email, String celular, String classe) {
		Doador doador = new Doador(nome, email, celular, id, classe);
		this.doadores.put(id, doador);
	}
	
	/**
	 * Metodo responsavel por pesquisar um usuario atraves de sua identificacao.
	 * 
	 * @param id
	 * 		String que representa o id do usuario.
	 * 
	 * @return A representacao textual do usuario, por meio da pesquisa de seu id.
	 */
	public String pesquisaUsuarioPorId(String id) {
		return this.doadores.get(id).toString();
	}
	
	/**
	 *  Metodo responsavel por atualizar um usuario no sistema.
	 *  
	 * @param id
	 * 		String que representa o id do usuario.
	 * 
	 * @return null.
	 */
	public String atualizaUsuario(String id) {
		return null;
	}
	
	/**
	 * Metodo responsavel por remover um usuario do sistema.
	 * 
	 * @param id
	 * 		String que representa o id do usuario.
	 */
	public void removeUsuario(String id) {
		this.doadores.remove(id);
	}

}
