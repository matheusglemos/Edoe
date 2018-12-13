package com.edoe.models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe que ira representar uma doacao entre um usuario doador e um usuario
 * receptor atraves dos seus itens necessarios.
 * 
 * @author Matheus Gusmao
 * @author Davidson Guedes
 * @author Caroliny Silva
 * @author Almir Crispiniano
 */
public class Doacao implements Serializable {

	private static final long serialVersionUID = -5595901637151680170L;

	/**
	 * Atributo em String que representa a data de uma doacao.
	 */
	private Date data;

	/**
	 * Atributo String que representa a data de uma doacao.
	 */
	private String date;

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
	 * @param data           String referente a data da doacao.
	 * @param itemDoado      Item referente ao item doado.
	 * @param itemNecessario Item referente ao item necessario.
	 * @param totalDoado     Inteiro referente ao total de itens doados.
	 * 
	 * @throws ParseException excecao que podera ser lancada.
	 */
	public Doacao(String data, Item itemDoado, ItemNecessario itemNecessario, int totalDoado) throws ParseException {
		if (data == null || data.trim().isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: data nao pode ser vazia ou nula.");
		}

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		this.data = format.parse(data);
		this.itemDoado = itemDoado;
		this.itemNecessario = itemNecessario;
		this.totalDoado = totalDoado;
		this.date = data;
	}

	/**
	 * Metodo responsavel por retornar uma data.
	 * 
	 * @return A data da doacao de um item.
	 */
	public Date getData() {
		return data;
	}

	/**
	 * Metodo que altera uma data.
	 * 
	 * @param data referente a data da doacao de um item.
	 * 
	 * @throws ParseException excecao que podera ser lancada.
	 */
	public void setData(String data) throws ParseException {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		this.data = format.parse(data);
	}

	/**
	 * Metodo que retorna o total de itens doados.
	 * 
	 * @return Inteiro correspondente ao total de itens doados.
	 */
	public int getTotalDoado() {
		return totalDoado;
	}

	/**
	 * Metodo que altera o total de itens doados.
	 * 
	 * @param totalDoado Inteiro correspondente ao total doado.
	 */
	public void setTotalDoado(int totalDoado) {
		this.totalDoado = totalDoado;
	}

	/**
	 * Metodo que retorna um item doado.
	 * 
	 * @return Item que foi doado.
	 */
	public Item getItemDoado() {
		return itemDoado;
	}

	/**
	 * Metodo que retorna um item necessario.
	 * 
	 * @return Item que e necessario.
	 */
	public ItemNecessario getItemNecessario() {
		return itemNecessario;
	}

	/**
	 * Representacao textual da classe doacao.
	 */
	@Override
	public String toString() {
		return this.date + " - doador: " + this.getItemDoado().nomeDoador() + "/" + this.getItemDoado().idDoador()
				+ ", item: " + this.getItemDoado().getDescricao() + ", quantidade: " + this.totalDoado + ", receptor: "
				+ this.getItemNecessario().nomeReceptor() + "/" + this.getItemNecessario().idReceptor();
	}

}
