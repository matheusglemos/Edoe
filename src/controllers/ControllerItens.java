package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.edoe.models.Doador;
import com.edoe.models.Item;

import comparators.DescricaoItemOrdemAlfabetica;
import comparators.OrdemQuantidadeDeItens;

/**
 * Classe que controla os itens de um usuario. Adiciona, pesquisa, atualiza e
 * remove itens.
 * 
 * @author Matheus Gusmao
 * @author Davidson Guedes
 * @author Caroliny Silva
 * @author Almir Crispiniano
 *
 */
public class ControllerItens {
	/**
	 * Atributo responsavel por ligar um Usuario doador a um item
	 */
	private ControllerUsuario controllerUsuario;
	/**
	 * HashSet formada por descritores
	 */
	private Set<String> descritores;
	/**
	 * Contador responsavel por incrementar o id de um item
	 */
	private int contadorItens;
	/**
	 * Lista de itens
	 */
	private List<Item> itens;

	/**
	 * Construtor do controlador de itens.
	 */
	public ControllerItens(ControllerUsuario controllerUsuario) {
		this.controllerUsuario = controllerUsuario;
		this.descritores = new HashSet<>();
		this.contadorItens = 0;
		this.itens = new ArrayList<>();
	}

	/**
	 * Metodo que verifica se ja existe uma descricao no hashset de descritores
	 * 
	 * @param descricao String que representa a descrição de um item
	 * @return booleano
	 */
	public boolean existeDescritor(String descricao) {
		return this.descritores.contains(descricao);
	}

	/**
	 * Metodo que adiciona um descritor no mapa de descritores
	 * 
	 * @param descricao String que representa a descrição de um item
	 * @return booleano
	 */
	public boolean adicionaDescritor(String descricao) {
		if (descricao == null || descricao.trim().isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
		}
		if (!this.existeDescritor(descricao.toLowerCase().trim())) {
			return this.descritores.add(descricao.toLowerCase().trim());
		}
		throw new IllegalArgumentException("Descritor de Item ja existente: " + descricao.toLowerCase().trim() + ".");

	}

	/**
	 * Metodo responsavel por adicionar um item para doacao associado a um usuario
	 * doador
	 * 
	 * @param idDoador      String que representa o id de um doador
	 * @param descricaoItem String que representa a descricao de um item
	 * @param quantidade    Inteiro que representa a quantidade de itens
	 * @param tags          String que representa as tags de um item
	 * @return
	 */
	public int adicionaItemParaDoacao(String idDoador, String descricaoItem, int quantidade, String tags) {
		if (descricaoItem == null || descricaoItem.trim().isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
		}
		if (idDoador == null || idDoador.trim().isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		if (quantidade <= 0) {
			throw new IllegalArgumentException("Entrada invalida: quantidade deve ser maior que zero.");
		}
		if (!this.controllerUsuario.existeUsuario(idDoador)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		}
		if (this.controllerUsuario.getDoador(idDoador).existeItem(descricaoItem)) {
			Collection<Item> itens = this.controllerUsuario.getDoador(idDoador).getItens();
			int id = 0;
			for (Item item : itens) {
				if (item.getDescricao().equals(descricaoItem)) {
					item.setQuantidade(quantidade);
					item.setTags(tags);
					id = item.getidItem();
				}
			}
			return id;
		} else {
			controllerUsuario.getDoador(idDoador).adicionaItemParaDoacao(++this.contadorItens, descricaoItem,
					quantidade, tags);
			return this.contadorItens;
		}

	}

	/**
	 * Metodo responsavel por acessar um item de um doador e exibir a sua
	 * representação textual
	 * 
	 * @param idDoador String que representa o id de um doador
	 * @param idItem   Inteiro que representa o id de um item
	 */
	public String exibeItem(String idDoador, int idItem) {
		if (!controllerUsuario.existeUsuario(idDoador)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		}
		if (!controllerUsuario.getDoador(idDoador).existeItem(idItem)) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}
		return this.controllerUsuario.getDoador(idDoador).exibeItem(idItem);
	}

