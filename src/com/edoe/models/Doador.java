package com.edoe.models;

import java.util.Collection;
import java.util.HashMap;
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
	 * Atributo que represnta um mapa de itens que um usuario doador possui.
	 */

	private Map<Integer, Item> itens;

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
	 * Metodo responsavel por adicionar um item para doacao no mapa de itens
	 * 
	 * @param idItem        Inteiro que representa um id de um item
	 * @param descricaoItem String que representa a descricao de um item
	 * @param quantidade    Inteiro que representa a quantidade de itens
	 * @param tags          String que representa as tags de um item
	 */
	public void adicionaItemParaDoacao(int idItem, String descricaoItem, int quantidade, String tags) {
		Item item = new Item(idItem, descricaoItem, tags, quantidade, this);
		this.itens.put(idItem, item);

	}

	/**
	 * Metodo responsavel por adicionar um item para doacao no mapa de itens
	 * utilizando o objeto Item
	 * 
	 * @param i Item
	 */
	public void adicionaItemParaDoacao(Item i) {
		this.itens.put(i.getidItem(), i);
	}

	/**
	 * Metodo responsavel por verificar a existencia de um item no mapa de itens
	 * utilizando o id do item.
	 * 
	 * @param idItem Inteiro que representa um id de um item
	 * @return
	 */
	public boolean existeItem(int idItem) {
		return this.itens.containsKey(idItem);
	}

	/**
	 * Metodo responsavel por verificar a existencia de um item no mapa de itens
	 * utilizando a descricao do item.
	 * 
	 * @param descricaoItem String que representa a descricao de um item
	 * @return
	 */
	public boolean existeItem(String descricaoItem) {
		for (Item item : itens.values()) {
			if (item.getDescricao().equals(descricaoItem)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo responsavel por exibir a sua representação textual de um item
	 * 
	 * @param idItem Inteiro que representa um id de um item
	 * @return
	 */
	public String exibeItem(int idItem) {
		return this.itens.get(idItem).toString();

	}

	/**
	 * Metodo responsavel por atualizar as tags ou a quantidade de um item
	 * 
	 * @param idItem     Inteiro que representa um id de um item
	 * @param quantidade Inteiro que representa a quantidade de itens
	 * @param tags       String que representa as tags de um item
	 */
	public void atualizaItemParaDoacao(int idItem, int quantidade, String tags) {
		if (this.existeItem(idItem)) {
			if (quantidade > 0) {
				this.itens.get(idItem).setQuantidade(quantidade);
			}
			if (tags != null) {
				this.itens.get(idItem).setTags(tags);
			}
		} else {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem);
		}

	}

	/**
	 * Metodo responsavel por remover um item do mapa de itens
	 * 
	 * @param idItem Inteiro que representa um id de um item
	 */
	public Item removeItemParaDoacao(int idItem) {
		return this.itens.remove(idItem);

	}

	/**
	 * Metodo responsavel por verificar se um usuario possui itens para doacao.
	 * 
	 * @return booleano que confirma se o usuario tem ou nao itens para doacao.
	 */
	public boolean temItensParaDoacao() {
		return this.itens.size() != 0;
	}

	/**
	 * Metodo responsavel por retorna os valores do mapa de itens
	 * 
	 * @return
	 */
	public Collection<Item> getItens() {
		return this.itens.values();
	}
	
	/**
	 * Metodo que retorna um item pelo id do mesmo
	 * 
	 * @param id inteiro que representa o id de um item
	 * @return item
	 */
	public Item getItem(int id) {
		return this.itens.get(id);
	}
}