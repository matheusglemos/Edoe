package comparators;

import java.util.Comparator;

import com.edoe.models.ItemNecessario;

/**
 * Classe responsavel por implementar o comparator da classe itens necessarios,
 * com objetivo de ordenar os itens pelo seu id.
 * 
 * @author Matheus Gusmao
 * @author Davidson Guedes
 * @author Caroliny Silva
 * @author Almir Crispiniano
 */
public class OrdemIdItemNecessario implements Comparator<ItemNecessario> {
	/**
	 * Metodo que compara dois itens atraves de seu id.
	 */
	@Override
	public int compare(ItemNecessario o1, ItemNecessario o2) {
		return o2.getItemNecId() - o1.getItemNecId();
	}

}
