package comparators;

import java.util.Comparator;

import com.edoe.models.Doacao;

/**
 * Classe responsavel por implementar o comparator da classe Doacao, com
 * objetivo de ordenar os itens pela sua data.
 * 
 * @author Matheus Gusmao
 * @author Davidson Guedes
 * @author Caroliny Silva
 * @author Almir Crispiniano
 */
public class OrdenarDoacaoPelaData implements Comparator<Doacao> {

	/**
	 * Metodo que compara duas doacoes por sua data.
	 */
	@Override
	public int compare(Doacao o1, Doacao o2) {
		if (o1.getData().compareTo(o2.getData()) > 0) {
			return 1;
		} else if (o1.getData().compareTo(o2.getData()) < 0) {
			return -1;
		} else {
			if (o1.getItemDoado().getDescricao().compareTo(o2.getItemDoado().getDescricao()) > 0) {
				return 1;
			} else {
				return -1;
			}
		}
	}

}
