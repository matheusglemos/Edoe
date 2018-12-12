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

	/**
	 * Atributo referente ao controle de usuarios.
	 */
	private ControllerUsuario controleUm;
	private ControllerItens controleDois;

	/**
	 * Construtor.
	 */
	public Facade() {
		controleUm = new ControllerUsuario();
		controleDois = new ControllerItens(controleUm);
	}

	/**
	 * Metodo que ira verificar os testes de aceitacao.
	 * 
	 * @param args Array de String que guardara o caminho dos testes de aceitacao e
	 *             o caminho da Facade.
	 */
	public static void main(String[] args) {
		args = new String[] { "main.Facade", "acceptance_tests/use_case_1.txt", "acceptance_tests/use_case_2.txt",
				"acceptance_tests/use_case_3.txt", "acceptance_tests/use_case_4.txt", "acceptance_tests/use_case_5.txt",
				"acceptance_tests/use_case_6.txt" };
		EasyAccept.main(args);
	}

	/**
	 * Metodo responsavel por ler os recptores o arquivo csv.
	 * 
	 * @param caminho String referente ao caminho dos receptores.
	 * @throws IOException referente a excecao que podera ser lancada.
	 */
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
	 * 
	 * @return Adicao de um doador no sistema.
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
	 * @param id      String que representa o id do usuario.
	 * @param nome    String que representa o nome do usuario.
	 * @param email   String que representa o email do usuario.
	 * @param celular String que representa o celular do usuario.
	 * 
	 * @return A atualizacao de um usuario no sistema.
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
	 * Metodo que adiciona uma descricao no mapa de descritores.
	 * 
	 * @param descricao String que representa a descricao de um item.
	 */
	public void adicionaDescritor(String descricao) {
		controleDois.adicionaDescritor(descricao);
	}

	/**
	 * Metodo responsavel por adicionar um item para doacao associado a um usuario
	 * doador.
	 * 
	 * @param idDoador      String que representa o id de um doador.
	 * @param descricaoItem String que representa a descricao de um item.
	 * @param quantidade    Inteiro que representa a quantidade de itens.
	 * @param tags          String que representa as tags de um item.
	 * @return o id do item adicionado para a doacao.
	 */
	public int adicionaItemParaDoacao(String idDoador, String descricaoItem, int quantidade, String tags) {
		return controleDois.adicionaItemParaDoacao(idDoador, descricaoItem, quantidade, tags);
	}

	/**
	 * Metodo responsavel por acessar um item de um doador e exibir a sua
	 * representacao textual.
	 * 
	 * @param idDoador String que representa o id de um doador.
	 * @param idItem   Inteiro que representa o id de um item.
	 * 
	 * @return exibicao o item.
	 */
	public String exibeItem(int idItem, String idDoador) {
		return controleDois.exibeItem(idDoador, idItem);
	}

	/**
	 * Metodo responsavel por atualizar as tags ou a quantidade de um item.
	 * 
	 * @param idDoador   String que representa o id de um doador.
	 * @param idItem     Inteiro que representa o id de um item.
	 * @param quantidade Inteiro que representa a quantidade de itens.
	 * @param tags       String que representa as tags de um item.
	 * 
	 * @return Atualizacao do item para doacao.
	 */
	public String atualizaItemParaDoacao(int idItem, String idDoador, int quantidade, String tags) {
		return controleDois.atualizaItemParaDoacao(idItem, idDoador, quantidade, tags);
	}

	/**
	 * Metodo responsavel por remover um item de um doador.
	 * 
	 * @param idDoador String que representa o id de um doador.
	 * @param idItem   Inteiro que representa o id de um item.
	 */
	public void removeItemParaDoacao(int idItem, String idDoador) {
		controleDois.removeItemParaDoacao(idItem, idDoador);
	}

	/**
	 * Metodo que permite uma listagem de todos os descritores de itens cadastrados
	 * no sistema, ordenado em ordem alfabetica pela descricao do item.
	 * 
	 * @return String contendo lista dos descritores em ordem alfabetica.
	 */
	public String listaDescritorDeItensParaDoacao() {
		return controleDois.listaDescritorDeItensParaDoacao();
	}

	/**
	 * Metodo que permite a listagem de todos os itens inseridos no sistema,
	 * ordenada pela quantidade do item no sistema.
	 * 
	 * @return String contendo lista dos itens em ordem pela quantidade do item.
	 */
	public String listaItensParaDoacao() {
		return controleDois.listaItensParaDoacao();
	}

	/**
	 * Metodo que lista todos os itens relacionados a uma dada string de pesquisa.
	 * 
	 * @param descricao String correspondente a descricao do item para doacao.
	 * 
	 * @return String contendo uma lista com todos os itens de uma dada string de
	 *         pesquisa.
	 */
	public String pesquisaItemParaDoacaoPorDescricao(String descricao) {
		return controleDois.pesquisaItemParaDoacaoPorDescricao(descricao);
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
	 * @return o id do item necessitado adicionado.
	 */
	public int adicionaItemNecessario(String idReceptor, String descricaoItem, int quantidade, String tags) {
		return controleDois.adicionaItemNecessario(idReceptor, descricaoItem, quantidade, tags);
	}

	/**
	 * Metodo responsavel por listar todos os itens necessario cadastrados no
	 * eDoe.com ordenados pelo identificador unico dos itens.
	 * 
	 * @return String contendo uma lista de itens necessarios.
	 */
	public String listaItensNecessarios() {
		return controleDois.listaItensNecessarios();
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
	 * @return String vazia.
	 */
	public String atualizaItemNecessario(String idReceptor, int itemNecId, int quantidade, String tags) {
		return controleDois.atualizaItemNecessario(itemNecId, idReceptor, quantidade, tags);
	}

	/**
	 * Metodo responsavel por remover um item necessario de um receptor.
	 * 
	 * @param idReceptor String que representa o id de um usuario receptor.
	 * @param idItem     Inteiro que representa um id de um item necessario.
	 */
	public void removeItemNecessario(String idReceptor, int idItem) {
		controleDois.removeItemNecessario(idReceptor, idItem);
	}

	/**
	 * Metodo responsavel por encontrar casamentos (matches) entre itens a serem
	 * doados e itens necessarios.
	 * 
	 * @param idReceptor       String que representa o id de um usuario receptor.
	 * @param idItemNecessario Inteiro que representa um id de um item necessario.
	 * @return String contendo Matches.
	 */
	public String match(String idReceptor, int idItemNecessario) {
		return this.controleDois.match(idReceptor, idItemNecessario);
		// controleUm.getReceptor(idReceptor).match(idReceptor, idItemNecessario);
	}

	public void realizaDoacao(int idItemNec, int idItemDoado, String data) {
	}

	public String listaDoacoes() {
		return "";
	}

}