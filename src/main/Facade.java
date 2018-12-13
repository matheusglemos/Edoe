package main;

import java.io.IOException;
import java.text.ParseException;

import controllers.ControllerDoacao;
import controllers.ControllerItens;
import controllers.ControllerUsuario;
import easyaccept.EasyAccept;
import excecoes.DataInvalidaException;
import excecoes.DescitorJaExistenteException;
import excecoes.DescricaoInvalidaException;
import excecoes.IdInvalidoException;
import excecoes.ItemNaoEncontradoException;
import excecoes.ItensIguaisException;
import excecoes.NomeInvalidoException;
import excecoes.QuantidadeInvalidaException;
import excecoes.TextoInvalidoException;
import excecoes.UsuarioDeveSerReceptorException;
import excecoes.UsuarioJaExistenteException;
import excecoes.UsuarioNaoEncontradoException;
import excecoes.UsuarioNaoPossuiItensCadastradosException;

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
	 * Atributo referente aos controles do projeto.
	 */
	private ControllerUsuario controleDeUsuarios;
	private ControllerItens controleDeItens;
	private ControllerDoacao controleDeDoacao;

	/**
	 * Construtor.
	 */
	public Facade() {
		controleDeUsuarios = new ControllerUsuario();
		controleDeItens = new ControllerItens(controleDeUsuarios);
		controleDeDoacao = new ControllerDoacao(controleDeItens);
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
				"acceptance_tests/use_case_6.txt", "acceptance_tests/use_case_7.txt" };
		EasyAccept.main(args);
	}

	/**
	 * Metodo responsavel por ler os recptores o arquivo csv.
	 * 
	 * @param caminho String referente ao caminho dos receptores.
	 * 
	 * @throws IOException referente a excecao que podera ser lancada.
	 */
	public void lerReceptores(String caminho) throws IOException {
		this.controleDeUsuarios.lerReceptores(caminho);
	}

	/**
	 * Metodo que adiciona um usuario doador no sistema.
	 * 
	 * @param nome    String que representa o nome do usuario doador.
	 * @param id      String que representa o id do usuario doador.
	 * @param email   String que representa o email do usuario doador.
	 * @param celular String que representa o celular do usuario doador.
	 * @param classe  String que representa a classe do usuario doador.
	 * 
	 * @throws UsuarioJaExistenteException excecao que podera ser lancada.
	 * 
	 * @return Adicao de um doador no sistema.
	 */
	public String adicionaDoador(String id, String nome, String email, String celular, String classe) throws UsuarioJaExistenteException {
		return this.controleDeUsuarios.adicionaDoador(id, nome, email, celular, classe);
	}

	/**
	 * Metodo que pesquisa determinado usuario atraves de seu id.
	 * 
	 * @param id String que representa o id do usuario.
	 * 
	 * @throws UsuarioNaoEncontradoException excecao que podera ser lancada.
	 * @throws IdInvalidoException excecao que podera ser lancada.
	 * 
	 * @return A representacao textual do usuario procurado, se estiver presente no
	 *         sistema.
	 */
	public String pesquisaUsuarioPorId(String id) throws IdInvalidoException, UsuarioNaoEncontradoException {
		return controleDeUsuarios.pesquisaUsuarioPorId(id);
	}

	/**
	 * Metodo que pesquisa determinado usuario atraves do seu nome.
	 * 
	 * @param nome String que representa o nome do usuario.
	 * 
	 * @throws NomeInvalidoException excecao que podera ser lancada.
	 * @throws UsuarioNaoEncontradoException excecao que podera ser lancada.
	 * 
	 * @return A representacao textual do usuario procurado, se estiver presente no
	 *         sistema.
	 */
	public String pesquisaUsuarioPorNome(String nome) throws UsuarioNaoEncontradoException, NomeInvalidoException {
		return controleDeUsuarios.pesquisaUsuarioPorNome(nome);
	}

	/**
	 * Metodo que atualiza um usuario atraves de seu id.
	 * 
	 * @param id      String que representa o id do usuario.
	 * @param nome    String que representa o nome do usuario.
	 * @param email   String que representa o email do usuario.
	 * @param celular String que representa o celular do usuario.
	 * 
	 * @throws UsuarioNaoEncontradoException excecao que podera ser lancada.
	 * @throws IdInvalidoException excecao que podera ser lancada.
	 * 
	 * @return A atualizacao de um usuario no sistema.
	 */
	public String atualizaUsuario(String id, String nome, String email, String celular) throws IdInvalidoException, UsuarioNaoEncontradoException {
		return controleDeUsuarios.atualizaUsuario(id, nome, email, celular);
	}

	/**
	 * Metodo responsavel por remover um usuario do sistema.
	 * 
	 * @param id String que representa o id do usuario.
	 * 
	 * @throws UsuarioNaoEncontradoException excecao que podera ser lancada.
	 * @throws IdInvalidoException excecao que podera ser lancada.
	 */
	public void removeUsuario(String id) throws IdInvalidoException, UsuarioNaoEncontradoException {
		controleDeUsuarios.removeUsuario(id);
	}

	/**
	 * Metodo que adiciona uma descricao no mapa de descritores.
	 * 
	 * @param descricao String que representa a descricao de um item.
	 * 
	 * @throws DescitorJaExistenteException excecao que podera ser lancada.
	 * @throws DescricaoInvalidaException excecao que podera ser lancada.
	 */
	public void adicionaDescritor(String descricao) throws DescricaoInvalidaException, DescitorJaExistenteException {
		controleDeItens.adicionaDescritor(descricao);
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
	 * @throws QuantidadeInvalidaException  excecao que podera ser lancada.
	 * @throws UsuarioNaoEncontradoException excecao que podera ser lancada.
	 * @throws IdInvalidoException excecao que podera ser lancada.
	 * @throws DescricaoInvalidaException excecao que podera ser lancada.
	 * 
	 * @return o id do item adicionado para a doacao.
	 */
	public int adicionaItemParaDoacao(String idDoador, String descricaoItem, int quantidade, String tags) throws DescricaoInvalidaException, IdInvalidoException, UsuarioNaoEncontradoException, QuantidadeInvalidaException {
		return controleDeItens.adicionaItemParaDoacao(idDoador, descricaoItem, quantidade, tags);
	}

	/**
	 * Metodo responsavel por acessar um item de um doador e exibir a sua
	 * representacao textual.
	 * 
	 * @param idDoador String que representa o id de um doador.
	 * @param idItem   Inteiro que representa o id de um item.
	 * 
	 * @throws UsuarioNaoEncontradoException excecao que podera ser lancada.
	 * @throws ItemNaoEncontradoException excecao que podera ser lancada.
	 * 
	 * @return exibicao o item.
	 */
	public String exibeItem(int idItem, String idDoador) throws ItemNaoEncontradoException, UsuarioNaoEncontradoException {
		return controleDeItens.exibeItem(idDoador, idItem);
	}

	/**
	 * Metodo responsavel por atualizar as tags ou a quantidade de um item.
	 * 
	 * @param idDoador   String que representa o id de um doador.
	 * @param idItem     Inteiro que representa o id de um item.
	 * @param quantidade Inteiro que representa a quantidade de itens.
	 * @param tags       String que representa as tags de um item.
	 * 
	 * @throws ItemNaoEncontradoException excecao que podera ser lancada.
	 * @throws UsuarioNaoEncontradoException excecao que podera ser lancada.
	 * @throws IdInvalidoException excecao que podera ser lancada. 
	 * 
	 * @return Atualizacao do item para doacao.
	 */
	public String atualizaItemParaDoacao(int idItem, String idDoador, int quantidade, String tags) throws IdInvalidoException, UsuarioNaoEncontradoException, ItemNaoEncontradoException {
		return controleDeItens.atualizaItemParaDoacao(idItem, idDoador, quantidade, tags);
	}

	/**
	 * Metodo responsavel por remover um item de um doador.
	 * 
	 * @param idDoador String que representa o id de um doador.
	 * @param idItem   Inteiro que representa o id de um item.
	 * 
	 * @throws UsuarioNaoPossuiItensCadastradosException excecao que podera ser lancada.
	 * @throws ItemNaoEncontradoException excecao que podera ser lancada.
	 * @throws UsuarioNaoEncontradoException excecao que podera ser lancada.
	 * @throws IdInvalidoException excecao que podera ser lancada.
	 */
	public void removeItemParaDoacao(int idItem, String idDoador) throws IdInvalidoException, UsuarioNaoEncontradoException, ItemNaoEncontradoException, UsuarioNaoPossuiItensCadastradosException {
		controleDeItens.removeItemParaDoacao(idItem, idDoador);
	}

	/**
	 * Metodo que permite uma listagem de todos os descritores de itens cadastrados
	 * no sistema, ordenado em ordem alfabetica pela descricao do item.
	 * 
	 * @return String contendo lista dos descritores em ordem alfabetica.
	 */
	public String listaDescritorDeItensParaDoacao() {
		return controleDeItens.listaDescritorDeItensParaDoacao();
	}

	/**
	 * Metodo que permite a listagem de todos os itens inseridos no sistema,
	 * ordenada pela quantidade do item no sistema.
	 * 
	 * @return String contendo lista dos itens em ordem pela quantidade do item.
	 */
	public String listaItensParaDoacao() {
		return controleDeItens.listaItensParaDoacao();
	}

	/**
	 * Metodo que lista todos os itens relacionados a uma dada string de pesquisa.
	 * 
	 * @param descricao String correspondente a descricao do item para doacao.
	 * 
	 * @throws TextoInvalidoException excecao que podera ser lancada.
	 * 
	 * @return String contendo uma lista com todos os itens de uma dada string de
	 *         pesquisa.
	 */
	public String pesquisaItemParaDoacaoPorDescricao(String descricao) throws TextoInvalidoException {
		return controleDeItens.pesquisaItemParaDoacaoPorDescricao(descricao);
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
	 * @throws UsuarioNaoEncontradoException excecao que podera ser lancada.
	 * @throws QuantidadeInvalidaException excecao que podera ser lancada.
	 * @throws IdInvalidoException excecao que podera ser lancada.
	 * @throws DescricaoInvalidaException excecao que podera ser lancada.
	 * @throws UsuarioDeveSerReceptorException excecao que podera ser lancada.
	 * 
	 * @return o id do item necessitado adicionado.
	 */
	public int adicionaItemNecessario(String idReceptor, String descricaoItem, int quantidade, String tags) throws DescricaoInvalidaException, IdInvalidoException, QuantidadeInvalidaException, UsuarioNaoEncontradoException, UsuarioDeveSerReceptorException {
		return controleDeItens.adicionaItemNecessario(idReceptor, descricaoItem, quantidade, tags);
	}

	/**
	 * Metodo responsavel por listar todos os itens necessario cadastrados no
	 * eDoe.com ordenados pelo identificador unico dos itens.
	 * 
	 * @return String contendo uma lista de itens necessarios.
	 */
	public String listaItensNecessarios() {
		return controleDeItens.listaItensNecessarios();
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
	 * @throws UsuarioNaoEncontradoException excecao que podera ser lancada.
	 * @throws IdInvalidoException excecao que podera ser lancada.
	 * @throws UsuarioDeveSerReceptorException excecao que podera ser lancada.
	 * 
	 * @return String vazia.
	 */
	public String atualizaItemNecessario(String idReceptor, int itemNecId, int quantidade, String tags) throws ItemNaoEncontradoException, IdInvalidoException, UsuarioNaoEncontradoException, UsuarioDeveSerReceptorException {
		return controleDeItens.atualizaItemNecessario(itemNecId, idReceptor, quantidade, tags);
	}

	/**
	 * Metodo responsavel por remover um item necessario de um receptor.
	 * 
	 * @param idReceptor String que representa o id de um usuario receptor.
	 * @param idItem     Inteiro que representa um id de um item necessario.
	 * 
	 * @throws UsuarioNaoPossuiItensCadastradosException excecao que podera ser lancada.
	 * @throws ItemNaoEncontradoException excecao que podera ser lancada.
	 * @throws UsuarioNaoEncontradoException excecao que podera ser lancada.
	 * @throws IdInvalidoException excecao que podera ser lancada.
	 * @throws UsuarioDeveSerReceptorException excecao que podera ser lancada.
	 */
	public void removeItemNecessario(String idReceptor, int idItem) throws IdInvalidoException, UsuarioNaoEncontradoException, ItemNaoEncontradoException, UsuarioNaoPossuiItensCadastradosException, UsuarioDeveSerReceptorException {
		controleDeItens.removeItemNecessario(idReceptor, idItem);
	}

	/**
	 * Metodo responsavel por encontrar casamentos (matches) entre itens a serem
	 * doados e itens necessarios.
	 * 
	 * @param idReceptor       String que representa o id de um usuario receptor.
	 * @param idItemNecessario Inteiro que representa um id de um item necessario.
	 * 
	 * @throws ItemNaoEncontradoException excecao que podera ser lancada.
	 * @throws UsuarioNaoEncontradoException excecao que podera ser lancada.
	 * @throws IdInvalidoException excecao que podera ser lancada.
	 * @throws UsuarioDeveSerReceptorException excecao que podera ser lancada.
	 * 
	 * @return String contendo Matches.
	 */
	public String match(String idReceptor, int idItemNecessario) throws IdInvalidoException, UsuarioNaoEncontradoException, ItemNaoEncontradoException, UsuarioDeveSerReceptorException {
		return this.controleDeItens.match(idReceptor, idItemNecessario);
	}

	/**
	 * Metodo responsavel por realizar uma doacao.
	 * 
	 * @param idItemNec   Inteiro referente a identificacao de um item necessario.
	 * @param idItemDoado Inteiro referente a identificacao de um item doado.
	 * @param data        String referente a data da doacao.
	 * 
	 * @return Exibicao das informacoes da doacao como uma String.
	 * 
	 * @throws ParseException excecao que podera ser lancada.
	 * @throws DataInvalidaException excecao que podera ser lancada.
	 * @throws IdInvalidoException excecao que podera ser lancada.
	 * @throws ItensIguaisException excecao que podera ser lancada.
	 * @throws UsuarioNaoPossuiItensCadastradosException excecao que podera ser lancada.
	 * @throws UsuarioNaoEncontradoException excecao que podera ser lancada.
	 * @throws ItemNaoEncontradoException excecao que podera ser lancada.
	 * @throws UsuarioDeveSerReceptorException excecao que podera ser lancada.
	 */
	public String realizaDoacao(int idItemNec, int idItemDoado, String data) throws ParseException, DataInvalidaException, IdInvalidoException, ItensIguaisException, ItemNaoEncontradoException, UsuarioNaoEncontradoException, UsuarioNaoPossuiItensCadastradosException, UsuarioDeveSerReceptorException {
		return this.controleDeDoacao.realizaDoacao(idItemNec, idItemDoado, data).toString();
	}

	/**
	 * Metodo responsavel por listar as doacoes.
	 * 
	 * @return String contendo a lista de doacoes.
	 */
	public String listaDoacoes() {
		return this.controleDeDoacao.listaDoacoes();
	}

	/**
	 * Metodo responsavel por realizar o salvamento dos dados no arquivo.
	 * 
	 * @throws IOException excecao que podera ser lancada.
	 */
	public void finalizaSistema() throws IOException {
		this.controleDeUsuarios.salvar();
		this.controleDeItens.salvar();
		this.controleDeDoacao.salvar();
	}

	/**
	 * Metodo responsavel por realizar o carregamento dos dados salvos no arquivo.
	 */
	public void iniciaSistema() {
		this.controleDeUsuarios.carregarDados();
		this.controleDeItens.carregarDados();
		this.controleDeDoacao.carregarDados();
	}

}