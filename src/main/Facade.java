package main;

import java.io.IOException;

import controllers.ControllerItens;
import controllers.ControllerUsuario;
import easyaccept.EasyAccept;

/**
 * Classe que ira se comunicar com os controllers. Facilitando assim a
 * comunicacao com a interface grafica.
 * 
 * @author Matheus Gusmao
 * @author Davidson Guedes
 * @author Caroliny Silva
 * @author Almir Crispiniano
 */
public class Facade {

	/*
	 * 15 - livro, tags: [Infantil, Matematica, Didatico], quantidade: 3, Receptor:
	 * Murilo Luiz Brito/84473712044 | 2 - toalha de banho, tags: [Adulto, TAM G,
	 * Azul], quantidade: 2, Receptor: Sonia Daniela/31862316040 | 3 - toalha de
	 * banho, tags: [Adulto, TAM G, Branca], quantidade: 1, Receptor: Sara Jennifer
	 * Vieira/24875800037 | 4 - frauda, tags: [Higiene, Infantil, P], quantidade:
	 * 15, Receptor: Luiza Elisa Lopes/72859801000118 | 5 - frauda, tags: [Higiene,
	 * Infantil, M], quantidade: 10, Receptor: Cristiane Isabella
	 * Caldeira/87831113000117 | 6 - frauda, tags: [Higiene, Adulto, GG],
	 * quantidade: 30, Receptor: Luiza Elisa Lopes/72859801000118 | 7 - alimento,
	 * tags: [Alimentacao, Saude], quantidade: 5, Receptor: Lucca Iago/57091431030 |
	 * 8 - sabonete, tags: [Higiene], quantidade: 8, Receptor: Sara Jennifer
	 * Vieira/24875800037 | 9 - livro, tags: [], quantidade: 1, Receptor: Sara
	 * Jennifer Vieira/24875800037 | 10 - cadeira de rodas, tags: [roda grande,
	 * 80kg, conforto], quantidade: 7, Receptor: Luiza Elisa Lopes/72859801000118 |
	 * 11 - colchao, tags: [colchao kingsize, conforto], quantidade: 6, Receptor:
	 * Murilo Luiz Brito/84473712044 | 12 - jaqueta de couro, tags: [outfit, couro
	 * de bode], quantidade: 3, Receptor: Antonella Sonia Moraes/32719454000103 | 13
	 * - travesseiro, tags: [travesseiro de pena, conforto, dormir], quantidade: 2,
	 * Receptor: Rafaela Beatriz/51567490000143 | 14 - camiseta, tags: [outfit,
	 * poliester, roupa], quantidade: 11, Receptor: Murilo Luiz Brito/84473712044>
	 * <15 - Livro, tags: [Infantil, Matematica, Didatico], quantidade: 3, Receptor:
	 * Murilo Luiz Brito/84473712044 | 14 - camiseta, tags: [outfit, poliester,
	 * roupa], quantidade: 11, Receptor: Murilo Luiz Brito/84473712044 | 13 -
	 * travesseiro, tags: [travesseiro de pena, conforto, dormir], quantidade: 2,
	 * Receptor: Rafaela Beatriz /51567490000143 | 12 - jaqueta de couro, tags:
	 * [outfit, couro de bode], quantidade: 3, Receptor: Antonella Sonia
	 * Moraes/32719454000103 | 11 - COLCHAO, tags: [colchao kingsize, conforto],
	 * quantidade: 6, Receptor: Murilo Luiz Brito/84473712044 | 10 - cAdEiRa de
	 * RoDaS, tags: [roda grande, 80kg, conforto], quantidade: 7, Receptor: Luiza
	 * Elisa Lopes/72859801000118 | 9 - Livro, tags: [], quantidade: 1, Receptor:
	 * Sara Jennifer Vieira/24875800037 | 8 - Sabonete, tags: [Higiene], quantidade:
	 * 8, Receptor: Sara Jennifer Vieira/24875800037 | 7 - Alimento, tags:
	 * [Alimentacao, Saude], quantidade: 5, Receptor: Lucca Iago/57091431030 | 6 -
	 * Frauda, tags: [Higiene, Adulto, GG], quantidade: 30, Receptor: Luiza Elisa
	 * Lopes/72859801000118 | 5 - Frauda, tags: [Higiene, Infantil, M], quantidade:
	 * 10, Receptor: Cristiane Isabella Caldeira/87831113000117 | 4 - Frauda, tags:
	 * [Higiene, Infantil, P], quantidade: 15, Receptor: Luiza Elisa
	 * Lopes/72859801000118 | 3 - Toalha de Banho, tags: [Adulto, TAM G, Branca],
	 * quantidade: 1, Receptor: Sara Jennifer Vieira/24875800037 | 2 - Toalha de
	 * Banho, tags: [Adulto, TAM G, Azul], quantidade: 2, Receptor: Sonia
	 * Daniela/31862316040 | 1 - Livro, tags: [Infantil, Matematica, Didatico],
	 * quantidade: 1, Receptor: Murilo Luiz Brito/84473712044>
	 * 
	 */

