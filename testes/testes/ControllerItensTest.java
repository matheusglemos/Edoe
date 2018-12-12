package testes;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import controllers.ControllerItens;
import controllers.ControllerUsuario;

class ControllerItensTest {
	
	private ControllerItens item;
	private ControllerUsuario usuario;
	private ControllerItens item2;
	private ControllerUsuario usuario2;

	@BeforeEach
	void setUp() throws IOException {
		usuario2 = new ControllerUsuario();
		item = new ControllerItens(usuario2);
		usuario2.lerReceptores("arquivos_sistema/novosReceptores.csv");
		item2 = new ControllerItens(usuario2);
		
		usuario = new ControllerUsuario();
		usuario.adicionaDoador("17", "Bolsolula", "lulabolso@gmail.com", "40028922", "orgao_publico_federal");
		item = new ControllerItens(usuario);
		item.adicionaDescritor("xbox");
		
	}

	@Test
	@DisplayName("teste adiciona descritor.")
	void testAddDescritor() {
		item.adicionaDescritor("playstation 4");
		assertTrue(item.existeDescritor("playstation 4"));
	}
	
	@Test
	@DisplayName("teste adiciona descritor existente.")
	void testAddDescritorExistente() {
		item.adicionaDescritor("playstation 4");
		assertTrue(item.existeDescritor("playstation 4"));
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.adicionaDescritor("playstation 4");
		});
		assertEquals("Descritor de Item ja existente: playstation 4.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste adiciona descritor vazio.")
	void testAddDescritorVazio() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.adicionaDescritor("");
		});
		assertEquals("Entrada invalida: descricao nao pode ser vazia ou nula.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste adiciona descritor nulo.")
	void testAddDescritorNulo() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.adicionaDescritor(null);
		});
		assertEquals("Entrada invalida: descricao nao pode ser vazia ou nula.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste adiciona item.")
	void testAdicionaItem() {
		item.adicionaItemParaDoacao("17", "curso de programacao", 300, "300 vagas poo java");
		assertEquals("1 - curso de programacao, tags: [300 vagas poo java], quantidade: 300", item.exibeItem("17", 1));
	}
		
	
	@Test
	@DisplayName("teste adiciona item vazio.")
	void testAddItemVazio() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.adicionaItemParaDoacao("17", "", 2, "roda grande");
		});
		assertEquals("Entrada invalida: descricao nao pode ser vazia ou nula.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste adiciona item nulo.")
	void testAddItemNulo() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.adicionaItemParaDoacao("17", null, 4, "colchao kingsize,conforto,dormir");
		});
		assertEquals("Entrada invalida: descricao nao pode ser vazia ou nula.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste adiciona item, quantidade zero.")
	void testAddQtdZero() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.adicionaItemParaDoacao("17", "colchao", 0, "colchao kingsize,conforto,dormir");
		});
		assertEquals("Entrada invalida: quantidade deve ser maior que zero.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste adiciona item, quantidade negativa.")
	void testAddQtdNegativa() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.adicionaItemParaDoacao("17", "colchao", -1, "colchao kingsize,conforto,dormir");
		});
		assertEquals("Entrada invalida: quantidade deve ser maior que zero.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste adiciona item, id usuario vazio.")
	void testAddIdUsuarioVazio() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.adicionaItemParaDoacao("", "colchao", 1, "colchao kingsize,conforto,dormir");
		});
		assertEquals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste adiciona item, id usuario nulo.")
	void testAddIdUsuarioNulo() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.adicionaItemParaDoacao(null, "colchao", 1, "colchao kingsize,conforto,dormir");
		});
		assertEquals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste adiciona item, id usuario nao encontrado.")
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
	@DisplayName("teste exibe item, usuario nao encontrado.")
	void testExibeItemUsuarioNaoEncontrado() {
		item.adicionaItemParaDoacao("17", "travesseiro", 500, "pena de cegonha");
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.exibeItem("21", 1);
		});
		assertEquals("Usuario nao encontrado: 21.", iae.getMessage());	
	}
	
	@Test
	@DisplayName("teste exibe item, item nao encontrado.")
	void testExibeItemIdItemNaoEncontrado() {
		item.adicionaItemParaDoacao("17", "travesseiro", 500, "pena de cegonha");
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.exibeItem("17", 4);
		});
		assertEquals("Item nao encontrado: 4.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste atualiza item.")
	void testAtualizaItem() {
		item.adicionaItemParaDoacao("17", "xbox one", 50, "1tb e fifa 2019");
		assertEquals("1 - xbox one, tags: [1tb e fifa 2019], quantidade: 50", item.exibeItem("17", 1));
		item.atualizaItemParaDoacao(1, "17", 30, "xbox one e gta v");
		assertEquals("1 - xbox one, tags: [xbox one e gta v], quantidade: 30", item.exibeItem("17", 1));
	}
	
	@Test
	@DisplayName("teste atualiza item, id do item negativo.")
	void testAtualizaItemIdNegativo() {
		item.adicionaItemParaDoacao("17", "travesseiro", 500, "pena de cegonha");
		assertEquals("1 - travesseiro, tags: [pena de cegonha], quantidade: 500", item.exibeItem("17", 1));
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.atualizaItemParaDoacao(-1, "17", 500, "pluma de ganso");
		});
		assertEquals("Entrada invalida: id do item nao pode ser negativo.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste atualiza item, id do usuario vazio.")
	void testAtualizaItemIdVazio() {
		item.adicionaItemParaDoacao("17", "travesseiro", 500, "pena de cegonha");
		assertEquals("1 - travesseiro, tags: [pena de cegonha], quantidade: 500", item.exibeItem("17", 1));
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.atualizaItemParaDoacao(1, "", 500, "pluma de ganso");
		});
		assertEquals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste atualiza item, id do usuario nulo.")
	void testAtualizaItemIdNulo() {
		item.adicionaItemParaDoacao("17", "travesseiro", 500, "pena de cegonha");
		assertEquals("1 - travesseiro, tags: [pena de cegonha], quantidade: 500", item.exibeItem("17", 1));
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.atualizaItemParaDoacao(1, null, 500, "pluma de ganso");
		});
		assertEquals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste atualiza item, item nao encontrado.")
	void testAtualizaItemNaoEncontrado() {
		item.adicionaItemParaDoacao("17", "travesseiro", 500, "pena de cegonha");
		assertEquals("1 - travesseiro, tags: [pena de cegonha], quantidade: 500", item.exibeItem("17", 1));
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.atualizaItemParaDoacao(5, "17", 500, "pluma de ganso");
		});
		assertEquals("Item nao encontrado: 5.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste item para doacao, item nao encontrado.")
	void testRemoveItemParaDoacaoItemNaoEncontrado() {
		item.adicionaItemParaDoacao("17", "travesseiro", 500, "pena de cegonha");
		assertEquals("1 - travesseiro, tags: [pena de cegonha], quantidade: 500", item.exibeItem("17", 1));
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.removeItemParaDoacao(5, "17");
		});
		assertEquals("Item nao encontrado: 5.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste item para doacao, id negativo.")
	void testRemoveItemParaDoacaoIdNegativo() {
		item.adicionaItemParaDoacao("17", "travesseiro", 500, "pena de cegonha");
		assertEquals("1 - travesseiro, tags: [pena de cegonha], quantidade: 500", item.exibeItem("17", 1));
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.removeItemParaDoacao(-3, "17");
		});
		assertEquals("Entrada invalida: id do item nao pode ser negativo.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste item para doacao, id do usuario vazio.")
	void testRemoveItemParaDoacaoIdUsuarioVazio() {
		item.adicionaItemParaDoacao("17", "travesseiro", 500, "pena de cegonha");
		assertEquals("1 - travesseiro, tags: [pena de cegonha], quantidade: 500", item.exibeItem("17", 1));
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.removeItemParaDoacao(1, "");
		});
		assertEquals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste item para doacao, id do usuario nulo.")
	void testRemoveItemParaDoacaoIdUsuarioNulo() {
		item.adicionaItemParaDoacao("17", "travesseiro", 500, "pena de cegonha");
		assertEquals("1 - travesseiro, tags: [pena de cegonha], quantidade: 500", item.exibeItem("17", 1));
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.removeItemParaDoacao(1, null);
		});
		assertEquals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste item para doacao, usuario nao encontrado.")
	void testRemoveItemParaDoacaoUsuarioNaoEncontrado() {
		item.adicionaItemParaDoacao("17", "travesseiro", 500, "pena de cegonha");
		assertEquals("1 - travesseiro, tags: [pena de cegonha], quantidade: 500", item.exibeItem("17", 1));
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.removeItemParaDoacao(1, "13");
		});
		assertEquals("Usuario nao encontrado: 13.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste lista descritor de item para doacao.")
	void testListaDescritorDeItens() {
		item.adicionaDescritor("Toalha de Banho");
		item.adicionaDescritor("Frauda");
		item.adicionaDescritor("Video Game");
		item.adicionaItemParaDoacao("17", "curso de programacao", 300, "300 vagas poo java");
		assertEquals("300 - curso de programacao | 0 - frauda | 0 - toalha de banho | 0 - video game | 0 - xbox | ", item.listaDescritorDeItensParaDoacao());
	}
	
	@Test
	@DisplayName("teste lista itens para doacao.")
	void testListaItens() {
		item.adicionaItemParaDoacao("17", "curso de programacao", 300, "300 vagas poo java");
		assertEquals("300 - curso de programacao | 0 - xbox | ", item.listaDescritorDeItensParaDoacao());
	}
	
	@Test
	@DisplayName("teste pesquisando itens.")
	void testPesquisaItem() {
		item.adicionaItemParaDoacao("17", "curso de programacao", 300, "300 vagas poo java");
		assertEquals("1 - curso de programacao, tags: [300 vagas poo java], quantidade: 300", item.pesquisaItemParaDoacaoPorDescricao("curso de programacao"));	
	}
	
	@Test
	@DisplayName("teste pesquisa item descricao vazia.")
	void testPesquisaItemDescricaoVazia() {
		item.adicionaItemParaDoacao("17", "travesseiro", 500, "pena de cegonha");
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.pesquisaItemParaDoacaoPorDescricao("");
		});
		assertEquals("Entrada invalida: texto da pesquisa nao pode ser vazio ou nulo.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste pesquisa item descricao nula.")
	void testPesquisaItemDescricaoNula() {
		item.adicionaItemParaDoacao("17", "travesseiro", 500, "pena de cegonha");
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item.pesquisaItemParaDoacaoPorDescricao(null);
		});
		assertEquals("Entrada invalida: texto da pesquisa nao pode ser vazio ou nulo.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste adiciona item necessario.")
	void testAdicionaItemNecessario() {
		item2.adicionaItemNecessario("84473712044", "Toalha de Banho", 2, "Adulto,TAM G,Azul");
		assertEquals("1 - toalha de banho, tags: [Adulto, TAM G, Azul], quantidade: 2, Receptor: Murilo Luiz Brito/84473712044",item2.listaItensNecessarios());
	}
	
	@Test
	@DisplayName("teste adiciona item necessario, descricao vazia.")
	void testAdicionaItemNecessarioDescricaoVazia() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item2.adicionaItemNecessario("84473712044", "", 2, "Adulto,TAM G,Azul");
		});
		assertEquals("Entrada invalida: descricao nao pode ser vazia ou nula.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste adiciona item necessario, descricao nula.")
	void testAdicionaItemNecessarioDescricaoNula() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item2.adicionaItemNecessario("84473712044", null, 2, "Adulto,TAM G,Azul");
		});
		assertEquals("Entrada invalida: descricao nao pode ser vazia ou nula.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste adiciona item necessario, quantidade menor que zero.")
	void testAdicionaItemNecessarioQtdNegativa() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item2.adicionaItemNecessario("84473712044", "Toalha de Banho", -1, "Adulto,TAM G,Azul");
		});
		assertEquals("Entrada invalida: quantidade deve ser maior que zero.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste adiciona item necessario, quantidade igual a zero.")
	void testAdicionaItemNecessarioQtdZero() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item2.adicionaItemNecessario("84473712044", "Toalha de Banho", 0, "Adulto,TAM G,Azul");
		});
		assertEquals("Entrada invalida: quantidade deve ser maior que zero.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste adiciona item necessario, id vazio.")
	void testAdicionaItemNecessarioIdVazio() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item2.adicionaItemNecessario("", "Toalha de Banho", 0, "Adulto,TAM G,Azul");
		});
		assertEquals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste adiciona item necessario, id nulo.")
	void testAdicionaItemNecessarioIdNulo() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item2.adicionaItemNecessario(null, "Toalha de Banho", 0, "Adulto,TAM G,Azul");
		});
		assertEquals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste listagem de itens necessarios.")
	void testListaItemNecessario() {
		item2.adicionaItemNecessario("84473712044", "Toalha de Banho", 2, "Adulto,TAM G,Azul");
		item2.adicionaItemNecessario("31862316040", "Alimento", 5, "Alimentacao,Saude");
		item2.adicionaItemNecessario("24875800037", "Sabonete", 8, "Higiene");
		assertEquals("1 - toalha de banho, tags: [Adulto, TAM G, Azul], quantidade: 2, "
				+ "Receptor: Murilo Luiz Brito/84473712044 | 2 - alimento, tags: [Alimentacao, Saude], quantidade: 5, "
				+ "Receptor: Sonia Daniela/31862316040 | 3 - sabonete, tags: [Higiene], quantidade: 8, "
				+ "Receptor: Sara Jennifer Vieira/24875800037",item2.listaItensNecessarios());
	}
	
	@Test
	@DisplayName("teste atualiza item necessario.")
	void testAtualizaItemNecessario() {
		item2.adicionaItemNecessario("84473712044", "Toalha de Banho", 2, "Adulto,TAM G,Azul");
		assertEquals("1 - toalha de banho, tags: [Adulto, TAM G, Azul], quantidade: 2, Receptor: Murilo Luiz Brito/84473712044",item2.listaItensNecessarios());
		item2.atualizaItemNecessario(1, "84473712044", 8, "Infantil, TAM PP, Rosa");
		assertEquals("1 - toalha de banho, tags: [Infantil,  TAM PP,  Rosa], quantidade: 8, Receptor: Murilo Luiz Brito/84473712044",item2.listaItensNecessarios());
	}
	
	@Test
	@DisplayName("teste atualiza item necessario, usuario nao encontrado.")
	void testAtualizaItemNecessarioUsuarioNaoEncontrado() {
		item2.adicionaItemNecessario("32719454000103", "jaqueta de couro", 3, "outfit,couro de bode");
		assertEquals("1 - jaqueta de couro, tags: [outfit, couro de bode], quantidade: 3, Receptor: Antonella Sonia Moraes/32719454000103",item2.listaItensNecessarios());
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item2.atualizaItemNecessario(1, "454545", 5, "couro de jacaré");
		});
		assertEquals("Usuario nao encontrado: 454545.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste atualiza item necessario, id do item negativo.")
	void testAtualizaItemNecessarioIdNegativo() {
		item2.adicionaItemNecessario("32719454000103", "jaqueta de couro", 3, "outfit,couro de bode");
		assertEquals("1 - jaqueta de couro, tags: [outfit, couro de bode], quantidade: 3, Receptor: Antonella Sonia Moraes/32719454000103",item2.listaItensNecessarios());
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item2.atualizaItemNecessario(-3, "32719454000103", 5, "couro de jacaré");
		});
		assertEquals("Entrada invalida: id do item nao pode ser negativo.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste atualiza item necessario, id do item nao encontrado.")
	void testAtualizaItemNecessarioItemNaoEcnontrado() {
		item2.adicionaItemNecessario("32719454000103", "jaqueta de couro", 3, "outfit,couro de bode");
		assertEquals("1 - jaqueta de couro, tags: [outfit, couro de bode], quantidade: 3, Receptor: Antonella Sonia Moraes/32719454000103",item2.listaItensNecessarios());
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item2.atualizaItemNecessario(150, "32719454000103", 5, "couro de jacaré");
		});
		assertEquals("Item nao encontrado: 150.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste atualiza item necessario, id do usuario vazio.")
	void testAtualizaItemNecessarioIdVazio() {
		item2.adicionaItemNecessario("32719454000103", "jaqueta de couro", 3, "outfit,couro de bode");
		assertEquals("1 - jaqueta de couro, tags: [outfit, couro de bode], quantidade: 3, Receptor: Antonella Sonia Moraes/32719454000103",item2.listaItensNecessarios());
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item2.atualizaItemNecessario(1, "", 5, "couro de jacaré");
		});
		assertEquals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste atualiza item necessario, id do usuario nulo.")
	void testAtualizaItemNecessarioIdNulo() {
		item2.adicionaItemNecessario("32719454000103", "jaqueta de couro", 3, "outfit,couro de bode");
		assertEquals("1 - jaqueta de couro, tags: [outfit, couro de bode], quantidade: 3, Receptor: Antonella Sonia Moraes/32719454000103",item2.listaItensNecessarios());
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item2.atualizaItemNecessario(1, null, 5, "couro de jacaré");
		});
		assertEquals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste remove item necessario, item nao encontrado.")
	void testRemoveItemNecessarioItemNaoEcontrado() {
		item2.adicionaItemNecessario("32719454000103", "jaqueta de couro", 3, "outfit,couro de bode");
		assertEquals("1 - jaqueta de couro, tags: [outfit, couro de bode], quantidade: 3, Receptor: Antonella Sonia Moraes/32719454000103",item2.listaItensNecessarios());
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item2.removeItemNecessario("32719454000103", 50);
		});
		assertEquals("Item nao encontrado: 50.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste remove item necessario, usuario nao encontrado.")
	void testRemoveItemNecessarioUsuarioNaoEcontrado() {
		item2.adicionaItemNecessario("32719454000103", "jaqueta de couro", 3, "outfit,couro de bode");
		assertEquals("1 - jaqueta de couro, tags: [outfit, couro de bode], quantidade: 3, Receptor: Antonella Sonia Moraes/32719454000103",item2.listaItensNecessarios());
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item2.removeItemNecessario("12121212121", 1);
		});
		assertEquals("Usuario nao encontrado: 12121212121.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste remove item necessario, id negativo.")
	void testRemoveItemNecessarioIdNegativo() {
		item2.adicionaItemNecessario("32719454000103", "jaqueta de couro", 3, "outfit,couro de bode");
		assertEquals("1 - jaqueta de couro, tags: [outfit, couro de bode], quantidade: 3, Receptor: Antonella Sonia Moraes/32719454000103",item2.listaItensNecessarios());
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item2.removeItemNecessario("32719454000103", -2);
		});
		assertEquals("Entrada invalida: id do item nao pode ser negativo.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste remove item necessario, id do usuario vazio.")
	void testRemoveItemNecessarioIdVazio() {
		item2.adicionaItemNecessario("32719454000103", "jaqueta de couro", 3, "outfit,couro de bode");
		assertEquals("1 - jaqueta de couro, tags: [outfit, couro de bode], quantidade: 3, Receptor: Antonella Sonia Moraes/32719454000103",item2.listaItensNecessarios());
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item2.removeItemNecessario("", 1);
		});
		assertEquals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste remove item necessario, id do usuario nulo.")
	void testRemoveItemNecessarioIdNulo() {
		item2.adicionaItemNecessario("32719454000103", "jaqueta de couro", 3, "outfit,couro de bode");
		assertEquals("1 - jaqueta de couro, tags: [outfit, couro de bode], quantidade: 3, Receptor: Antonella Sonia Moraes/32719454000103",item2.listaItensNecessarios());
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			item2.removeItemNecessario(null, 1);
		});
		assertEquals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.", iae.getMessage());
	}
	
	
}
