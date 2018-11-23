package com.edoe.models;

import java.util.HashMap;
import java.util.Map;

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
	 * 
	 */
	
	private Map<String, Item> itens;

	/**
	 * Construtor de um usuario doador.
	 * 
	 * @param nome
	 * 		String referente ao nome do usuario.
	 * 
	 * @param email
	 * 		String referente ao email do usuario.
	 * 
	 * @param telefone
	 * 		String referente ao telefone do usuario.
	 * 
	 * @param id
	 * 		String referente ao id do usuario.
	 * 
	 * @param classe
	 * 		String referente a classe do usuario.
	 */
	public Doador(String nome, String email, String telefone, String id, String classe) {
		super(nome, email, telefone, id, classe);
		this.itens = new HashMap<>();

	}

	/**
	 * Metodo responsavel por retornar a representacao textual do usuario doador.
	 */
	@Override
	public String toString() {
		return this.getNome() + "/" + this.getId() + ", " + this.getEmail() + ", " + this.getCelular() + ", status: doador";
	}
	
	/**
	 * 
	 * @param descricao
	 * @return
	 */
	public boolean existeDescritor(String descricao) {
		return this.itens.containsKey(descricao);
	}
	
	public void adicionaDescritor(String descricao) {
	
	}
	
	public void adicionaItemParaDoacao(String descricaoItem, int quantidade, String tags) {
		
	}
	
	public void exibeItem(int idItem) {
		
	}
	
	public void atualizaItemParaDoacao(int idItem, int quantidade, String tags ) {
		
	}
	
	public void removeItemParaDoacao(int idItem) {
		
	}
}