	/**
	 * Atributo referente ao controle de usuarios.
	 */
	private ControllerUsuario controleUm;
	private ControllerItens controleDois;

	public Facade() {
		controleUm = new ControllerUsuario();
		controleDois = new ControllerItens(controleUm);
	}

	public static void main(String[] args) {
		args = new String[] { "main.Facade", "acceptance_tests/use_case_1.txt", "acceptance_tests/use_case_2.txt",
				"acceptance_tests/use_case_3.txt", "acceptance_tests/use_case_4.txt",
				"acceptance_tests/use_case_5.txt" };
		EasyAccept.main(args);
	}

	public void lerReceptores(String caminho) throws IOException {
		this.controleUm.lerReceptores(caminho);
	}

	/**
	 * Metodo que adiciona um usuario doador no sistema.
	 * 
	 * @param nome    String que representa o nome do usuario doador.
	 * 
	 * @param id      String que representa o id do usuario doador.
	 * 
	 * @param email   String que representa o email do usuario doador.
	 * 
	 * @param celular String que representa o celular do usuario doador.
	 * 
	 * @param classe  String que representa a classe do usuario doador.
	 */
	public String adicionaDoador(String nome, String id, String email, String celular, String classe) {
		return this.controleUm.adicionaDoador(nome, id, email, celular, classe);
	}

	/**
	 * Metodo que pesquisa determinado usuario atraves de seu id.
	 * 
	 * @param id String que representa o id do usuario.
	 * 
	 * @return A representacao textual do usuario procurado, se estiver presente no
	 *         sistema.
	 */
	public String pesquisaUsuarioPorId(String id) {
		return controleUm.pesquisaUsuarioPorId(id);
	}

	/**
	 * Metodo que pesquisa determinado usuario atraves do seu nome.
	 * 
	 * @param nome String que representa o nome do usuario.
	 * 
	 * @return A representacao textual do usuario procurado, se estiver presente no
	 *         sistema.
	 */
	public String pesquisaUsuarioPorNome(String nome) {
		return controleUm.pesquisaUsuarioPorNome(nome);
	}

	/**
	 * Metodo que atualiza um usuario atraves de seu id.
	 * 
	 * @param id String que representa o id do usuario.
	 * 
	 * @return
	 */
	public String atualizaUsuario(String id, String nome, String email, String celular) {
		return controleUm.atualizaUsuario(id, nome, email, celular);
	}

	/**
	 * Metodo responsavel por remover um usuario do sistema.
	 * 
	 * @param id String que representa o id do usuario.
	 */
	public void removeUsuario(String id) {
		controleUm.removeUsuario(id);
	}

