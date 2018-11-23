package Validador;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsavel por validar um usuario.
 * 
 * @author Matheus Gusmao
 * @author Davidson Guedes
 * @author Almir Crispiniano
 * @author Caroliny Silva
 *
 */
public class ValidadorUsuario {
	/**
	 * O metodo ira analisar as excecoes durante a criacao do usuario. Caso nome,
	 * email, celular, id ou classe sejam nulo ou vazio, a excecao sera lancada
	 * juntamente com uma mensagem.
	 * 
	 * @param nome
	 *            String que representa o nome do usuario doador.
	 * 
	 * @param id
	 *            String que representa o id do usuario doador.
	 * 
	 * @param email
	 *            String que representa o email do usuario doador.
	 * 
	 * @param celular
	 *            String que representa o celular do usuario doador.
	 * 
	 * @param classe
	 *            String que representa a classe do usuario doador.
	 */
	public static void validaCriacaoDeUsuario(String nome, String email, String celular, String id, String classe) {
		
		List<String> listaClasses = new ArrayList<String>();
		listaClasses.add("pessoa_fisica");
		listaClasses.add("igreja");
		listaClasses.add("orgao publico municipal");
		listaClasses.add("orgao publico estadual");
		listaClasses.add("orgao publico federal");
		listaClasses.add("ong");
		listaClasses.add("associacao");
		listaClasses.add("sociedade");
		
		if (nome == null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: nome nao pode ser vazio ou nulo.");
		}
		if (email == null || email.trim().isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: email nao pode ser vazio ou nulo.");
		}
		if (celular == null || celular.trim().isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: celular nao pode ser vazio ou nulo.");
		}
		if (id == null || id.trim().isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		if (classe == null || classe.trim().isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: classe nao pode ser vazia ou nula.");
		}
		if (!listaClasses.contains(classe.trim().toLowerCase())) {
			throw new IllegalArgumentException("Entrada invalida: opcao de classe invalida.");
		}
	}

}