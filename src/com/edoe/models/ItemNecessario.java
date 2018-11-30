package com.edoe.models;

import java.util.ArrayList;
import java.util.List;

public class ItemNecessario {

	/**
	 * Atributo que representa o id de um item necessario.
	 */
	private int itemNecId;

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
	 * Atributo que representa um receptor
	 */
	private Receptor receptor;

	/**
	 * 
	 * @param itemNecId
	 * @param descricaoItem
	 * @param quantidade
	 * @param tag
	 * @param receptor
	 */
	public ItemNecessario(int itemNecId, String descricaoItem, int quantidade, String tag, Receptor receptor) {
		this.itemNecId = itemNecId;
		this.tags = new ArrayList<>();
		this.separaTags(tag);
		this.quantidade = quantidade;
		this.descricaoItem = descricaoItem;
		this.tag = tag;
		this.receptor = receptor;
	}

	public List<String> getTags() {
		return tags;
	}

	/**
	 * Metodo que recebe uma tag em String e usa o separaTags para separar cada tag
	 * e depois atualizar pelo set
	 * 
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

	public int getItemNecId() {
		return itemNecId;
	}

	public String getDescricaoItem() {
		return descricaoItem;
	}

	public String getTag() {
		return tag;
	}

	/**
	 * Metodo que recebe uma String com tags e adiciona uma por uma numa lista
	 * 
	 * @param tag
	 */
	private void separaTags(String tag) {
		String[] array = tag.split(",");
		for (int i = 0; i < array.length; i++) {
			this.tags.add(array[i]);
		}
	}

	/**
	 * Metodo que gera o hashcode de um item necessario. Dois itens necessários são
	 * iguais se eles tiverem o mesmo descritor genérico de item e as mesmas tags
	 * (na mesma ordem).
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
	 * Metodo responsavel por comparar um item necessario com outro a partir da sua
	 * descricao e tags
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemNecessario other = (ItemNecessario) obj;
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
	 * Metodo responsavel por retornar a representacao textual de um item
	 * necessario.
	 */
	@Override
	public String toString() {
		return this.getItemNecId() + " - " + this.getDescricaoItem() + ", tags: " + this.getTags() + ", quantidade: "
				+ this.getQuantidade();
	}

	/**
	 * Metodo que retorna uma String contendo a descricao completa de um item
	 * necessario
	 * 
	 * @return String contendo descricao de um item necessario
	 */
	public String itensNecessariosDescricaoCompleta() {
		return this.toString() + ", Receptor: " + receptor.getNome() + "/" + receptor.getId();
	}

}
