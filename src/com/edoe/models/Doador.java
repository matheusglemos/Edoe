package com.edoe.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Classe que representa um usuario como doador. Esta classe e filha da classe
 * usuario.
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

	private Map<Integer, Item> itens;

	/**
	 * 
	 */
	private Set<String> descricoes;

	/**
	 * Construtor de um usuario doador.
	 * 
	 * @param nome     String referente ao nome do usuario.
	 * 
	 * @param email    String referente ao email do usuario.
	 * 
	 * @param telefone String referente ao telefone do usuario.
	 * 
	 * @param id       String referente ao id do usuario.
	 * 
	 * @param classe   String referente a classe do usuario.
	 */
	public Doador(String nome, String email, String telefone, String id, String classe) {
		super(nome, email, telefone, id, classe);
		this.itens = new HashMap<>();
		this.descricoes = new HashSet<>();

	}

	/**
	 * Metodo responsavel por retornar a representacao textual do usuario doador.
	 */
	@Override
	public String toString() {
		return this.getNome() + "/" + this.getId() + ", " + this.getEmail() + ", " + this.getCelular()
				+ ", status: doador";
	}

	/**
	 * 
	 * @param descricao
	 * @return
	 */
	public boolean existeDescritor(String descricao) {
		return this.descricoes.contains(descricao);
	}

	/**
	 * 
	 * @param descricao
	 * @return
	 */
	public boolean adicionaDescritor(String descricao) {
		if (!this.existeDescritor(descricao)) {
			return this.descricoes.add(descricao);
		}
		throw new IllegalArgumentException("Descritor de Item ja existente: " + descricao);

	}

	/**
	 * 
	 * @param idItem
	 * @param descricaoItem
	 * @param quantidade
	 * @param tags
	 */
	public void adicionaItemParaDoacao(int idItem, String descricaoItem, int quantidade, String tags) {
		Item item = new Item(idItem, descricaoItem, tags, quantidade);
		this.itens.put(idItem, item);

	}
	
	/**
	 * 
	 */
	public boolean existeItem(int idItem) {
		return this.itens.containsKey(idItem);
	}

	/**
	 * 
	 * @param idItem
	 * @return
	 */
	public String exibeItem(int idItem) {
		return this.itens.get(idItem).toString();

	}

	/**
	 * 
	 * @param idItem
	 * @param quantidade
	 * @param tags
	 */
	public void atualizaItemParaDoacao(int idItem, int quantidade, String tags) {
		if(this.existeItem(idItem)) {
			if(quantidade > 0) {
				this.itens.get(idItem).setQuantidade(quantidade);
			}
			if(tags != null) {
				this.itens.get(idItem);
			}
		}else {
			
		}

	}

	/**
	 * 
	 * @param idItem
	 */
	public void removeItemParaDoacao(int idItem) {
		this.itens.remove(idItem);

	}
}
