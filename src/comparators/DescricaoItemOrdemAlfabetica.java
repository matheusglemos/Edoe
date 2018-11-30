package comparators;

import java.util.Comparator;

import com.edoe.models.Item;

/**
 * Classe responsavel por implementar o comparator
 *
 * @author Matheus Gusmao
 * @author Davidson Guedes
 * @author Caroliny Silva
 * @author Almir Crispiniano
 */
public class DescricaoItemOrdemAlfabetica implements Comparator<String> {
	/**
	 * Metodo que compara duas strings
	 */
	@Override
	public int compare(String o1, String o2) {
		return o1.compareTo(o2);
	}

}