	/**
	 * Metodo responsavel por atualizar as tags ou a quantidade de um item
	 * 
	 * @param idDoador   String que representa o id de um doador
	 * @param idItem     Inteiro que representa o id de um item
	 * @param quantidade Inteiro que representa a quantidade de itens
	 * @param tags       String que representa as tags de um item
	 */
	public String atualizaItemParaDoacao(int idItem, String idDoador, int quantidade, String tags) {
		if (idItem < 0) {
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		}
		if (idDoador == null || idDoador.trim().isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		if (!controllerUsuario.existeUsuario(idDoador)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		}
		if (!controllerUsuario.getDoador(idDoador).existeItem(idItem)) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}
		if (controllerUsuario.existeUsuario(idDoador)) {
			this.controllerUsuario.getDoador(idDoador).atualizaItemParaDoacao(idItem, quantidade, tags);
		}
		return this.controllerUsuario.getDoador(idDoador).exibeItem(idItem);
	}

	/**
	 * Metodo responsavel por remover um item de um doador
	 * 
	 * @param idDoador String que representa o id de um doador
	 * @param idItem   Inteiro que representa o id de um item
	 */
	public void removeItemParaDoacao(int idItem, String idDoador) {
		if (idItem < 0) {
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		}
		if (idDoador == null || idDoador.trim().isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		if (!controllerUsuario.existeUsuario(idDoador)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		}
		if (!controllerUsuario.getDoador(idDoador).existeItem(idItem)) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}
		this.controllerUsuario.getDoador(idDoador).removeItemParaDoacao(idItem);
	}

	/**
	 * Metodo que permite uma listagem de todos os descritores de itens cadastrados
	 * no sistema, ordenado em ordem alfabética pela descrição do item
	 * 
	 * @return String contendo lista dos descritores em ordem alfabetica
	 */
	public String listaDescritorDeItensParaDoacao() {
		String resultado = "";
		Collections.sort(this.itens, new DescricaoItemOrdemAlfabetica());
		for (int i = 0; i < itens.size(); i++) {
			if (i == itens.size() - 1) {
				resultado += itens.get(i).quantidadeDescricao();
			} else {
				resultado += itens.get(i).quantidadeDescricao() + " | ";
			}
		}
		return resultado;
	}

	/**
	 * Metodo que permite a listagem de todos os itens inseridos no sistema,
	 * ordenada pela quantidade do item no sistema.
	 * 
	 * @return String contendo lista dos itens em ordem pela quantidade do item
	 */
	public String listaItensParaDoacao() {
		String resultado = "";
		Collections.sort(this.itens, new OrdemQuantidadeDeItens());
		for (int i = 0; i < itens.size(); i++) {
			if (i == itens.size() - 1) {
				resultado += itens.get(i).quantidadeDoItemNoSistema();
			} else {
				resultado += itens.get(i).quantidadeDoItemNoSistema() + " | ";
			}
		}
		return resultado;
	}

	/**
	 * Metodo que listaa todos os itens relacionados a uma dada string de pesquisa
	 * 
	 * @return String contendo uma lista com todos os itens de uma dada string de
	 *         pesquisa
	 */

	public String pesquisaItemParaDoacaoPorDescricao(String descricao) {
		if (descricao == null || descricao.trim().isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: texto da pesquisa nao pode ser vazio ou nulo.");
		}
		String resultado = "";
		int cont = 0;
		for (Item item : itens) {
			if (item.getDescricao().equals(descricao)) {
				cont++;
			}
		}
		Collections.sort(this.itens, new DescricaoItemOrdemAlfabetica());
		int add = 0;
		for (Item item : this.itens) {
			if (add < cont && item.getDescricao().equals(descricao)) {
				resultado += item.toString() + "|";
				add++;
			} else if (add == cont && item.getDescricao().equals(descricao)) {
				resultado += item.toString();
				break;
			}
		}
		return resultado;
	}

	public void adicionaItemNecessario(int itemNecId, String idReceptor, String descricaoItem, int quantidade,
			String tags) {

	}
	
	public void listaItensNecessarios() {
		
	}
	
	public void atualizaItemNecessario() {
		
	}
	
	public void removeItemNecessario(String idReceptor, int idItem) {
		
	}

}
