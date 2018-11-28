package comparators;

import java.util.Comparator;

import com.edoe.models.Item;

/**
 * Classe responsavel por implementar o comparator da classe item, 
 * com objetivo de ordenar os itens pela sua descrição
 *
 * @author Matheus Gusmao
 * @author Davidson Guedes
 * @author Caroliny Silva
 * @author Almir Crispiniano
 */
public class DescricaoItemOrdemAlfabetica implements Comparator<Item> {
	/**
	 * Metodo que compara dois itens pela sua descrição 
	 */
	@Override
	public int compare(Item o1, Item o2) {
		return o1.getDescricao().compareToIgnoreCase(o2.getDescricao());
	}

}
