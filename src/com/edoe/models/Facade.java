package com.edoe.models;

import controllers.ControllerItens;
import controllers.ControllerUsuario;

/**
 * Classe que ira se comunicar com os controllers. 
 * Facilitando assim a comunicacao com a interface.
 * 
 * @author Matheus Gusmao
 * @author Davidson Guedes
 * @author Caroliny Silva
 * @author Almir Crispiniano
 */
public class Facade {
	
	/**
	 * Atributo referente ao controle de usuarios.
	 */
	private ControllerUsuario controleUm;
	private ControllerItens controleDois;

	/**
	 * Metodo responsavel pelo carregamento de dados.
	 */
	public void inicializa() {
		controleUm = new ControllerUsuario();
		controleDois = new ControllerItens();
	}

	/**
	 * Metodo que adiciona um usuario doador no sistema.
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
		controleUm.adicionaDoador(nome, id, email, celular, classe);
	}

	/**
	 * Metodo que pesquisa determinado usuario atraves de seu id.
	 * 
	 * @param id
	 * 		String que representa o id do usuario.
	 * 
	 * @return A representacao textual do usuario procurado, se estiver
	 * presente no sistema.
	 */
	public String pesquisaUsuarioPorId(String id) {
		return controleUm.pesquisaUsuarioPorId(id);
	}

	/**
	 * Metodo que pesquisa determinado usuario atraves do seu nome.
	 * 
	 * @param nome
	 * 		String que representa o nome do usuario.
	 * 
	 * @return A representacao textual do usuario procurado, se estiver
	 * presente no sistema.
	 */
	public String pesquisaUsuarioPorNome(String nome) {
		return controleUm.pesquisaUsuarioPorId(nome);
	}

	/**
	 * Metodo que atualiza um usuario atraves de seu id.
	 * 
	 * @param id
	 * 		String que representa o id do usuario.
	 * 
	 * @return Null.
	 */
	public String atualizaUsuario(String id) {
		return controleUm.atualizaUsuario(id);
	}

	/**
	 * Metodo responsavel por remover um usuario do sistema.
	 * 
	 * @param id
	 * 		String que representa o id do usuario.
	 */
	public void removeUsuario(String id) {
		controleUm.removeUsuario(id);
	}
	
	public void adicionaDescritor(String descricao) {
		
	}
	
	public void adicionaItemParaDoacao(String idDoador, String descricaoItem, int quantidade, String tags) {
		
	}
	
	public void exibeItem(String idDoador) {
		
	}
	
	public void atualizaItemParaDoacao(String idDoador) {
		
	}
	
	public void removeItemParaDoacao(String idDoador) {
		
	}
	
	
}