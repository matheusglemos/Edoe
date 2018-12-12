package testes;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.edoe.models.Doador;
import com.edoe.models.Item;

class ItemTest {
	
	private Item item;
	private Doador doador;

	@BeforeEach
	void setUp() throws Exception {
		doador = new Doador("Davidson", "davidson@yahoo.com", "88223344", "17", "pessoa_fisica");
		item = new Item(35, "Camisa hugo boss", "tamanho p, 100% algodao", 5, doador);
	}

	@Test
	@DisplayName("teste da saida, toString.")
	void testToString() {
		assertEquals("35 - Camisa hugo boss, tags: [tamanho p,  100% algodao], quantidade: 5", item.toString());
	}
	
	@Test
	@DisplayName("teste da quantidade e descricao.")
	void testQtdDescricao() {
		assertEquals("5-Camisa hugo boss", item.quantidadeDescricao());
	}
	
	@Test
	@DisplayName("teste da quantidade do item no sistema.")
	void testQtdItemNoSistema() {
		assertEquals("35 - Camisa hugo boss, tags: [tamanho p,  100% algodao], quantidade: 5, doador: Davidson/17", item.quantidadeDoItemNoSistema());
	}

}
