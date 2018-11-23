package comparators;

import java.util.Comparator;

import com.edoe.models.Item;

/**
 * 
 *
 */
public class DescricaoItemOrdemAlfabetica implements Comparator<Item> {
	/**
	 * 
	 */
	@Override
	public int compare(Item o1, Item o2) {
		return o1.getDescricao().compareToIgnoreCase(o2.getDescricao());
	}

}