	/**
	 * Metodo que adiciona uma descricao no mapa de descritores
	 * 
	 * @param descricao String que representa a descrição de um item
	 * @return booleano
	 */
	public void adicionaDescritor(String descricao) {
		controleDois.adicionaDescritor(descricao);
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
		return controleDois.adicionaItemParaDoacao(idDoador, descricaoItem, quantidade, tags);
	}

	/**
	 * Metodo responsavel por acessar um item de um doador e exibir a sua
	 * representacao textual
	 * 
	 * @param idDoador String que representa o id de um doador
	 * @param idItem   Inteiro que representa o id de um item
	 */
	public String exibeItem(int idItem, String idDoador) {
		return controleDois.exibeItem(idDoador, idItem);
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
		return controleDois.atualizaItemParaDoacao(idItem, idDoador, quantidade, tags);
	}

	/**
	 * Metodo responsavel por remover um item de um doador
	 * 
	 * @param idDoador String que representa o id de um doador
	 * @param idItem   Inteiro que representa o id de um item
	 */
	public void removeItemParaDoacao(int idItem, String idDoador) {
		controleDois.removeItemParaDoacao(idItem, idDoador);
	}

	/**
	 * Metodo que permite uma listagem de todos os descritores de itens cadastrados
	 * no sistema, ordenado em ordem alfabetica pela descricao do item
	 * 
	 * @return String contendo lista dos descritores em ordem alfabetica
	 */
	public String listaDescritorDeItensParaDoacao() {
		return controleDois.listaDescritorDeItensParaDoacao();
	}

	/**
	 * Metodo que permite a listagem de todos os itens inseridos no sistema,
	 * ordenada pela quantidade do item no sistema.
	 * 
	 * @return String contendo lista dos itens em ordem pela quantidade do item
	 */
	public String listaItensParaDoacao() {
		return controleDois.listaItensParaDoacao();
	}

	/**
	 * Metodo que listaa todos os itens relacionados a uma dada string de pesquisa
	 * 
	 * @return String contendo uma lista com todos os itens de uma dada string de
	 *         pesquisa
	 */

	public String pesquisaItemParaDoacaoPorDescricao(String descricao) {
		return controleDois.pesquisaItemParaDoacaoPorDescricao(descricao);
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
		return controleDois.adicionaItemNecessario(idReceptor, descricaoItem, quantidade, tags);
	}

	/**
	 * Metodo responsavel por listar todos os itens necessario cadastrados no
	 * eDoe.com ordenados pelo identificador unico dos itens
	 */
	public String listaItensNecessarios() {
		return controleDois.listaItensNecessarios();
	}

	/**
	 * Metodo responsavel por atualizar as tags ou a quantidade de um item
	 * necessario
	 * 
	 * @param itemNecId  Inteiro que representa o id de um item necessario
	 * @param idReceptor String que representa o id de um receptor
	 * @param quantidade Inteiro que representa a quantidade de itens necessarios
	 * @param tags       String que representa as tags de um item
	 */
	public String atualizaItemNecessario(String idReceptor, int itemNecId, int quantidade, String tags) {
		return controleDois.atualizaItemNecessario(itemNecId, idReceptor, quantidade, tags);
	}

	/**
	 * Metodo responsavel por remover um item necessario de um receptor
	 * 
	 * @param idReceptor String que representa o id de um usuario receptor
	 * @param idItem     Inteiro que representa um id de um item necessario
	 */
	public void removeItemNecessario(String idReceptor, int idItem) {
		controleDois.removeItemNecessario(idReceptor, idItem);
	}

	/**
	 * Metodo responsavel por encontrar casamentos (matches) entre itens a serem
	 * doados e itens necessários.
	 * 
	 * @param idReceptor       String que representa o id de um usuario receptor
	 * @param idItemNecessario Inteiro que representa um id de um item necessario
	 * @return String contendo Matches
	 */
	public String match(String idReceptor, int idItemNecessario) {
		return this.controleDois.match(idReceptor, idItemNecessario);
		// controleUm.getReceptor(idReceptor).match(idReceptor, idItemNecessario);
	}

}