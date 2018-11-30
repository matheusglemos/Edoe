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
import comparators.ItemOrdemAlfabetica;
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
		if (!this.descritores.contains(descricaoItem.toLowerCase().trim())) {
			this.descritores.add(descricaoItem.toLowerCase().trim());
		}
		
		Item i = new Item(++this.contadorItens, descricaoItem, tags, quantidade,
				this.controllerUsuario.getDoador(idDoador));
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
			controllerUsuario.getDoador(idDoador).adicionaItemParaDoacao(i);
			this.itens.add(i);
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
		if (!this.controllerUsuario.getDoador(idDoador).temItensParaDoacao()) {
			throw new IllegalArgumentException("O Usuario nao possui itens cadastrados.");
		}
		if (!controllerUsuario.getDoador(idDoador).existeItem(idItem)) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}
		Item aux = this.controllerUsuario.getDoador(idDoador).removeItemParaDoacao(idItem);
		this.itens.remove(aux);
		aux.setQuantidade(0);
	}

	/**
	 * Metodo que permite uma listagem de todos os descritores de itens cadastrados
	 * no sistema, ordenado em ordem alfabética pela descrição do item
	 * 
	 * @return String contendo lista dos descritores em ordem alfabetica
	 */
	public String listaDescritorDeItensParaDoacao() {
		String resultado = "";
		List<String> lista = new ArrayList<>();
		lista.addAll(this.descritores);
		Collections.sort(lista, new DescricaoItemOrdemAlfabetica());

		for (int i = 0; i < lista.size(); i++) {
			boolean entrou = false;
			for (Item item : itens) {	
				if (item.getDescricao().toLowerCase().trim().equals(lista.get(i))) {
					if (i == lista.size() - 1) {
						resultado += item.getQuantidade() + " - " + lista.get(i);
					} else {
						resultado += item.getQuantidade() + " - " + lista.get(i) + " | ";
					}
					entrou = true;
				}
			}
			if (!entrou) {
				resultado += "0 - " + lista.get(i) + " | ";
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

	/*
	 <6 - camiseta, tags: [outfit, algodao], quantidade: 25, doador: Cave Johnson/183.047.152-42 | 7 - cadeira de praia, tags: [dobravel], quantidade: 15, doador: Elizabeth Ashe/705.133.729-11 | 3 - cobertor, tags: [lencol, conforto], quantidade: 15, doador: Aramis Araujo/498.471.033-31 | 4 - travesseiro, tags: [travesseiro de pena], quantidade: 10, doador: Satya Vaswani/592.386.501-11 | 8 - cadeira de alimentacao, tags: [35kg, infantil], quantidade: 5, doador: Cave Johnson/183.047.152-42 | 2 - colchao, tags: [colchao kingsize, conforto, dormir], quantidade: 5, doador: Elizabeth Ashe/705.133.729-11 | 5 - jaqueta de couro, tags: [outfit, couro de cobra], quantidade: 5, doador: Carlos Eduardo/120.949.124-84 | 9 - cadeira reclinavel, tags: [couro], quantidade: 4, doador: Arthur Morgan/526.419.476-13 | 11 - calca jeans, tags: [], quantidade: 3, doador: Arthur Morgan/526.419.476-13 | 1 - cadeira de rodas, tags: [roda grande, cadeira], quantidade: 2, doador: Claudio Campelo/587.910.934-99>
	 <6 - camiseta, tags: [outfit, algodao], quantidade: 25, doador: Cave Johnson/18304715242 | 7 - cadeira de praia, tags: [dobravel], quantidade: 15, doador: Elizabeth Ashe/70513372911 | 3 - cobertor, tags: [lencol, conforto], quantidade: 15, doador: Aramis Araujo/49847103331 | 4 - travesseiro, tags: [travesseiro de pena], quantidade: 10, doador: Satya Vaswani/59238650111 | 8 - cadeira de alimentacao, tags: [35kg, infantil], quantidade: 5, doador: Cave Johnson/18304715242 | 2 - colchao, tags: [colchao kingsize, conforto, dormir], quantidade: 5, doador: Elizabeth Ashe/70513372911 | 5 - jaqueta de couro, tags: [outfit, couro de cobra], quantidade: 5, doador: Carlos Eduardo/12094912484 | 9 - cadeira reclinavel, tags: [couro], quantidade: 4, doador: Arthur Morgan/52641947613 | 11 - calca jeans, tags: [], quantidade: 3, doador: Arthur Morgan/52641947613 | 1 - cadeira de rodas, tags: [roda grande, cadeira], quantidade: 2, doador: Claudio Campelo/58791093499>

	 */
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
			if (item.getDescricao().contains(descricao)) {
				cont++;
			}
		}
		Collections.sort(this.itens, new ItemOrdemAlfabetica());
		for (Item item : this.itens) {
			if (cont > 1 && item.getDescricao().contains(descricao)) {
				resultado += item.toString() + " | ";
				cont--;
			} else if (cont == 1 && item.getDescricao().contains(descricao)) {
				resultado += item.toString();
			}
		}
		return resultado;
	}
}
