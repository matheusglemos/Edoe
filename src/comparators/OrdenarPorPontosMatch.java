package comparators;

import java.util.Comparator;

import com.edoe.models.Item;

/**
 * Classe responsavel por implementar o comparator para o metodo match, com
 * objetivo de retorna os itens a serem doados que pontuarem nesse processo,
 * ordenados da maior para a menor pontuação.
 * 
 * @author Matheus Gusmao
 * @author Davidson Guedes
 * @author Caroliny Silva
 * @author Almir Crispiniano
 */
public class OrdenarPorPontosMatch implements Comparator<Item> {
	/**
	 * Metodo que compara a pontuação de dois itens, se os dois itens tiverem uma
	 * pontuação maior que 0 retorna 1 se tiverem uma pontuação menor que 0 retorna
	 * -1 se não o mesmo retorna a pontuacao pelo id do item
	 */
	@Override
	public int compare(Item o1, Item o2) {
		if (o1.getPontos() - o2.getPontos() > 0) {
			return -1;
		} else if (o1.getPontos() - o2.getPontos() < 0) {
			return 1;
		} else {
			return o1.getidItem() - o2.getidItem();
		}
	}

}
