package Validador;

public class ValidadorUsuario {
	/**
	 * O método irá analisar as excecoes durante a criacao do usuario. Caso nome,
	 * email, celular, id ou classe sejam nulo ou vazio, a excecao sera lancada
	 * juntamente com uma mensagem.
	 * 
	 * @param nome
	 * @param email
	 * @param celular
	 * @param id
	 * @param classe
	 */
	public static void validaCriacaoDeUsuario(String nome, String email, String celular, String id, String classe) {
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
	}

}