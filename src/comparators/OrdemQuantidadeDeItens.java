package comparators;

import java.util.Comparator;

import com.edoe.models.Item;

/**
 * Classe responsavel por implementar o comparator da classe item, com objetivo
 * de ordenar os itens pela sua quantidade
 * 
 * @author Matheus Gusmao
 * @author Davidson Guedes
 * @author Caroliny Silva
 * @author Almir Crispiniano
 */
public class OrdemQuantidadeDeItens implements Comparator<Item> {
	/**
	 * Metodo que compara dois itens pela sua quantidade, se dois itens tiverem a
	 * mesma quantidade os mesmo seram comparados pela sua descrição
	 */
	@Override
	public int compare(Item o1, Item o2) {
		if ((o2.getQuantidade() - o1.getQuantidade()) == 0) {
			return o1.getDescricao().compareTo(o2.getDescricao());
		}
		return o2.getQuantidade() - o1.getQuantidade();
	}

}
