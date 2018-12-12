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
	 * Atributo que representa um Doador.
	 */
	private Doador doador;

	/**
	 * Atributo que representa os pontos de um match.
	 *
	 */
	private int pontosMatch;

	/**
	 * Construtor da classe item.
	 * 
	 * @param idItem        Inteiro referente a identificacao do item.
	 * @param tag           String referente as tags do item.
	 * @param quantidade    Inteiro referente a quantidade do item.
	 * @param descricaoItem String referente a descricao do item.
	 * @param doador        Do tipo Doador referente a o doador do item.
	 */
	public Item(int idItem, String descricaoItem, String tag, int quantidade, Doador doador) {
		this.idItem = idItem;
		this.tags = new ArrayList<>();
		separaTags(tag);
		this.quantidade = quantidade;
		this.descricaoItem = descricaoItem;
		this.doador = doador;
		this.pontosMatch = 0;
	}

	public List<String> getTags() {
		return tags;
	}

	/**
	 * Metodo que recebe uma tag em String e usa o separaTags para separar cada tag
	 * e depois atualizar pelo set.
	 * 
	 * @param tag String referente as tags do item.
	 */
	public void setTags(String tag) {
		this.tags = new ArrayList<>();
		separaTags(tag);
	}

	/**
	 * Metodo que retorna a quantidade do item.
	 * 
	 * @return Inteiro referente a quantidade.
	 */
	public int getQuantidade() {
		return quantidade;
	}

	/**
	 * Metodo responsavel por alterar a quantidade do item.
	 * 
	 * @param quantidade Inteiro referente a quantidade do item.
	 */
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * Metodo que retorna a identificacao do item.
	 * 
	 * @return Inteiro id referente a identificacao do item.
	 */
	public int getidItem() {
		return idItem;
	}

	/**
	 * Metodo que retorna a descricao de um item.
	 * 
	 * @return String referente a descricao do item.
	 */
	public String getDescricao() {
		return descricaoItem;
	}

	/**
	 * Metodo que recebe uma String com tags e adiciona uma por uma numa lista.
	 * 
	 * @param tag String referente a/as tags de um item.
	 */
	private void separaTags(String tag) {
		String[] array = tag.split(",");
		for (int i = 0; i < array.length; i++) {
			this.tags.add(array[i]);
		}
	}

	/**
	 * Metodo que gera o hashcode de um Item. Dois itens sao iguais se possurem a
	 * mesma descricao e as mesmas tags.
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
	 * tags.
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
		return this.getidItem() + " - " + this.getDescricao() + ", tags: " + this.getTags() + ", quantidade: "
				+ this.getQuantidade();
	}

	/**
	 * Metodo que retorna uma String com uma quantidade associada a descricao de um
	 * item.
	 * 
	 * @return String representando um item em quantidade junto a sua descricao.
	 */
	public String quantidadeDescricao() {
		return this.getQuantidade() + "-" + this.getDescricao();
	}

	/**
	 * Metodo que retorna uma String contendo o toString() da classe item,
	 * adicionando o doador e o seu id.
	 * 
	 * @return String representando o item de um doador.
	 */
	public String quantidadeDoItemNoSistema() {
		return toString() + ", doador: " + this.doador.getNome() + "/" + this.doador.getId();
	}

	/**
	 * Metodo que incrementa pontos no atributo pontosMatch.
	 * 
	 * @param valor inteiro que adiciona pontos.
	 */
	public void incremetarPontos(int valor) {
		this.pontosMatch += valor;
	}

	/**
	 * Metodo que descrementa pontos no atributo pontosMatch.
	 * 
	 * @param valor inteiro que retira pontos.
	 */
	public void decremetarPontos(int valor) {
		this.pontosMatch -= valor;
	}

	/**
	 * Metodo que retorna a quantidade de pontos do atributo pontosMatch.
	 * 
	 * @return inteiro contendo os pontos de um match.
	 */
	public int getPontos() {
		return this.pontosMatch;
	}


	/**
	 * Metodo que retorna o nome de um Usuario doador
	 * 
	 * @return String contendo nome do usuario doador
	 */
	public String nomeDoador() {
		return doador.getNome();
	}

	/**
	 * Metodo que retorna o id de um usuario doador
	 * 
	 * @return String contendo id do usuario doador
	 */
	public String idDoador() {
		return doador.getId();
	}


}