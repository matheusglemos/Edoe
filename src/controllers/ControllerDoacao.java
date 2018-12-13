package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.edoe.models.Doacao;
import com.edoe.models.Item;
import com.edoe.models.ItemNecessario;

import comparators.OrdenarDoacaoPelaData;
import excecoes.DataInvalidaException;
import excecoes.IdInvalidoException;
import excecoes.ItemNaoEncontradoException;
import excecoes.ItensIguaisException;
import excecoes.UsuarioDeveSerReceptorException;
import excecoes.UsuarioNaoEncontradoException;
import excecoes.UsuarioNaoPossuiItensCadastradosException;

/**
 * Classe que controla as doacoes entre usuarios. Realiza doacoes e lista
 * doacoes.
 * 
 * @author Matheus Gusmao
 * @author Davidson Guedes
 * @author Caroliny Silva
 * @author Almir Crispiniano
 *
 */
public class ControllerDoacao {
	/**
	 * Atributo responsavel por ligar uma Doacao a um item.
	 */
	private ControllerItens controllerItens;

	/**
	 * Atributo que representa uma lista de doacoes.
	 */
	private List<Doacao> doacoes;

	/**
	 * Contrutor do controller de doacoes.
	 * 
	 * @param controllerItens referente ao controller de itens.
	 */
	public ControllerDoacao(ControllerItens controllerItens) {
		this.controllerItens = controllerItens;
		this.doacoes = new ArrayList<>();
	}

	/**
	 * Metodo em que o usuario deve indicar o identificador do item necessario e do
	 * item a ser doado. E o sistema deve validar o pedido de doacao olhando se os
	 * descritores de itens sao mesmo iguais. Caso o pedido seja validado, o sistema
	 * deve atualizar a quantidade de itens a serem doados e de itens necessarios
	 * dos itens envolvidos nesta doacao. Se uma dessas quantidades cair para zero,
	 * o item especifico (para doacao ou necessario) e removido do sistema.
	 * 
	 * @param idItemNec   Inteiro representando o id de Um item necessario.
	 * @param idItemDoado Inteiro representando o id de um item doado.
	 * @param data        String representando a data da doacao.
	 * 
	 * @throws ParseException excecao que podera ser lancada.
	 * @throws DataInvalidaException excecao que podera ser lancada.
	 * @throws IdInvalidoException excecao que podera ser lancada.
	 * @throws ItensIguaisException excecao que podera ser lancada.	 
	 * @throws ItemNaoEncontradoException excecao que podera ser lancada.
	 * @throws UsuarioNaoPossuiItensCadastradosException excecao que podera ser lancada.
	 * @throws UsuarioNaoEncontradoException excecao que podera ser lancada.
	 * @throws UsuarioDeveSerReceptorException excecao que podera ser lancada.
	 * 
	 * @return Doacao. 
	 */
	public Doacao realizaDoacao(int idItemNec, int idItemDoado, String data) throws ParseException, DataInvalidaException, IdInvalidoException, ItensIguaisException, ItemNaoEncontradoException, UsuarioNaoEncontradoException, UsuarioNaoPossuiItensCadastradosException, UsuarioDeveSerReceptorException {
		if (idItemNec < 0 || idItemDoado < 0) {
			throw new IdInvalidoException("Entrada invalida: id do item nao pode ser negativo.");
		}
		if (data == null || data.trim().isEmpty()) {
			throw new DataInvalidaException("Entrada invalida: data nao pode ser vazia ou nula.");
		}

		Item itemDoado = controllerItens.pesquisaItem(idItemDoado);
		ItemNecessario itemNecessario = controllerItens.pesquisaItemNecessario(idItemNec);

		if (!itemNecessario.getDescricaoItem().equals(itemDoado.getDescricao())) {
			throw new ItensIguaisException("Os itens nao tem descricoes iguais.");
		}

		int quantItensDoados = itemNecessario.getQuantidade();
		if (quantItensDoados > itemDoado.getQuantidade()) {
			quantItensDoados = itemDoado.getQuantidade();
		}

		itemDoado.setQuantidade(itemDoado.getQuantidade() - quantItensDoados);
		if (itemDoado.getQuantidade() == 0) {
			this.controllerItens.removeItemParaDoacao(idItemDoado, itemDoado.idDoador());
		}

		itemNecessario.setQuantidade(itemNecessario.getQuantidade() - quantItensDoados);
		if (itemNecessario.getQuantidade() == 0) {
			this.controllerItens.removeItemNecessario(itemNecessario.idReceptor(), idItemNec);
		}

		Doacao doacao = new Doacao(data, itemDoado, itemNecessario, quantItensDoados);
		this.doacoes.add(doacao);
		return doacao;

	}

	/**
	 * Metodo que lista o historico de doacoes pela ordem em que as mesmas foram
	 * realizadas (da mais antiga para a mais nova). E caso as datas sejam iguais
	 * ele lista pela ordem alfabetica das descricoes dos itens doados.
	 * 
	 * @return String contendo as Doacoes.
	 */
	public String listaDoacoes() {
		Collections.sort(this.doacoes, new OrdenarDoacaoPelaData());
		String res = "";
		for (int i = 0; i < this.doacoes.size(); i++) {
			if (i == this.doacoes.size() - 1) {
				res += doacoes.get(i).toString();
			} else {
				res += doacoes.get(i).toString() + " | ";
			}
		}
		return res;
	}

	/**
	 * Metodo responsavel por salvar os dados em um arquivo.
	 * 
	 * @throws IOException excecao que podera ser lancada.
	 */
	public void salvar() throws IOException {
		File file = new File("persistencia/doacoes.csv");
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream obj = new ObjectOutputStream(fos);
		try {
			obj.writeObject(this.doacoes);
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
			os = new ObjectInputStream(new FileInputStream("persistencia/doacoes.csv"));
			this.doacoes = (List<Doacao>) os.readObject();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Arquivo nao existe no sistema.");
		} catch (IOException e) {
			System.out.println("Algum erro ocorre...");
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Alguma coisa no sistema mudou");
		}
	}
}
