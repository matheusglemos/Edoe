
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import controllers.ControllerItens;
import controllers.ControllerUsuario;

class ControllerItensTest {
	
	private ControllerItens item;
	private ControllerUsuario usuario;

	@BeforeEach
	void setUp() {
		usuario = new ControllerUsuario();
		item = new ControllerItens(usuario);
		item.adicionaDescritor("xbox");
		usuario.adicionaDoador("17", "Bolsolula", "lulabolso@gmail.com", "40028922", "orgao_publico_federal");
		
	}

	@Test
	@DisplayName("teste adiciona descritor")
	void testAddDescritor() {
		item.adicionaDescritor("playstation 4");
		assertTrue(item.existeDescritor("playstation 4"));
	}
	
	@Test
	@DisplayName("teste adiciona descritor existente")
	void testAddDescritorExistente() {
		item.adicionaDescritor("playstation 4");
		assertTrue(item.existeDescritor("playstation 4"));
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.adicionaDescritor("playstation 4");
		});
		assertEquals("Descritor de Item ja existente: playstation 4.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste adiciona descritor vazio")
	void testAddDescritorVazio() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.adicionaDescritor("");
		});
		assertEquals("Entrada invalida: descricao nao pode ser vazia ou nula.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste adiciona descritor nulo")
	void testAddDescritorNulo() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.adicionaDescritor(null);
		});
		assertEquals("Entrada invalida: descricao nao pode ser vazia ou nula.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste adiciona item")
	void testAdicionaItem() {
		item.adicionaItemParaDoacao("17", "curso de programacao", 300, "300 vagas poo java");
		assertEquals("1 - curso de programacao, tags: [300 vagas poo java], quantidade: 300", item.exibeItem("17", 1));
	}
		
	
	@Test
	@DisplayName("teste adiciona item vazio")
	void testAddItemVazio() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.adicionaItemParaDoacao("17", "", 2, "roda grande");
		});
		assertEquals("Entrada invalida: descricao nao pode ser vazia ou nula.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste adiciona item nulo")
	void testAddItemNulo() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.adicionaItemParaDoacao("17", null, 4, "colchao kingsize,conforto,dormir");
		});
		assertEquals("Entrada invalida: descricao nao pode ser vazia ou nula.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste adiciona item, quantidade zero")
	void testAddQtdZero() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.adicionaItemParaDoacao("17", "colchao", 0, "colchao kingsize,conforto,dormir");
		});
		assertEquals("Entrada invalida: quantidade deve ser maior que zero.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste adiciona item, quantidade negativa")
	void testAddQtdNegativa() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.adicionaItemParaDoacao("17", "colchao", -1, "colchao kingsize,conforto,dormir");
		});
		assertEquals("Entrada invalida: quantidade deve ser maior que zero.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste adiciona item, id usuario vazio")
	void testAddIdUsuarioVazio() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.adicionaItemParaDoacao("", "colchao", 1, "colchao kingsize,conforto,dormir");
		});
		assertEquals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste adiciona item, id usuario nulo")
	void testAddIdUsuarioNulo() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.adicionaItemParaDoacao(null, "colchao", 1, "colchao kingsize,conforto,dormir");
		});
		assertEquals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste adiciona item, id usuario nao encontrado")
	void testAddIdUsuarioNaoEncontrato() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.adicionaItemParaDoacao("42323", "colchao", 1, "colchao kingsize,conforto,dormir");
		});
		assertEquals("Usuario nao encontrado: 42323.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste exibe item")
	void testExibeItem() {
		item.adicionaItemParaDoacao("17", "travesseiro", 500, "pena de cegonha");
		assertEquals("1 - travesseiro, tags: [pena de cegonha], quantidade: 500", item.exibeItem("17", 1));
		
	}
	
	@Test
	@DisplayName("teste exibe item, usuario nao encontrado")
	void testExibeItemUsuarioNaoEncontrado() {
		item.adicionaItemParaDoacao("17", "travesseiro", 500, "pena de cegonha");
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.exibeItem("21", 1);
		});
		assertEquals("Usuario nao encontrado: 21.", iae.getMessage());	
	}
	
	@Test
	@DisplayName("teste exibe item, item nao encontrado")
	void testExibeItemIdItemNaoEncontrado() {
		item.adicionaItemParaDoacao("17", "travesseiro", 500, "pena de cegonha");
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.exibeItem("17", 4);
		});
		assertEquals("Item nao encontrado: 4.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste atualiza item")
	void testAtualizaItem() {
		item.adicionaItemParaDoacao("17", "xbox one", 50, "1tb e fifa 2019");
		assertEquals("1 - xbox one, tags: [1tb e fifa 2019], quantidade: 50", item.exibeItem("17", 1));
		item.atualizaItemParaDoacao(1, "17", 30, "xbox one e gta v");
		assertEquals("1 - xbox one, tags: [xbox one e gta v], quantidade: 30", item.exibeItem("17", 1));
	}
	
	@Test
	@DisplayName("teste atualiza item, id do item negativo")
	void testAtualizaItemIdNegativo() {
		item.adicionaItemParaDoacao("17", "travesseiro", 500, "pena de cegonha");
		assertEquals("1 - travesseiro, tags: [pena de cegonha], quantidade: 500", item.exibeItem("17", 1));
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.atualizaItemParaDoacao(-1, "17", 500, "pluma de ganso");
		});
		assertEquals("Entrada invalida: id do item nao pode ser negativo.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste atualiza item, id do usuario vazio")
	void testAtualizaItemIdVazio() {
		item.adicionaItemParaDoacao("17", "travesseiro", 500, "pena de cegonha");
		assertEquals("1 - travesseiro, tags: [pena de cegonha], quantidade: 500", item.exibeItem("17", 1));
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.atualizaItemParaDoacao(1, "", 500, "pluma de ganso");
		});
		assertEquals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste atualiza item, id do usuario nulo")
	void testAtualizaItemIdNulo() {
		item.adicionaItemParaDoacao("17", "travesseiro", 500, "pena de cegonha");
		assertEquals("1 - travesseiro, tags: [pena de cegonha], quantidade: 500", item.exibeItem("17", 1));
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.atualizaItemParaDoacao(1, null, 500, "pluma de ganso");
		});
		assertEquals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste atualiza item, item nao encontrado")
	void testAtualizaItemNaoEncontrado() {
		item.adicionaItemParaDoacao("17", "travesseiro", 500, "pena de cegonha");
		assertEquals("1 - travesseiro, tags: [pena de cegonha], quantidade: 500", item.exibeItem("17", 1));
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.atualizaItemParaDoacao(5, "17", 500, "pluma de ganso");
		});
		assertEquals("Item nao encontrado: 5.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste item para doacao, item nao encontrado")
	void testRemoveItemParaDoacaoItemNaoEncontrado() {
		item.adicionaItemParaDoacao("17", "travesseiro", 500, "pena de cegonha");
		assertEquals("1 - travesseiro, tags: [pena de cegonha], quantidade: 500", item.exibeItem("17", 1));
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.removeItemParaDoacao(5, "17");
		});
		assertEquals("Item nao encontrado: 5.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste item para doacao, id negativo")
	void testRemoveItemParaDoacaoIdNegativo() {
		item.adicionaItemParaDoacao("17", "travesseiro", 500, "pena de cegonha");
		assertEquals("1 - travesseiro, tags: [pena de cegonha], quantidade: 500", item.exibeItem("17", 1));
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.removeItemParaDoacao(-3, "17");
		});
		assertEquals("Entrada invalida: id do item nao pode ser negativo.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste item para doacao, id do usuario vazio")
	void testRemoveItemParaDoacaoIdUsuarioVazio() {
		item.adicionaItemParaDoacao("17", "travesseiro", 500, "pena de cegonha");
		assertEquals("1 - travesseiro, tags: [pena de cegonha], quantidade: 500", item.exibeItem("17", 1));
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.removeItemParaDoacao(1, "");
		});
		assertEquals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste item para doacao, id do usuario nulo")
	void testRemoveItemParaDoacaoIdUsuarioNulo() {
		item.adicionaItemParaDoacao("17", "travesseiro", 500, "pena de cegonha");
		assertEquals("1 - travesseiro, tags: [pena de cegonha], quantidade: 500", item.exibeItem("17", 1));
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.removeItemParaDoacao(1, null);
		});
		assertEquals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste item para doacao, usuario nao encontrado")
	void testRemoveItemParaDoacaoUsuarioNaoEncontrado() {
		item.adicionaItemParaDoacao("17", "travesseiro", 500, "pena de cegonha");
		assertEquals("1 - travesseiro, tags: [pena de cegonha], quantidade: 500", item.exibeItem("17", 1));
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.removeItemParaDoacao(1, "13");
		});
		assertEquals("Usuario nao encontrado: 13.", iae.getMessage());
	}
	
}
