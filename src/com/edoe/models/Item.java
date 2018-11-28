package com.edoe.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um Item no sistema. Cada item possui id, tags,
 * quantidade e descricao.
 * 
 * @author Matheus Gusmao
 * @author Davidson Guedes
 * @author Almir Crispiniano
 * @author Caroliny Silva
 *
 */
public class Item {
	/**
	 * Atributo que representa o id do Item.
	 */

	private int idItem;

	/**
	 * Atributo que representa uma lista de Tags.
	 */
	private List<String> tags;

	/**
	 * Atributo que representa a quantidade de itens.
	 */
	private int quantidade;

	/**
	 * Atributo que representa a descricao do item.
	 */
	private String descricaoItem;

	/**
	 * Atributo que representa uma String 'Tag'.
	 */
	private String tag;

	/**
	 * Atributo que representa um Doador
	 */
	private Doador doador;

	/**
	 * Construtor da classe item 
	 * @param idItem
	 * @param tags
	 * @param quantidade
	 * @param descricao
	 */
	public Item(int idItem, String descricaoItem, String tag, int quantidade, Doador doador) {
		this.idItem = idItem;
		this.tags = new ArrayList<>();
		this.quantidade = quantidade;
		this.descricaoItem = descricaoItem;
		this.tag = tag;
		this.doador = doador;
	}

	public List<String> getTags() {
		return tags;
	}
	/**
	 * Metodo que recebe uma tag em String e usa o separaTags para separar cada tag
	 * e depois atualizar pelo set
	 * @param tag
	 */
	public void setTags(String tag) {
		separaTags(tag);
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getidItem() {
		return idItem;
	}

	public String getDescricao() {
		return descricaoItem;
	}

	/**
	 * Metodo que recebe uma String com tags e adiciona uma por uma numa lista
	 * 
	 * @param tag
	 */
	private void separaTags(String tag) {
		String[] array = tag.split(",");
		for (int i = 0; i < array.length; i++) {
			tags.add(array[i]);
		}
	}

	/**
	 * Metodo que gera o hashcode de um Item. Dois itens sao iguais se possurem a
	 * mesma descricao e as mesmas tags
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricaoItem == null) ? 0 : descricaoItem.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		return result;
	}

	/**
	 * Metodo responsavel por comparar um Item com outro a partir da sua descricao e
	 * tags
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (descricaoItem == null) {
			if (other.descricaoItem != null)
				return false;
		} else if (!descricaoItem.equals(other.descricaoItem))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		return true;
	}

	/**
	 * Metodo responsavel por retornar a representacao textual de um Item.
	 */
	@Override
	public String toString() {
		return this.getidItem() + " - " + this.getDescricao() + ", tags:" + this.getTags() + ", quantidade:"
				+ this.getQuantidade();
	}

	/**
	 * Metodo que retorna uma String com uma quantidade associada a descrição de um
	 * item
	 * 
	 * @return String representando um item em quantidade junto a sua descrição
	 */
	public String quantidadeDescricao() {
		return this.getQuantidade() + "-" + this.getDescricao();
	}

	/**
	 * Metodo que retorna uma String contendo o toString() da classe item,
	 * adicionando o doador e o seu id
	 * 
	 * @return String representando o item de um doador
	 */
	public String quantidadeDoItemNoSistema() {
		return toString() + ", doador: " + this.doador.getNome() + "/" + this.doador.getId();
	}

}