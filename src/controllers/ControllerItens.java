package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.edoe.models.Doador;
import com.edoe.models.Item;

public class ControllerItens {
	/**
	 * 
	 */
	private Map<String, Doador> doadores;
	/**
	 * 
	 */
	private Set<String> descritores;
	
	
	public ControllerItens() {
		this.doadores = new HashMap<>();
		this.descritores = new HashSet<>();
	}
	
	/**
	 *
	 * @param descricao
	 * @return
	 */
	public boolean existeDescritor(String descricao) {
		return this.descritores.contains(descricao);
	}

	/**
	 * 
	 * @param descricao
	 * @return
	 */
	public boolean adicionaDescritor(String descricao) {
		if (!this.existeDescritor(descricao)) {
			return this.descritores.add(descricao.toLowerCase());
		}
		if(descricao == null || descricao.trim().isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
		}
		throw new IllegalArgumentException("Descritor de Item ja existente: " + descricao);

	}
	/**
	 * 
	 * @param idItem
	 * @param idDoador
	 * @param descricaoItem
	 * @param quantidade
	 * @param tags
	 */
	public void adicionaItemParaDoacao(int idItem, String idDoador, String descricaoItem, int quantidade, String tags) {
		if (descricaoItem == null || descricaoItem.trim().isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
		}
		if(idDoador == null || idDoador.trim().isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		if(quantidade < 0) {
			throw new IllegalArgumentException("Entrada invalida: quantidade deve ser maior que zero.");
		}
		this.doadores.get(idDoador).adicionaItemParaDoacao(idItem, descricaoItem, quantidade, tags);
	}
	/**
	 * 
	 * @param idDoador
	 * @param idItem
	 */
	public void exibeItem(String idDoador,int idItem) {
		if(!doadores.get(idDoador).existeItem(idItem)) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem);
		}
		this.doadores.get(idDoador).exibeItem(idItem);
	}
	/**
	 * 
	 * @param idItem
	 * @param idDoador
	 * @param quantidade
	 * @param tags
	 */
	public void atualizaItemParaDoacao(int idItem, String idDoador, int quantidade, String tags) {
		
	}
	/**
	 * 
	 * @param idItem
	 * @param idDoador
	 */
	public void removeItemParaDoacao(int idItem, String idDoador) {
		if(!doadores.get(idDoador).existeItem(idItem)) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem);
		}
		if (idItem < 0) {
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		}
		if(idDoador == null || idDoador.trim().isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		this.doadores.get(idDoador).removeItemParaDoacao(idItem);
	}
	
	

}
