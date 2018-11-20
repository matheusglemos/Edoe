package com.edoe.models;
/**
 * 
 *
 */
public class Facade {
	private ControllerUsuario controle;

	public void inicializa() {
		controle = new ControllerUsuario();
	}

	public void adicionaDoador(String nome, String id, String email, String celular, String classe) {
		controle.adicionaDoador(nome, id, email, celular, classe);
	}

	public String pesquisaUsuarioPorId(String id) {
		return controle.pesquisaUsuarioPorId(id);
	}

	public String pesquisaUsuarioPorNome(String nome) {
		return controle.pesquisaUsuarioPorId(nome);
	}

	public String atualizaUsuario(String id) {
		return controle.atualizaUsuario(id);

	}

	public void removeUsuario(String id) {
		controle.removeUsuario(id);
	}
}