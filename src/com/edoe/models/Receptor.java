package com.edoe.models;

import java.util.HashMap;
import java.util.Map;

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
	 * 
	 */
	private Map<Integer, ItemNecessario> itensNecessarios;

	/**
	 * Construtor de um usuario receptor.
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
	 *
	 */
	public Receptor(String nome, String email, String telefone, String id, String classe) {
		super(nome, email, telefone, id, classe);
		this.itensNecessarios = new HashMap<>();
	}

	@Override
	public String toString() {
		return this.getNome() + "/" + this.getId() + ", " + this.getEmail() + ", " + this.getCelular()
				+ ", status: receptor";
	}

	/**
	 * Metodo responsavel por adicionar um item necesaario para recepção no mapa de
	 * itens necessarios
	 * 
	 * @param itemNecId     Inteiro que representa um id de um item necessario
	 * @param descricaoItem String que representa a descricao de um item necessario
	 * @param quantidade    Inteiro que representa a quantidade de itens necessarios
	 * @param tags          String que representa as tags de um item necessario
	 */
	public void adicionaItemNecessario(int itemNecId, String descricaoItem, int quantidade, String tags) {
		ItemNecessario item = new ItemNecessario(itemNecId, descricaoItem, quantidade, tags, this);
		this.itensNecessarios.put(itemNecId, item);
	}

	/**
	 * Metodo responsavel por verificar a existencia de um item necessario no mapa
	 * de itens necessarios.
	 * 
	 * @param idItem Inteiro que representa um id de um item necessario
	 * @return booleano
	 */
	public boolean existeItemNecessario(int itemNecId) {
		return this.itensNecessarios.containsKey(itemNecId);
	}

	public void atualizaItemNecessario(int itemNecId, int quantidade, String tags) {
		
	}

	/**
	 * Metodo responsavel por remover um item necessario do mapa de itens
	 * necessarios
	 * 
	 * @param itemNecId Inteiro que representa um id de um item necessario
	 */
	public void removeItemNecessario(int itemNecId) {
		this.itensNecessarios.remove(itemNecId);

	}

}
