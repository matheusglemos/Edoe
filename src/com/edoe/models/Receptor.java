package com.edoe.models;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import excecoes.ItemNaoEncontradoException;

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
	 * Atributo que representa um mapa de itens necessarios que um usuario receptor
	 * possui.
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

	/**
	 * Metodo responsavel por retornar a representacao textual de um receptor.
	 */
	@Override
	public String toString() {
		return this.getNome() + "/" + this.getId() + ", " + this.getEmail() + ", " + this.getCelular()
				+ ", status: receptor";
	}

	/**
	 * Metodo responsavel por adicionar um item necesaario para recepcao no mapa de
	 * itens necessarios.
	 * 
	 * @param itemNecId     Inteiro que representa um id de um item necessario.
	 * @param descricaoItem String que representa a descricao de um item necessario.
	 * @param quantidade    Inteiro que representa a quantidade de itens
	 *                      necessarios.
	 * @param tags          String que representa as tags de um item necessario.
	 */
	public void adicionaItemNecessario(int itemNecId, String descricaoItem, int quantidade, String tags) {
		ItemNecessario item = new ItemNecessario(itemNecId, descricaoItem, quantidade, tags, this);
		this.itensNecessarios.put(itemNecId, item);
	}

	/**
	 * Metodo responsavel por adicionar um item no mapa de itens necessarios
	 * utilizando o objeto Item necessario.
	 * 
	 * @param i Item necessario.
	 */
	public void adicionaUmItemNecessario(ItemNecessario i) {
		this.itensNecessarios.put(i.getItemNecId(), i);
	}

	/**
	 * Metodo responsavel por verificar a existencia de um item necessario no mapa
	 * de itens necessarios.
	 * 
	 * @param i correspondente a um item necessario.
	 * 
	 * @return booleano.
	 */
	public boolean existeItemNecessario(ItemNecessario i) {
		for (ItemNecessario item : itensNecessarios.values()) {
			if (item.equals(i)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo responsavel por verificar a existencia de um item necessario no mapa
	 * de itens necessarios.
	 * 
	 * @param idItem Inteiro que representa um id de um item necessario.
	 * 
	 * @return booleano.
	 */
	public boolean existeItemNecessario(int idItem) {
		return this.itensNecessarios.containsKey(idItem);
	}

	/**
	 * Metodo responsavel por atualizar as tags ou a quantidade de um item
	 * necessario.
	 * 
	 * @param itemNecId  Inteiro que representa um id de um item necessario.
	 * @param quantidade Inteiro que representa a quantidade de itens necessarios.
	 * @param tags       String que representa as tags de um item necessario.
	 *
	 * @throws ItemNaoEncontradoException excecao que podera ser lancada.
	 * 
	 * @return String referente a representacao textual do item necessario
	 *         atualizado.
	 */
	public String atualizaItemNecessario(int itemNecId, int quantidade, String tags) throws ItemNaoEncontradoException {
		if (this.existeItemNecessario(itemNecId)) {
			if (quantidade > 0) {
				this.itensNecessarios.get(itemNecId).setQuantidade(quantidade);
			}
			if (tags != null && !tags.equals("")) {
				this.itensNecessarios.get(itemNecId).setTags(tags);
			}
			return this.itensNecessarios.get(itemNecId).toString();
		} else {
			throw new ItemNaoEncontradoException("Item nao encontrado: " + itemNecId);
		}
	}

	/**
	 * Metodo responsavel por remover um item necessario do mapa de itens
	 * necessarios.
	 * 
	 * @param itemNecId Inteiro que representa um id de um item necessario.
	 */
	public void removeItemNecessario(int itemNecId) {
		this.itensNecessarios.remove(itemNecId);

	}

	/**
	 * Metodo responsavel por verificar se um usuario possui itens cadastrados.
	 * 
	 * @return booleano que confirma se o usuario tem ou nao itens cadastrados.
	 */
	public boolean temItensCadastrados() {
		return this.itensNecessarios.size() != 0;
	}

	/**
	 * Metodo responsavel por retorna os valores do mapa de itens necessarios.
	 * 
	 * @return valores do mapa de itensNecessarios.
	 */
	public Collection<ItemNecessario> getItensNecessarios() {
		return this.itensNecessarios.values();
	}

	/**
	 * Metodo que retorna um item necessario pelo id do mesmo.
	 * 
	 * @param id inteiro que representa o id de um item necessario.
	 * 
	 * @return item necessario.
	 */
	public ItemNecessario getItemNecessario(int id) {
		return this.itensNecessarios.get(id);
	}

}
