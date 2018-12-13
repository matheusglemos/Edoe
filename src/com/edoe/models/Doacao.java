package com.edoe.models;

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
public class Doacao {

	/**
	 * Atributo em String que representa a data de uam doacao.
	 */
	private Date data;

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
	 * @param data
	 * @param itemDoado
	 * @param itemNecessario
	 * @param totalDoado
	 * @throws ParseException
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

	public Date getData() {
		return data;
	}

	public void setData(String data) throws ParseException {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		this.data = format.parse(data);
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
	 * 
	 * <11/10/2018 - doador: Cave Johnson/18304715242, item: cadeira de rodas,
	 * quantidade: 7, receptor: Luiza Elisa Lopes/72859801000118> <11/10/2018 -
	 * doador: Cave Johnson/18304715242, item: cadeira de rodas, quantidade: 15,
	 * receptor: Luiza Elisa Lopes/72859801000118>
	 */

	/**
	 * Representação textual da classe doacao.
	 */
	@Override
	public String toString() {
		return this.date + " - doador: " + this.getItemDoado().nomeDoador() + "/" + this.getItemDoado().idDoador()
				+ ", item: " + this.getItemDoado().getDescricao() + ", quantidade: " + this.totalDoado + ", receptor: "
				+ this.getItemNecessario().nomeReceptor() + "/" + this.getItemNecessario().idReceptor();
	}

}
