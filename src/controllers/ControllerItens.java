package controllers;

import java.util.ArrayList;
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
	 * 
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
		controllerUsuario.getDoador(idDoador).adicionaItemParaDoacao(++this.contadorItens, descricaoItem, quantidade,
				tags);
		return this.contadorItens;
	}

	/**
	 * Metodo responsavel por acessar um item de um doador e exibir a sua
	 * representação textual
	 * 
	 * @param idDoador String que representa o id de um doador
	 * @param idItem   Inteiro que representa o id de um item
	 */
	public void exibeItem(String idDoador, int idItem) {
		if (!controllerUsuario.getDoador(idDoador).existeItem(idItem)) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem);
		}
		this.controllerUsuario.getDoador(idDoador).exibeItem(idItem);
	}

	/**
	 * Metodo responsavel por atualizar as tags ou a quantidade de um item
	 * 
	 * @param idDoador   String que representa o id de um doador
	 * @param idItem     Inteiro que representa o id de um item
	 * @param quantidade Inteiro que representa a quantidade de itens
	 * @param tags       String que representa as tags de um item
	 */
	public void atualizaItemParaDoacao(int idItem, String idDoador, int quantidade, String tags) {
		if (idItem < 0) {
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		}
		if (idDoador == null || idDoador.trim().isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		if (!controllerUsuario.getDoador(idDoador).existeItem(idItem)) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem);
		}
		if (!controllerUsuario.existeUsuario(idDoador)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador);
		}
		if (controllerUsuario.existeUsuario(idDoador)) {
			this.controllerUsuario.getDoador(idDoador).atualizaItemParaDoacao(idItem, quantidade, tags);
		}

	}

	/**
	 * Metodo responsavel por remover um item de um doador
	 * 
	 * @param idDoador String que representa o id de um doador
	 * @param idItem   Inteiro que representa o id de um item
	 */
	public void removeItemParaDoacao(int idItem, String idDoador) {
		if (!controllerUsuario.getDoador(idDoador).existeItem(idItem)) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem);
		}
		if (idItem < 0) {
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		}
		if (idDoador == null || idDoador.trim().isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		this.controllerUsuario.getDoador(idDoador).removeItemParaDoacao(idItem);
	}

	/**
	 * Metodo 1 da parte 3
	 * 
	 * @return
	 */
	public String descricaoOrdemAlfabetica() {
		String resultado = "";
		Collections.sort(this.itens, new DescricaoItemOrdemAlfabetica());
		for (Item item : this.itens) {
			resultado += item.quantidadeDescricao() + " | ";
		}
		return resultado;
	}

	/**
	 * Editar ainda
	 * 
	 * @return
	 */
	public String descricaoOrdemQuantidade() {
		String resultado = "";
		Collections.sort(this.itens, new OrdemQuantidadeDeItens());
		for (Item item : this.itens) {
			resultado += item.quantidadeDoItemNoSistema();
		}
		return resultado;
	}

}
