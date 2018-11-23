package com.edoe.models;

import java.util.ArrayList;
import java.util.List;
/**
 * 

 *
 */
public class Item {

	private int idItem;
	private List<String> tags;
	private int quantidade;
	private String descricao;

	public Item(int idItem, List<String> tags, int quantidade, String descricao) {
		this.idItem = idItem;
		this.tags = new ArrayList<>();
		this.quantidade = quantidade;
		this.descricao = descricao;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
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
		return descricao;
	}

	/**
	 * Dois itens sao iguais se possurem a mesma descricao e as mesmas tags
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.getidItem() + " - " + this.getDescricao() + ", " + this.getTags() + ", quantidade:"
				+ this.getQuantidade();
	}

}
