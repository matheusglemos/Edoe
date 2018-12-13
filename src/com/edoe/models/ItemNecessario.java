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
	 * Atributo que representa um receptor.
	 */
	private Receptor receptor;

	/**
	 * Atributo que representa os pontos de um match.
	 */
	private int pontosMatch;

	/**
	 * Construtor de um item necessario.
	 * 
	 * @param itemNecId
	 *            Inteiro que corresponde a identificacao de um item necessario.
	 * @param descricaoItem
	 *            String referente a descricao de um item.
	 * @param quantidade
	 *            Inteiro que corresponde a quantidade de um item necessario.
	 * @param tag
	 *            String que corresponde a/as tags de um item necessario.
	 * @param receptor
	 *            referente ao receptor que necessita do(s) item(s).
	 */
	public ItemNecessario(int itemNecId, String descricaoItem, int quantidade,
			String tag, Receptor receptor) {
		this.itemNecId = itemNecId;
		this.tags = new ArrayList<>();
		this.separaTags(tag);
		this.quantidade = quantidade;
		this.descricaoItem = descricaoItem;
		this.tag = tag;
		this.receptor = receptor;
		this.pontosMatch = 0;
	}

	public List<String> getTags() {
		return tags;
	}

	/**
	 * Metodo que recebe uma tag em String e usa o separaTags para separar cada
	 * tag e depois atualizar pelo set.
	 * 
	 * @param tag
	 *            String correspondente a/as tags de um item necessario.
	 */
	public void setTags(String tag) {
		separaTags(tag);
	}

	/**
	 * Metodo responsavel por retornar a quantidade do item necessario.
	 * 
	 * @return Inteiro referente a quantidade do item necessario.
	 */
	public int getQuantidade() {
		return quantidade;
	}

	/**
	 * Metodo responsavel por atualizar a quantidade de um item necessario.
	 * 
	 * @param quantidade
	 *            Inteiro correspondente a quantidade do item necessario.
	 */
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * Metodo responsavel por retornar a identificacao de um item necessario.
	 * 
	 * @return Inteiro correspondente ao id do item necessario.
	 */
	public int getItemNecId() {
		return itemNecId;
	}

	/**
	 * Metodo responsavel por retornar a descricao de um item necessario.
	 * 
	 * @return String correspondente a descricao do item necessario.
	 */
	public String getDescricaoItem() {
		return descricaoItem;
	}

	/**
	 * Metodo responsavel por retornar a/as tags do item necessario.
	 * 
	 * @return String correspondente a/as tags do item necessario.
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * Metodo responsavel por retornar os pontos do match.
	 * 
	 * @return Inteiro correspondente aos pontos do match.
	 */
	public int getPontosMatch() {
		return pontosMatch;
	}

	/**
	 * Metodo responsavel por alterar os pontos do match.
	 * 
	 * @param pontosMatch
	 *            Inteiro correspondente aos pontos do match.
	 */
	public void setPontosMatch(int pontosMatch) {
		this.pontosMatch = pontosMatch;
	}

	/**
	 * Metodo que recebe uma String com tags e adiciona uma por uma numa lista.
	 * 
	 * @param tag
	 *            String correspondente a/as tags do item necessario.
	 */
	private void separaTags(String tag) {
		this.tags = new ArrayList<>();
		String[] array = tag.split(",");
		for (int i = 0; i < array.length; i++) {
			this.tags.add(array[i]);
		}
	}

	/**
	 * Metodo que gera o hashcode de um item necessario. Dois itens necessarios
	 * sao iguais se eles tiverem o mesmo descritor generico de item e as mesmas
	 * tags (na mesma ordem).
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricaoItem == null) ? 0 : descricaoItem.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		return result;
	}

	/**
	 * Metodo responsavel por comparar um item necessario com outro a partir da
	 * sua descricao e tags.
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
		return this.getItemNecId() + " - " + this.getDescricaoItem()
				+ ", tags: " + this.getTags() + ", quantidade: "
				+ this.getQuantidade();
	}

	/**
	 * Metodo que retorna uma String contendo a descricao completa de um item
	 * necessario.
	 * 
	 * @return String contendo descricao de um item necessario.
	 */
	public String itensNecessariosDescricaoCompleta() {
		return this.toString() + ", Receptor: " + receptor.getNome() + "/"
				+ receptor.getId();
	}

	/**
	 * Metodo que retorna o nome de um Usuario receptor
	 * 
	 * @return String contendo nome do usuario receptor
	 */
	public String nomeReceptor() {
		return receptor.getNome();
	}

	/**
	 * Metodo que retorna o id de um usuario receptor
	 * 
	 * @return String contendo id do usuario receptor
	 */
	public String idReceptor() {
		return receptor.getId();
	}

}
