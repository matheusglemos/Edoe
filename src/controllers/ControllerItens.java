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
import com.edoe.models.ItemNecessario;

import comparators.DescricaoItemOrdemAlfabetica;
import comparators.ItemOrdemAlfabetica;
import comparators.OrdemIdItemNecessario;
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
	 * Contador responsavel por incrementar o id de um item necessario
	 */
	private int contadorItensNecessarios;
	/**
	 * Lista de itens Necessarios
	 */
	private List<ItemNecessario> listaDeItensNecessarios;

	/**
	 * Construtor do controlador de itens.
	 */
	public ControllerItens(ControllerUsuario controllerUsuario) {
		this.controllerUsuario = controllerUsuario;
		this.descritores = new HashSet<>();
		this.contadorItens = 0;
		this.itens = new ArrayList<>();
		this.contadorItensNecessarios = 0;
		this.listaDeItensNecessarios = new ArrayList<>();
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

	/**
	 * Metodo responsavel por cadastrar novos itens necessários associados a
	 * usuários receptores
	 * 
	 * @param itemNecId     Inteiro que representa o id de um item necessario
	 * @param idReceptor    String que representa o id de um receptor
	 * @param descricaoItem String que representa a descricao de um item necessario
	 * @param quantidade    Inteiro que representa a quantidade de itens necessarios
	 * @param tags          String que representa as tags de um item
	 * @return
	 */
	public int adicionaItemNecessario(String idReceptor, String descricaoItem, int quantidade, String tags) {
		if (descricaoItem == null || descricaoItem.trim().isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
		}
		if (idReceptor == null || idReceptor.trim().isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		if (quantidade <= 0) {
			throw new IllegalArgumentException("Entrada invalida: quantidade deve ser maior que zero.");
		}
		if (!this.controllerUsuario.existeUsuario(idReceptor)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idReceptor + ".");
		}
		ItemNecessario i = new ItemNecessario(++this.contadorItensNecessarios, descricaoItem, quantidade, tags,
				controllerUsuario.getReceptor(idReceptor));
		if (this.controllerUsuario.getReceptor(idReceptor).existeItemNecessario(contadorItensNecessarios)) {
			Collection<ItemNecessario> itens = this.controllerUsuario.getReceptor(idReceptor).getItensNecessarios();
			int id = 0;
			for (ItemNecessario itemNecessario : itens) {
				if (itemNecessario.getDescricaoItem().equals(descricaoItem)) {
					itemNecessario.setQuantidade(quantidade);
					itemNecessario.setTags(tags);
					id = itemNecessario.getItemNecId();
				}
			}
			return id;
		} else {
			controllerUsuario.getReceptor(idReceptor).adicionaUmItemNecessario(i);
			this.listaDeItensNecessarios.add(i);
			return this.contadorItensNecessarios;
		}

	}
	
	/*
	 <15 - livro, tags: [Infantil, Matematica, Didatico], quantidade: 3, Receptor: Murilo Luiz Brito/84473712044 | 2 - toalha de banho, tags: [Adulto, TAM G, Azul], quantidade: 2, Receptor: Sonia Daniela/31862316040 | 3 - toalha de banho, tags: [Adulto, TAM G, Branca], quantidade: 1, Receptor: Sara Jennifer Vieira/24875800037 | 4 - frauda, tags: [Higiene, Infantil, P], quantidade: 15, Receptor: Luiza Elisa Lopes/72859801000118 | 5 - frauda, tags: [Higiene, Infantil, M], quantidade: 10, Receptor: Cristiane Isabella Caldeira/87831113000117 | 6 - frauda, tags: [Higiene, Adulto, GG], quantidade: 30, Receptor: Luiza Elisa Lopes/72859801000118 | 7 - alimento, tags: [Alimentacao, Saude], quantidade: 5, Receptor: Lucca Iago/57091431030 | 8 - sabonete, tags: [Higiene], quantidade: 8, Receptor: Sara Jennifer Vieira/24875800037 | 9 - livro, tags: [], quantidade: 1, Receptor: Sara Jennifer Vieira/24875800037 | 10 - cadeira de rodas, tags: [roda grande, 80kg, conforto], quantidade: 7, Receptor: Luiza Elisa Lopes/72859801000118 | 11 - colchao, tags: [colchao kingsize, conforto], quantidade: 6, Receptor: Murilo Luiz Brito/84473712044 | 12 - jaqueta de couro, tags: [outfit, couro de bode], quantidade: 3, Receptor: Antonella Sonia Moraes/32719454000103 | 13 - travesseiro, tags: [travesseiro de pena, conforto, dormir], quantidade: 2, Receptor: Rafaela Beatriz/51567490000143 | 14 - camiseta, tags: [outfit, poliester, roupa], quantidade: 11, Receptor: Murilo Luiz Brito/84473712044>
	 <15 - Livro, tags: [Infantil, Matematica, Didatico], quantidade: 3,receptor: Murilo Luiz Brito/84473712044 | 14 - camiseta, tags: [outfit, poliester, roupa], quantidade: 11,receptor: Murilo Luiz Brito/84473712044 | 13 - travesseiro, tags: [travesseiro de pena, conforto, dormir], quantidade: 2,receptor: Rafaela Beatriz /51567490000143 | 12 - jaqueta de couro, tags: [outfit, couro de bode], quantidade: 3,receptor: Antonella Sonia Moraes/32719454000103 | 11 - COLCHAO, tags: [colchao kingsize, conforto], quantidade: 6,receptor: Murilo Luiz Brito/84473712044 | 10 - cAdEiRa de RoDaS, tags: [roda grande, 80kg, conforto], quantidade: 7,receptor: Luiza Elisa Lopes/72859801000118 | 9 - Livro, tags: [], quantidade: 1,receptor: Sara Jennifer Vieira/24875800037 | 8 - Sabonete, tags: [Higiene], quantidade: 8,receptor: Sara Jennifer Vieira/24875800037 | 7 - Alimento, tags: [Alimentacao, Saude], quantidade: 5,receptor: Lucca Iago/57091431030 | 6 - Frauda, tags: [Higiene, Adulto, GG], quantidade: 30,receptor: Luiza Elisa Lopes/72859801000118 | 5 - Frauda, tags: [Higiene, Infantil, M], quantidade: 10,receptor: Cristiane Isabella Caldeira/87831113000117 | 4 - Frauda, tags: [Higiene, Infantil, P], quantidade: 15,receptor: Luiza Elisa Lopes/72859801000118 | 3 - Toalha de Banho, tags: [Adulto, TAM G, Branca], quantidade: 1,receptor: Sara Jennifer Vieira/24875800037 | 2 - Toalha de Banho, tags: [Adulto, TAM G, Azul], quantidade: 2,receptor: Sonia Daniela/31862316040 | 1 - Livro, tags: [Infantil, Matematica, Didatico], quantidade: 1,receptor: Murilo Luiz Brito/84473712044>
	 */

	/**
	 * Metodo responsavel por listar todos os itens necessário cadastrados no
	 * eDoe.com ordenados pelo identificador único dos itens
	 * 
	 * @return String contendo uma lista de itens necessarios
	 */
	public String listaItensNecessarios() {
		String resultado = "";
		Collections.sort(this.listaDeItensNecessarios, new OrdemIdItemNecessario());
		for (int i = 0; i < listaDeItensNecessarios.size(); i++) {
			if (i == listaDeItensNecessarios.size() - 1) {
				resultado += listaDeItensNecessarios.get(i).itensNecessariosDescricaoCompleta();
			} else {
				resultado += listaDeItensNecessarios.get(i).itensNecessariosDescricaoCompleta() + " | ";
			}
		}
		return resultado;

	}
	
	/**
	 <15 - livro, tags: [Infantil, Matematica, Didatico], quantidade: 3, Receptor: Murilo Luiz Brito/84473712044 | 2 - toalha de banho, tags: [Adulto, TAM G, Azul], quantidade: 2, Receptor: Sonia Daniela/31862316040 | 3 - toalha de banho, tags: [Adulto, TAM G, Branca], quantidade: 1, Receptor: Sara Jennifer Vieira/24875800037 | 4 - frauda, tags: [Higiene, Infantil, P], quantidade: 15, Receptor: Luiza Elisa Lopes/72859801000118 | 5 - frauda, tags: [Higiene, Infantil, M], quantidade: 10, Receptor: Cristiane Isabella Caldeira/87831113000117 | 6 - frauda, tags: [Higiene, Adulto, GG], quantidade: 30, Receptor: Luiza Elisa Lopes/72859801000118 | 7 - alimento, tags: [Alimentacao, Saude], quantidade: 5, Receptor: Lucca Iago/57091431030 | 8 - sabonete, tags: [Higiene], quantidade: 8, Receptor: Sara Jennifer Vieira/24875800037 | 9 - livro, tags: [], quantidade: 1, Receptor: Sara Jennifer Vieira/24875800037 | 10 - cadeira de rodas, tags: [roda grande, 80kg, conforto], quantidade: 7, Receptor: Luiza Elisa Lopes/72859801000118 | 11 - colchao, tags: [colchao kingsize, conforto], quantidade: 6, Receptor: Murilo Luiz Brito/84473712044 | 12 - jaqueta de couro, tags: [outfit, couro de bode], quantidade: 3, Receptor: Antonella Sonia Moraes/32719454000103 | 13 - travesseiro, tags: [travesseiro de pena, conforto, dormir], quantidade: 2, Receptor: Rafaela Beatriz/51567490000143 | 14 - camiseta, tags: [outfit, poliester, roupa], quantidade: 11, Receptor: Murilo Luiz Brito/84473712044>
	 <15 - Livro, tags: [], quantidade: 3,receptor: Murilo Luiz Brito/84473712044 | 14 - camiseta, tags: [], quantidade: 11,receptor: Murilo Luiz Brito/84473712044 | 13 - travesseiro, tags: [], quantidade: 2,receptor: Rafaela Beatriz /51567490000143 | 12 - jaqueta de couro, tags: [], quantidade: 3,receptor: Antonella Sonia Moraes/32719454000103 | 11 - COLCHAO, tags: [], quantidade: 6,receptor: Murilo Luiz Brito/84473712044 | 10 - cAdEiRa de RoDaS, tags: [], quantidade: 7,receptor: Luiza Elisa Lopes/72859801000118 | 9 - Livro, tags: [], quantidade: 1,receptor: Sara Jennifer Vieira/24875800037 | 8 - Sabonete, tags: [], quantidade: 8,receptor: Sara Jennifer Vieira/24875800037 | 7 - Alimento, tags: [], quantidade: 5,receptor: Lucca Iago/57091431030 | 6 - Frauda, tags: [], quantidade: 30,receptor: Luiza Elisa Lopes/72859801000118 | 5 - Frauda, tags: [], quantidade: 10,receptor: Cristiane Isabella Caldeira/87831113000117 | 4 - Frauda, tags: [], quantidade: 15,receptor: Luiza Elisa Lopes/72859801000118 | 3 - Toalha de Banho, tags: [], quantidade: 1,receptor: Sara Jennifer Vieira/24875800037 | 2 - Toalha de Banho, tags: [], quantidade: 2,receptor: Sonia Daniela/31862316040 | 1 - Livro, tags: [], quantidade: 1,receptor: Murilo Luiz Brito/84473712044>
 
	 
	 */

	/**
	 * Metodo responsavel por atualizar as tags ou a quantidade de um item
	 * necessario
	 * 
	 * @param itemNecId  Inteiro que representa o id de um item necessario
	 * @param idReceptor String que representa o id de um receptor
	 * @param quantidade Inteiro que representa a quantidade de itens necessarios
	 * @param tags       String que representa as tags de um item
	 */
	public void atualizaItemNecessario(int itemNecId, String idReceptor, int quantidade, String tags) {
		if (itemNecId < 0) {
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		}
		if (idReceptor == null || idReceptor.trim().isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		if (!controllerUsuario.existeUsuario(idReceptor)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idReceptor + ".");
		}
		if (!controllerUsuario.getReceptor(idReceptor).existeItemNecessario(itemNecId)) {
			throw new IllegalArgumentException("Item nao encontrado: " + itemNecId + ".");
		}
		if (controllerUsuario.existeUsuario(idReceptor)) {
			this.controllerUsuario.getReceptor(idReceptor).atualizaItemNecessario(itemNecId, quantidade, tags);
		}

	}

	/**
	 * Metodo responsavel por remover um item necessario de um receptor
	 * 
	 * @param idReceptor String que representa o id de um usuario receptor
	 * @param idItem     Inteiro que representa um id de um item necessario
	 */
	public void removeItemNecessario(String idReceptor, int idItem) {
		if (idItem < 0) {
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		}
		if (idReceptor == null || idReceptor.trim().isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		if (!controllerUsuario.existeUsuario(idReceptor)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idReceptor + ".");
		}
		if (!controllerUsuario.getReceptor(idReceptor).temItensCadastrados()) {
			throw new IllegalArgumentException("O Usuario nao possui itens cadastrados.");
		}
		if (!controllerUsuario.getReceptor(idReceptor).existeItemNecessario(idItem)) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}
		this.controllerUsuario.getReceptor(idReceptor).removeItemNecessario(idItem);

	}

}
