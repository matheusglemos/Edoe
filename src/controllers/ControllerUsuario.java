package controllers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.edoe.models.Doador;
import com.edoe.models.Receptor;
import com.edoe.models.Usuario;

/**
 * Classe que controla o usuario. Adiciona, pesquisa atualiza e remove usuarios.
 * 
 * @author Matheus Gusmao
 * @author Davidson Guedes
 * @author Caroliny Silva
 * @author Almir Crispiniano
 *
 */
public class ControllerUsuario {

	/**
	 * Atributo que representa os doadores.
	 */
	private Map<String, Usuario> doadores;

	/**
	 * Construtor do controlador de usuarios.
	 */
	public ControllerUsuario() {
		this.doadores = new HashMap<>();
	}
	
	public void lerReceptores(String caminho) throws IOException {
		Scanner sc = new Scanner(new File(caminho));
		String linha = null;
		while(sc.hasNextLine()) {
			linha = sc.nextLine();
			if(linha.equals("id,nome,E-mail,celular,classe")) {
				continue;
			}String dadosUsuario[] = linha.split(",");
			if(dadosUsuario.length != 5) {
				throw new IOException("Campos invalidos");
			}doadores.put(dadosUsuario[0], new Receptor(dadosUsuario[1], dadosUsuario[2], dadosUsuario[3], dadosUsuario[0], dadosUsuario[4]));
			
		}
	}

	/**
	 * Metodo responsavel por verificar se existe uma usuario cadastrado no mapa de
	 * doadores
	 * 
	 * @param id String que representa o id do usuario doador.
	 * 
	 * @return um booleano
	 * 
	 */
	public boolean existeUsuario(String id) {
		return this.doadores.containsKey(id);
	}

	/**
	 * Metodo responsavel por adicionar um usuario doador.
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
	public void adicionaDoador(String nome, String id, String email, String celular, String classe) {
		if (this.existeUsuario(id)) {
			throw new IllegalArgumentException("Usuario ja existente: " + id + ".");
		}
		Doador doador = new Doador(nome, email, celular, id, classe);
		this.doadores.put(id, doador);
	}

	/**
	 * Metodo responsavel por pesquisar um usuario atraves de sua identificacao.
	 * 
	 * @param id String que representa o nome do usuario.
	 * 
	 * @return A representacao textual do usuario, por meio da pesquisa de seu nome.
	 */
	public String pesquisaUsuarioPorNome(String nome) {
		for (Usuario Doadores : doadores.values()) {
			if (doadores.get(nome).equals(nome)) {
				return this.doadores.toString();
			}
		}throw new IllegalArgumentException("Usuario nao encontrado: " + nome + ".");
	}

	/**
	 * Metodo responsavel por pesquisar um usuario atraves de sua identificacao.
	 * 
	 * @param id String que representa o id do usuario.
	 * 
	 * @return A representacao textual do usuario, por meio da pesquisa de seu id.
	 */
	public String pesquisaUsuarioPorId(String id) {
		if (!this.existeUsuario(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + this.doadores.get(id) + ".");
		}
		return this.doadores.get(id).toString();
	}

	/**
	 * Metodo responsavel por atualizar um usuario no sistema.
	 * 
	 * @param id String que representa o id do usuario.
	 * 
	 * @return null.
	 */
	public void atualizaUsuario(String id,String nome, String email, String celular) {
		if (this.existeUsuario(id)) {
			if (nome != null) {
				this.doadores.get(id).setNome(nome);
			}
			if(email != null) {
				this.doadores.get(id).setEmail(email);
			}
			if(celular != null) {
				this.doadores.get(id).setCelular(celular);
			}
		}
		throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
	}

	/**
	 * Metodo responsavel por remover um usuario do sistema.
	 * 
	 * @param id String que representa o id do usuario.
	 */
	public void removeUsuario(String id) {
		if (!this.existeUsuario(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		}
		this.doadores.remove(id);
	}

}
