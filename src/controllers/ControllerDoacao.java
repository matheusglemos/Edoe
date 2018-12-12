package controllers;

import java.util.ArrayList;
import java.util.List;

import com.edoe.models.Doacao;
import com.edoe.models.Item;
import com.edoe.models.ItemNecessario;

/**
 * Classe que controla as doaçoẽs entre usuarios. Realiza doações e lista
 * doações
 * 
 * @author Matheus Gusmao
 * @author Davidson Guedes
 * @author Caroliny Silva
 * @author Almir Crispiniano
 *
 */
public class ControllerDoacao {
	/**
	 * Atributo responsavel por ligar uma Doacao a um item
	 */
	private ControllerItens controllerItens;

	/**
	 * Atributo que representa uma lista de doacoes
	 */
	private List<Doacao> doacoes;

	public ControllerDoacao(ControllerItens controllerItens) {

		this.controllerItens = controllerItens;
		this.doacoes = new ArrayList<>();
	}

	/**
	 * Metodo em que o usuário deve indicar o identificador do item necessário e do
	 * item a ser doado. E o sistema deve validar o pedido de doação olhando se os
	 * descritores de itens são mesmo iguais. Caso o pedido seja validado, o sistema
	 * deve atualizar a quantidade de itens a serem doados e de itens necessários
	 * dos itens envolvidos nesta doação. Se uma dessas quantidades cair para zero,
	 * o item específico (para doação ou necessário) é removido do sistema.
	 * 
	 * @param idItemNec   Inteiro representando o id de Um item necessario
	 * @param idItemDoado Inteiro representando o id de um item doado
	 * @param data        String representando a data da doacao
	 */
	public void realizaDoacao(int idItemNec, int idItemDoado, String data) {
		if (idItemNec < 0 || idItemDoado < 0) {
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		}
		if (data == null || data.trim().isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: data nao pode ser vazia ou nula.");
		}
		if (!this.controllerItens.getItemNecessario(idItemNec).getDescricaoItem()
				.equals(this.controllerItens.getItem(idItemDoado).getDescricao())) {
			throw new IllegalArgumentException("Os itens nao tem descricoes iguais.");
		}
		if (this.controllerItens.getItemNecessario(idItemNec).getDescricaoItem()
				.equals(this.controllerItens.getItem(idItemDoado).getDescricao())) {

			int quantidadeItemDoado = controllerItens.getItem(idItemDoado).getQuantidade();
			int quantidadeItemNecessario = controllerItens.getItemNecessario(idItemNec).getQuantidade();

			if (quantidadeItemNecessario < quantidadeItemDoado) {

			}

			if (quantidadeItemDoado == 0) {

			}

			if (quantidadeItemNecessario == 0) {

			}
		}

	}

	/**
	 * Metodo que lista o histórico de doações pela ordem em que as mesmas foram
	 * realizadas (da mais antiga para a mais nova). E caso as datas sejam iguais ele
	 * lista pela ordem alfabética das descrições dos itens doados.
	 * 
	 * @return String contendo as Doacoes
	 */
	public String listaDoacoes() {
		return "0";
	}

}
