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
	private ControllerUsuario controllerUsuario;
	/**
	 * 
	 */
	private Set<String> descritores;
	
	
	public ControllerItens(ControllerUsuario controllerUsuario) {
		this.controllerUsuario = controllerUsuario;
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
			return this.descritores.add(descricao.toLowerCase().trim());
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
		controllerUsuario.getDoador(idDoador).adicionaItemParaDoacao(idItem, descricaoItem, quantidade, tags);
	}
	/**
	 * 
	 * @param idDoador
	 * @param idItem
	 */
	public void exibeItem(String idDoador,int idItem) {
		if(!controllerUsuario.getDoador(idDoador).existeItem(idItem)) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem);
		}
		this.controllerUsuario.getDoador(idDoador).exibeItem(idItem);
	}
	/**
	 * 
	 * @param idItem
	 * @param idDoador
	 * @param quantidade
	 * @param tags
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
		if(!controllerUsuario.existeUsuario(idDoador)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador);
		}
		if(controllerUsuario.existeUsuario(idDoador)) {
			this.controllerUsuario.getDoador(idDoador).atualizaItemParaDoacao(idItem, quantidade, tags);
		}
		
	}
	/**
	 * 
	 * @param idItem
	 * @param idDoador
	 */
	public void removeItemParaDoacao(int idItem, String idDoador) {
		if(!controllerUsuario.getDoador(idDoador).existeItem(idItem)) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem);
		}
		if (idItem < 0) {
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		}
		if(idDoador == null || idDoador.trim().isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		this.controllerUsuario.getDoador(idDoador).removeItemParaDoacao(idItem);
	}
	
	

}
