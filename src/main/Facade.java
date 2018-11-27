package main;

import java.io.IOException;

import controllers.ControllerItens;
import controllers.ControllerUsuario;
import easyaccept.EasyAccept;

/**
 * Classe que ira se comunicar com os controllers. Facilitando assim a
 * comunicacao com a interface.
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

	public Facade() {
		controleUm = new ControllerUsuario();
		controleDois = new ControllerItens(controleUm);
	}

	public static void main(String[] args) {
		args = new String[] { "main.Facade", "acceptance_tests/use_case_1.txt", "acceptance_tests/use_case_2.txt" };
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
	 * @return Null.
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
	 * 
	 * @param descricao
	 */
	public void adicionaDescritor(String descricao) {
		controleDois.adicionaDescritor(descricao);
	}

	/**
	 * 
	 * @param idDoador
	 * @param idItem
	 * @param descricaoItem
	 * @param quantidade
	 * @param tags
	 */
	public int adicionaItemParaDoacao(String idDoador, String descricaoItem, int quantidade, String tags) {
		return controleDois.adicionaItemParaDoacao(idDoador, descricaoItem, quantidade, tags);
	}

	/**
	 * 
	 * @param idDoador
	 * @param idItem
	 */
	public void exibeItem(String idDoador, int idItem) {
		controleDois.exibeItem(idDoador, idItem);
	}

	/**
	 * 
	 * @param idDoador
	 */
	public void atualizaItemParaDoacao(int idItem, String idDoador, int quantidade, String tags) {
		controleDois.atualizaItemParaDoacao(idItem, idDoador, quantidade, tags);
	}

	/**
	 * 
	 * @param idItem
	 * @param idDoador
	 */
	public void removeItemParaDoacao(int idItem, String idDoador) {
		controleDois.removeItemParaDoacao(idItem, idDoador);
	}

}