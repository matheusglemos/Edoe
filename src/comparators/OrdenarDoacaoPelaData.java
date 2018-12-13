package comparators;

import java.util.Comparator;

import com.edoe.models.Doacao;

public class OrdenarDoacaoPelaData implements Comparator<Doacao> {

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
