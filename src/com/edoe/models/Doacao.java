package com.edoe.models;

/**
 * Classe que ira representar uma doacao entre um usuario doador e um usuario
 * receptor atraves dos seus itens necessarios.
 * 
 * @author Matheus Gusmao
 * @author Davidson Guedes
 * @author Caroliny Silva
 * @author Almir Crispiniano
 */
public class Doacao {

	/**
	 * Atributo em String que representa a data de uam doacao.
	 */
	private String data;

	/**
	 * Atributo Item que representa um item a ser doado.
	 */
	private Item itemDoado;

	/**
	 * Atributo item necessario que representa um item necessario
	 */
	private ItemNecessario itemNecessario;

	/**
	 * Atributo que representa o total de itens doados.
	 */
	private int totalDoado;

	/**
	 * Construtor da classe doacao.
	 * 
	 * @param data
	 * @param itemDoado
	 * @param itemNecessario
	 * @param totalDoado
	 */
	public Doacao(String data, Item itemDoado, ItemNecessario itemNecessario,
			int totalDoado) {
		if (data == null || data.trim().isEmpty()) {
			throw new IllegalArgumentException(
					"Entrada invalida: data nao pode ser vazia ou nula.");
		}

		this.data = data;
		this.itemDoado = itemDoado;
		this.itemNecessario = itemNecessario;
		this.totalDoado = totalDoado;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getTotalDoado() {
		return totalDoado;
	}

	public void setTotalDoado(int totalDoado) {
		this.totalDoado = totalDoado;
	}

	public Item getItemDoado() {
		return itemDoado;
	}

	public ItemNecessario getItemNecessario() {
		return itemNecessario;
	}

	/**
	 * Representação textual da classe doacao.
	 */
	@Override
	public String toString() {
		return this.getData() + "- doador:" + this.getItemDoado().nomeDoador()
				+ "/" + this.getItemDoado().idDoador() + ",item:"
				+ this.getItemDoado().getDescricao() + ",quantidade:"
				+ this.getItemDoado().getQuantidade() + ",receptor:"
				+ this.getItemNecessario().nomeReceptor() + "/"
				+ this.getItemNecessario().idReceptor();
	}

}
