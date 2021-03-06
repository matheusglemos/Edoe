package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.edoe.models.Item;
import com.edoe.models.ItemNecessario;
import com.edoe.models.Receptor;
import com.edoe.models.Usuario;

import comparators.DescricaoItemOrdemAlfabetica;
import comparators.ItemOrdemAlfabetica;
import comparators.OrdemQuantidadeDeItens;
import comparators.OrdenarPorPontosMatch;
import excecoes.DescitorJaExistenteException;
import excecoes.DescricaoInvalidaException;
import excecoes.IdInvalidoException;
import excecoes.ItemNaoEncontradoException;
import excecoes.QuantidadeInvalidaException;
import excecoes.TextoInvalidoException;
import excecoes.UsuarioDeveSerReceptorException;
import excecoes.UsuarioNaoEncontradoException;
import excecoes.UsuarioNaoPossuiItensCadastradosException;

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
	 * Atributo responsavel por ligar um Usuario doador a um item.
	 */
	private ControllerUsuario controllerUsuario;
	/**
	 * HashSet formada por descritores.
	 */
	private Set<String> descritores;
	/**
	 * Contador responsavel por incrementar o id de um item.
	 */
	private int contadorItens;
	/**
	 * Lista de itens.
	 */
	private List<Item> itens;
	/**
	 * Contador responsavel por incrementar o id de um item necessario.
	 */
	private int contadorItensNecessarios;
	/**
	 * Lista de itens Necessarios.
	 */
	private List<ItemNecessario> listaDeItensNecessarios;

	/**
	 * Construtor do controlador de itens.
	 * 
	 * @param controllerUsuario referente a o controller de usuarios do sistema.
	 */
	public ControllerItens(ControllerUsuario controllerUsuario) {
		this.controllerUsuario = controllerUsuario;
		this.descritores = new HashSet<>();
		this.contadorItens = 0;
		this.itens = new ArrayList<>();
		this.contadorItensNecessarios = 0;
		this.listaDeItensNecessarios = new LinkedList<>();
	}

	/**
	 * Metodo que verifica se ja existe uma descricao no hashset de descritores
	 * 
	 * @param descricao String que representa a descricao de um item.
	 * @return booleano.
	 */
	public boolean existeDescritor(String descricao) {
		return this.descritores.contains(descricao);
	}

	/**
	 * Metodo que adiciona um descritor no mapa de descritores.
	 * 
	 * @param descricao String que representa a descricao de um item.
	 * 
	 * @throws excecoes.DescricaoInvalidaException excecao que podera ser lancada.
	 * @throws DescitorJaExistenteException excecao que podera ser lancada.
	 * 
	 * @return booleano.
	 */
	public boolean adicionaDescritor(String descricao) throws excecoes.DescricaoInvalidaException, DescitorJaExistenteException {
		if (descricao == null || descricao.trim().isEmpty()) {
			throw new excecoes.DescricaoInvalidaException("Entrada invalida: descricao nao pode ser vazia ou nula.");
		}
		if (!this.existeDescritor(descricao.toLowerCase().trim())) {
			return this.descritores.add(descricao.toLowerCase().trim());
		}
		throw new DescitorJaExistenteException("Descritor de Item ja existente: " + descricao.toLowerCase().trim() + ".");

	}

	/**
	 * Metodo responsavel por adicionar um item para doacao associado a um usuario
	 * doador.
	 * 
	 * @param idDoador      String que representa o id de um doador.
	 * @param descricaoItem String que representa a descricao de um item.
	 * @param quantidade    Inteiro que representa a quantidade de itens.
	 * @param tags          String que representa as tags de um item.
	 * 
	 * @throws DescricaoInvalidaException excecao que podera ser lancada.
	 * @throws IdInvalidoException excecao que podera ser lancada.
	 * @throws UsuarioNaoEncontradoException excecao que podera ser lancada.
	 * @throws QuantidadeInvalidaException excecao que podera ser lancada.
	 *
	 * @return id do item adicionado para doacao.
	 */
	public int adicionaItemParaDoacao(String idDoador, String descricaoItem, int quantidade, String tags) throws DescricaoInvalidaException, IdInvalidoException, UsuarioNaoEncontradoException, QuantidadeInvalidaException {
		if (descricaoItem == null || descricaoItem.trim().isEmpty()) {
			throw new DescricaoInvalidaException("Entrada invalida: descricao nao pode ser vazia ou nula.");
		}
		if (idDoador == null || idDoador.trim().isEmpty()) {
			throw new IdInvalidoException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		if (quantidade <= 0) {
			throw new QuantidadeInvalidaException("Entrada invalida: quantidade deve ser maior que zero.");
		}
		if (!this.controllerUsuario.existeUsuario(idDoador)) {
			throw new UsuarioNaoEncontradoException("Usuario nao encontrado: " + idDoador + ".");
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
	 * representacao textual.
	 * 
	 * @param idDoador String que representa o id de um doador.
	 * @param idItem   Inteiro que representa o id de um item.
	 * @throws ItemNaoEncontradoException excecao que podera ser lancada.
	 * @throws UsuarioNaoEncontradoException excecao que podera ser lancada.
	 * 
	 * @return String contendo a exibicao de um item.
	 */
	public String exibeItem(String idDoador, int idItem) throws ItemNaoEncontradoException, UsuarioNaoEncontradoException {
		if (!controllerUsuario.existeUsuario(idDoador)) {
			throw new UsuarioNaoEncontradoException("Usuario nao encontrado: " + idDoador + ".");
		}
		if (!controllerUsuario.getDoador(idDoador).existeItem(idItem)) {
			throw new ItemNaoEncontradoException("Item nao encontrado: " + idItem + ".");
		}
		return this.controllerUsuario.getDoador(idDoador).exibeItem(idItem);
	}

	/**
	 * Metodo responsavel por atualizar as tags ou a quantidade de um item.
	 * 
	 * @param idDoador   String que representa o id de um doador.
	 * @param idItem     Inteiro que representa o id de um item.
	 * @param quantidade Inteiro que representa a quantidade de itens.
	 * @param tags       String que representa as tags de um item.
	 * 
	 * @throws IdInvalidoException excecao que podera ser lancada.
	 * @throws UsuarioNaoEncontradoException excecao que podera ser lancada.
	 * @throws ItemNaoEncontradoException excecao que podera ser lancada.
	 * 
	 * @return String correspondente a o item atualizado.
	 */
	public String atualizaItemParaDoacao(int idItem, String idDoador, int quantidade, String tags) throws IdInvalidoException, UsuarioNaoEncontradoException, ItemNaoEncontradoException {
		if (idItem < 0) {
			throw new IdInvalidoException("Entrada invalida: id do item nao pode ser negativo.");
		}
		if (idDoador == null || idDoador.trim().isEmpty()) {
			throw new IdInvalidoException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		if (!controllerUsuario.existeUsuario(idDoador)) {
			throw new UsuarioNaoEncontradoException("Usuario nao encontrado: " + idDoador + ".");
		}
		if (!controllerUsuario.getDoador(idDoador).existeItem(idItem)) {
			throw new ItemNaoEncontradoException("Item nao encontrado: " + idItem + ".");
		}
		if (controllerUsuario.existeUsuario(idDoador)) {
			this.controllerUsuario.getDoador(idDoador).atualizaItemParaDoacao(idItem, quantidade, tags);
		}
		return this.controllerUsuario.getDoador(idDoador).exibeItem(idItem);
	}

	/**
	 * Metodo responsavel por remover um item de um doador.
	 * 
	 * @param idDoador String que representa o id de um doador.
	 * @param idItem   Inteiro que representa o id de um item.
	 *
	 * @throws IdInvalidoException excecao que podera ser lancada.
	 * @throws UsuarioNaoEncontradoException excecao que podera ser lancada.
	 * @throws ItemNaoEncontradoException excecao que podera ser lancada.
	 * @throws UsuarioNaoPossuiItensCadastradosException excecao que podera ser lancada.
	 */
	public void removeItemParaDoacao(int idItem, String idDoador) throws IdInvalidoException, UsuarioNaoEncontradoException, ItemNaoEncontradoException, UsuarioNaoPossuiItensCadastradosException {
		if (idItem < 0) {
			throw new IdInvalidoException("Entrada invalida: id do item nao pode ser negativo.");
		}
		if (idDoador == null || idDoador.trim().isEmpty()) {
			throw new IdInvalidoException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		if (!controllerUsuario.existeUsuario(idDoador)) {
			throw new UsuarioNaoEncontradoException("Usuario nao encontrado: " + idDoador + ".");
		}
		if (!this.controllerUsuario.getDoador(idDoador).temItensParaDoacao()) {
			throw new UsuarioNaoPossuiItensCadastradosException("O Usuario nao possui itens cadastrados.");
		}
		if (!controllerUsuario.getDoador(idDoador).existeItem(idItem)) {
			throw new ItemNaoEncontradoException("Item nao encontrado: " + idItem + ".");
		}
		Item aux = this.controllerUsuario.getDoador(idDoador).removeItemParaDoacao(idItem);
		this.itens.remove(aux);
		aux.setQuantidade(0);
	}

	/**
	 * Metodo que permite uma listagem de todos os descritores de itens cadastrados
	 * no sistema, ordenado em ordem alfabetica pela descricao do item.
	 * 
	 * @return String contendo lista dos descritores em ordem alfabetica.
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
		List<Item> lista = new ArrayList<>();
		lista.addAll(this.itens);
		Collections.sort(lista, new OrdemQuantidadeDeItens());
		for (int i = 0; i < lista.size(); i++) {
			if (i == lista.size() - 1) {
				resultado += lista.get(i).quantidadeDoItemNoSistema();
			} else {
				resultado += lista.get(i).quantidadeDoItemNoSistema() + " | ";
			}
		}
		return resultado;
	}

	/**
	 * Metodo que lista todos os itens relacionados a uma dada string de pesquisa.
	 * 
	 * @param descricao String referente a descricao do item.
	 *
	 * @throws TextoInvalidoException excecao que podera ser lancada.
	 * 
	 * @return String contendo uma lista com todos os itens de uma dada string de
	 *         pesquisa.
	 */

	public String pesquisaItemParaDoacaoPorDescricao(String descricao) throws TextoInvalidoException {
		if (descricao == null || descricao.trim().isEmpty()) {
			throw new TextoInvalidoException("Entrada invalida: texto da pesquisa nao pode ser vazio ou nulo.");
		}
		String resultado = "";
		int cont = 0;
		List<Item> lista = new ArrayList<>();
		lista.addAll(this.itens);
		for (Item item : itens) {
			if (item.getDescricao().contains(descricao)) {
				cont++;
			}
		}
		Collections.sort(lista, new ItemOrdemAlfabetica());
		for (Item item : lista) {
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
	 * Metodo responsavel por cadastrar novos itens necessarios associados a
	 * usuarios receptores.
	 * 
	 * @param idReceptor    String que representa o id de um receptor.
	 * @param descricaoItem String que representa a descricao de um item necessario.
	 * @param quantidade    Inteiro que representa a quantidade de itens
	 *                      necessarios.
	 * @param tags          String que representa as tags de um item.
	 * 
	 * @throws DescricaoInvalidaException excecao que podera ser lancada.
	 * @throws IdInvalidoException excecao que podera ser lancada.
	 * @throws QuantidadeInvalidaException excecao que podera ser lancada.
	 * @throws UsuarioNaoEncontradoException excecao que podera ser lancada.
	 * @throws UsuarioDeveSerReceptorException excecao que devera ser lancada.
	 * 
	 * @return id do item necessitado adicionado.
	 */
	public int adicionaItemNecessario(String idReceptor, String descricaoItem, int quantidade, String tags) throws DescricaoInvalidaException, IdInvalidoException, QuantidadeInvalidaException, UsuarioNaoEncontradoException, UsuarioDeveSerReceptorException {
		if (descricaoItem == null || descricaoItem.trim().isEmpty()) {
			throw new DescricaoInvalidaException("Entrada invalida: descricao nao pode ser vazia ou nula.");
		}
		if (idReceptor == null || idReceptor.trim().isEmpty()) {
			throw new IdInvalidoException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		if (quantidade <= 0) {
			throw new QuantidadeInvalidaException("Entrada invalida: quantidade deve ser maior que zero.");
		}
		if (!this.controllerUsuario.existeUsuario(idReceptor)) {
			throw new UsuarioNaoEncontradoException("Usuario nao encontrado: " + idReceptor + ".");
		}
		if (!this.descritores.contains(descricaoItem.toLowerCase().trim())) {
			this.descritores.add(descricaoItem.toLowerCase().trim());
		}
		ItemNecessario i = new ItemNecessario(++this.contadorItensNecessarios, descricaoItem.toLowerCase().trim(),
				quantidade, tags, controllerUsuario.getReceptor(idReceptor));
		if (this.controllerUsuario.getReceptor(idReceptor).existeItemNecessario(i)) {
			Collection<ItemNecessario> itens = this.controllerUsuario.getReceptor(idReceptor).getItensNecessarios();
			int id = 0;
			for (ItemNecessario itemNecessario : itens) {
				if (itemNecessario.getDescricaoItem().equals(descricaoItem.toLowerCase())) {
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

	/**
	 * Metodo responsavel por listar todos os itens necessario cadastrados no
	 * eDoe.com ordenados pelo identificador unico dos itens.
	 * 
	 * @return String contendo uma lista de itens necessarios.
	 */
	public String listaItensNecessarios() {
		String resultado = "";
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
	 * Metodo responsavel por atualizar as tags ou a quantidade de um item
	 * necessario.
	 * 
	 * @param itemNecId  Inteiro que representa o id de um item necessario.
	 * @param idReceptor String que representa o id de um receptor.
	 * @param quantidade Inteiro que representa a quantidade de itens necessarios.
	 * @param tags       String que representa as tags de um item.
	 * 
	 * @throws ItemNaoEncontradoException excecao que podera ser lancada.
	 * @throws IdInvalidoException excecao que podera ser lancada.
	 * @throws UsuarioNaoEncontradoException excecao que podera ser lancada.
	 * @throws UsuarioDeveSerReceptorException excecao que podera ser lancada.
	 * 
	 * @return String vazia.
	 */
	public String atualizaItemNecessario(int itemNecId, String idReceptor, int quantidade, String tags) throws ItemNaoEncontradoException, IdInvalidoException, UsuarioNaoEncontradoException, UsuarioDeveSerReceptorException {
		if (itemNecId < 0) {
			throw new IdInvalidoException("Entrada invalida: id do item nao pode ser negativo.");
		}
		if (idReceptor == null || idReceptor.trim().isEmpty()) {
			throw new IdInvalidoException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		if (!controllerUsuario.existeUsuario(idReceptor)) {
			throw new UsuarioNaoEncontradoException("Usuario nao encontrado: " + idReceptor + ".");
		}
		if (!controllerUsuario.getReceptor(idReceptor).existeItemNecessario(itemNecId)) {
			throw new ItemNaoEncontradoException("Item nao encontrado: " + itemNecId + ".");
		}
		if (controllerUsuario.existeUsuario(idReceptor)) {
			return this.controllerUsuario.getReceptor(idReceptor).atualizaItemNecessario(itemNecId, quantidade, tags);
		}
		return "";

	}

	/**
	 * Metodo responsavel por remover um item necessario de um receptor.
	 * 
	 * @param idReceptor String que representa o id de um usuario receptor.
	 * @param idItem     Inteiro que representa um id de um item necessario.
	 * 
	 * @throws IdInvalidoException excecao que podera ser lancada.
	 * @throws UsuarioNaoEncontradoException excecao que podera ser lancada.
	 * @throws ItemNaoEncontradoException excecao que podera ser lancada.
	 * @throws UsuarioNaoPossuiItensCadastradosException excecao que podera ser lancada.
	 * @throws UsuarioDeveSerReceptorException excecao que podera ser lancada.
	 */
	public void removeItemNecessario(String idReceptor, int idItem) throws IdInvalidoException, UsuarioNaoEncontradoException, ItemNaoEncontradoException, UsuarioNaoPossuiItensCadastradosException, UsuarioDeveSerReceptorException {
		ItemNecessario resultado;
		if (idItem < 0) {
			throw new IdInvalidoException("Entrada invalida: id do item nao pode ser negativo.");
		}
		if (idReceptor == null || idReceptor.trim().isEmpty()) {
			throw new IdInvalidoException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		if (!controllerUsuario.existeUsuario(idReceptor)) {
			throw new UsuarioNaoEncontradoException("Usuario nao encontrado: " + idReceptor + ".");
		}
		if (!controllerUsuario.getReceptor(idReceptor).temItensCadastrados()) {
			throw new UsuarioNaoPossuiItensCadastradosException("O Usuario nao possui itens cadastrados.");
		}
		if (!controllerUsuario.getReceptor(idReceptor).existeItemNecessario(idItem)) {
			throw new ItemNaoEncontradoException("Item nao encontrado: " + idItem + ".");
		}
		resultado = this.controllerUsuario.getReceptor(idReceptor).getItemNecessario(idItem);
		this.controllerUsuario.getReceptor(idReceptor).removeItemNecessario(idItem);
		this.listaDeItensNecessarios.remove(resultado);

	}

	/**
	 * Metodo responsavel por pecorrer um ArrayList de itens e comparar se a
	 * descricao desse item e igual a do item necessario, se for incrementa 20
	 * pontos ao atributo pontosMatch, depois ele compara as tags fazendo uso do
	 * metodo privado comparaTagsESoma e por fim adiciona os itens que deram match
	 * no Array de itensMatch.
	 * 
	 * @param itemN que representa um Item Necessario.
	 * 
	 * @return um ArrayList de matchs.
	 */
	private List<Item> pecorreItensNecessariosReceptor(ItemNecessario itemN) {
		List<Item> itensMatch = new ArrayList<>();
		for (Item item : this.itens) {
			if (item.getDescricao().equals(itemN.getDescricaoItem())) {
				item.incremetarPontos(20);
				this.comparaTagsESoma(item, itemN.getTag());
				itensMatch.add(item);
			}
		}
		return itensMatch;
	}

	/**
	 * Metodo responsavel por comparar tags, se elas forem iguais na mesma posicao
	 * somam 10 pontos, mas se forem iguais em posicoes diferentes somam 5 pontos.
	 * 
	 * @param item que representa um Item.
	 * @param tags que representam uma tag em String.
	 */
	private void comparaTagsESoma(Item item, String tags) {
		List<String> tags1 = Arrays.asList(tags.split(","));
		int i = 0;
		while (i < item.getTags().size() && i < tags1.size()) {
			if (item.getTags().get(i).equals(tags1.get(i))) {
				item.incremetarPontos(10);
			} else if (item.getTags().contains(tags1.get(i))) {
				item.incremetarPontos(5);
			}
			i++;
		}
	}

	/**
	 * Metodo que encontra casamentos (matches) entre itens a serem doados e itens
	 * necessarios. A partir de um Receptor e de um Item necessario o metodo cria
	 * uma lista que usa o pecorreItensNecessariosReceptor e depois ordena a mesma
	 * pelo pontos de Match.
	 * 
	 * @param idReceptor       String que representa o id de um usuario receptor.
	 * @param idItemNecessario Inteiro que representa o id de um item necessario.
	 * 
	 * @throws IdInvalidoException excecao que podera ser lancada.
	 * @throws UsuarioNaoEncontradoException excecao que podera ser lancada.
	 * @throws ItemNaoEncontradoException excecao que podera ser lancada.
	 * @throws UsuarioDeveSerReceptorException excecao que podera ser lancada.
	 * 
	 * @return String contendo uma lista de matches.
	 */
	public String match(String idReceptor, int idItemNecessario) throws IdInvalidoException, UsuarioNaoEncontradoException, ItemNaoEncontradoException, UsuarioDeveSerReceptorException {
		if (idReceptor == null || idReceptor.trim().isEmpty()) {
			throw new IdInvalidoException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		if (!(controllerUsuario.existeUsuario(idReceptor))) {
			throw new UsuarioNaoEncontradoException("Usuario nao encontrado: " + idReceptor + ".");
		}
		if (idItemNecessario < 0) {
			throw new IdInvalidoException("Entrada invalida: id do item nao pode ser negativo.");
		}
		if (!controllerUsuario.getReceptor(idReceptor).existeItemNecessario(idItemNecessario)) {
			throw new ItemNaoEncontradoException("Item nao encontrado: " + idItemNecessario + ".");
		}
		Receptor receptor = this.controllerUsuario.getReceptor(idReceptor);
		ItemNecessario itemN = receptor.getItemNecessario(idItemNecessario);

		List<Item> lista = pecorreItensNecessariosReceptor(itemN);
		Collections.sort(lista, new OrdenarPorPontosMatch());
		String saida = "";
		for (int i = 0; i < lista.size(); i++) {
			if (i == lista.size() - 1) {
				saida += lista.get(i).quantidadeDoItemNoSistema();
			} else {
				saida += lista.get(i).quantidadeDoItemNoSistema() + " | ";
			}
		}
		return saida;
	}

	/**
	 * Metodo responsavel por retornar um item a partir do id do item.
	 * 
	 * @param idItem Inteiro que representa o id de um item.
	 * @return Um item.
	 */
	public Item getItem(int idItem) {
		return (Item) this.itens.get(idItem);
	}

	/**
	 * Metodo responsavel por retornar um item necessario a partir do id do item.
	 * 
	 * @param itemNecId Inteiro que representa o id de um item necessario.
	 * 
	 * @return Um item necessario.
	 */
	public ItemNecessario getItemNecessario(int itemNecId) {
		return (ItemNecessario) this.listaDeItensNecessarios.get(itemNecId);
	}

	/**
	 * Metodo que pesquisa um item doado.
	 * 
	 * @param idItemDoado Item que foi doado.
	 * 
	 * @throws ItemNaoEncontradoException excecao que podera ser lancada.
	 * 
	 * @return O item procurado.
	 */
	public Item pesquisaItem(int idItemDoado) throws ItemNaoEncontradoException {
		for (Item i : this.itens) {
			if (i.getidItem() == idItemDoado) {
				return i;
			}
		}
		throw new ItemNaoEncontradoException("Item nao encontrado: " + idItemDoado + ".");
	}

	/**
	 * Metodo que pesquisa um item necessario.
	 * 
	 * @param idItemNec Item necessario que sera pesquisado.
	 * 
	 * @throws ItemNaoEncontradoException excecao que podera ser lancada.
	 * 
	 * @return O item necessario procurado.
	 */
	public ItemNecessario pesquisaItemNecessario(int idItemNec) throws ItemNaoEncontradoException {
		for (ItemNecessario i : this.listaDeItensNecessarios) {
			if (i.getItemNecId() == idItemNec) {
				return i;
			}
		}
		throw new ItemNaoEncontradoException("Item nao encontrado: " + idItemNec + ".");
	}

	/**
	 * Metodo responsavel por salvar os dados em um arquivo.
	 * 
	 * @throws IOException excecao que podera ser lancada.
	 */
	public void salvar() throws IOException {
		File file = new File("persistencia/itens.csv");
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream obj = new ObjectOutputStream(fos);
		try {
			obj.writeObject(this.descritores);
			obj.writeObject(this.itens);
			obj.writeObject(this.contadorItens);
			obj.writeObject(this.listaDeItensNecessarios);
			obj.writeObject(this.contadorItensNecessarios);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Arquivo nao existe no sistema.");
		} catch (IOException e) {
			System.out.println("Algum erro ocorreu...");
		}
	}

	/**
	 * Metodo responsavel por carregar os dados salvos em um arquivo.
	 */
	public void carregarDados() {
		ObjectInputStream os;
		try {
			os = new ObjectInputStream(new FileInputStream("persistencia/itens.csv"));
			this.descritores = (Set<String>) os.readObject();
			this.itens = (List<Item>) os.readObject();
			this.contadorItens = (int) os.readObject();
			this.listaDeItensNecessarios = (List<ItemNecessario>) os.readObject();
			this.contadorItensNecessarios = (int) os.readObject();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Arquivo nao existe no sistema.");
		} catch (IOException e) {
			System.out.println("Algum erro ocorre...");
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Alguma coisa no sistema mudou");
		}
	}

}
