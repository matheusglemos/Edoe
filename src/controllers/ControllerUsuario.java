package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.LinkedHashMap;
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
	 * Atributo que representa os usuarios.
	 */
	private Map<String, Usuario> usuarios;

	/**
	 * Construtor do controlador de usuarios.
	 */
	public ControllerUsuario() {
		this.usuarios = new LinkedHashMap<>();
	}

	/**
	 * Metodo responsavel por ler receptores.
	 * 
	 * @param caminho responsavel pelo caminho do arquivo.
	 * 
	 * @throws IOException referente a uma excecao que podera ser lancada.
	 */
	public void lerReceptores(String caminho) throws IOException {
		Scanner sc = new Scanner(new File(caminho));
		String linha = null;
		while (sc.hasNextLine()) {
			linha = sc.nextLine();
			if (linha.equals("id,nome,e-mail,celular,classe")) {
				continue;
			}
			String dadosUsuario[] = linha.split(",");
			if (dadosUsuario.length != 5) {
				throw new IOException("Campos invalidos");
			}
			usuarios.put(dadosUsuario[0],
					new Receptor(dadosUsuario[1], dadosUsuario[2], dadosUsuario[3], dadosUsuario[0], dadosUsuario[4]));

		}
	}

	/**
	 * Metodo responsavel por verificar se existe uma usuario cadastrado no mapa de
	 * usuarios.
	 * 
	 * @param id String que representa o id do usuario doador.
	 * 
	 * @return um booleano.
	 * 
	 */
	public boolean existeUsuario(String id) {
		return this.usuarios.containsKey(id);
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
	 * 
	 * @return id do doador adicionado.
	 */
	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
		if (this.existeUsuario(id)) {
			throw new IllegalArgumentException("Usuario ja existente: " + id + ".");
		}
		Doador doador = new Doador(nome, email, celular, id, classe);
		this.usuarios.put(id, doador);
		return id;
	}

	/**
	 * Metodo responsavel por pesquisar um usuario atraves de sua identificacao.
	 * 
	 * @param nome String que representa o nome do usuario.
	 * 
	 * @return A representacao textual do usuario, por meio da pesquisa de seu nome.
	 */
	public String pesquisaUsuarioPorNome(String nome) {
		String res = "";
		if (nome == null || nome.equals("")) {
			throw new IllegalArgumentException("Entrada invalida: nome nao pode ser vazio ou nulo.");
		}
		int i = 0;
		for (Usuario usuarios : usuarios.values()) {
			if (usuarios.getNome().equals(nome)) {
				if (i != 0) {
					res += " | " + usuarios.toString();
				} else {
					res += usuarios.toString();
					i++;
				}
			}
		}
		if (res.equals("")) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + nome + ".");
		}
		return res;
	}

	/**
	 * Metodo responsavel por pesquisar um usuario atraves de sua identificacao.
	 * 
	 * @param id String que representa o id do usuario.
	 * 
	 * @return A representacao textual do usuario, por meio da pesquisa de seu id.
	 */
	public String pesquisaUsuarioPorId(String id) {
		if (id == null || id.equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		if (!this.existeUsuario(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		}
		return this.usuarios.get(id).toString();
	}

	/**
	 * Metodo responsavel por atualizar um usuario no sistema.
	 * 
	 * @param id      String que representa o id do usuario.
	 * @param nome    String que representa o nome do usuario.
	 * @param email   String que representa o email do usuario.
	 * @param celular String que representa o celular do usuario.
	 * 
	 * @return a representacao textual atualizada do usuario.
	 */
	public String atualizaUsuario(String id, String nome, String email, String celular) {
		if (id == null || id.equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		if (this.existeUsuario(id)) {
			if (nome != null && !"".equals(nome)) {
				this.usuarios.get(id).setNome(nome);
			}
			if (email != null && !"".equals(email)) {
				this.usuarios.get(id).setEmail(email);
			}
			if (celular != null && !"".equals(celular)) {
				this.usuarios.get(id).setCelular(celular);
			}
			return this.usuarios.get(id).toString();
		}
		throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");

	}

	/**
	 * Metodo responsavel por remover um usuario do sistema.
	 * 
	 * @param id String que representa o id do usuario.
	 */
	public void removeUsuario(String id) {
		if (id == null || id.equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		if (!this.existeUsuario(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		}
		this.usuarios.remove(id);
	}

	/**
	 * Metodo responsavel por retornar um doador a partir do id de um usuario.
	 * 
	 * @param id String que representa o id do usuario.
	 * @return Um usuario doador.
	 */
	public Doador getDoador(String id) {
		return (Doador) this.usuarios.get(id);
	}

	/**
	 * Metodo responsavel por retornar um receptor a partir do id de um usuario.
	 * 
	 * @param id String que representa o id do usuario.
	 * @return Um usuario receptor.
	 */
	public Receptor getReceptor(String id) {
		Usuario user = this.usuarios.get(id);
		if (user instanceof Receptor) {
			return (Receptor) user;
		}
		throw new IllegalArgumentException("O Usuario deve ser um receptor: " + id + ".");
	}

	public void salvar() throws IOException {
		File file = new File("persistencia/usuario.csv");
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream obj = new ObjectOutputStream(fos);
		try {
			obj.writeObject(this.usuarios);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Arquivo nao existe no sistema.");
		} catch (IOException e) {
			System.out.println("Algum erro ocorreu...");
		}
	}

	public void carregarDados() {
		ObjectInputStream os;
		try {
			os = new ObjectInputStream(new FileInputStream("persistencia/usuario.csv"));
			this.usuarios = (Map<String, Usuario>) os.readObject();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Arquivo nao existe no sistema.");
		} catch (IOException e) {
			System.out.println("Algum erro ocorre...");
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Alguma coisa no sistema mudou");
		}
	}

}
