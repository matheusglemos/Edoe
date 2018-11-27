import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import controllers.ControllerUsuario;

class ControllerUsuarioTest {
	
	private ControllerUsuario c;
	
	@BeforeEach
	void test() {
		c = new ControllerUsuario();
		c.adicionaDoador("2", "Rick", "rick@gmail.com", "87979222", "PESSOA_FISICA");
	}
	
	@Test
	@DisplayName("teste adiciona doador")
	void testAddDoador() {
		c.adicionaDoador("1", "Rick", "rick@gmail.com", "87979222", "PESSOA_FISICA");
		assertTrue(c.existeUsuario("1"));
	}
	
	@Test
	@DisplayName("teste adiciona doador ja existente")
	void testAddDoadorExistente() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			c.adicionaDoador("2", "Rick", "rick@gmail.com", "87979222", "PESSOA_FISICA");
		});
		assertEquals("Usuario ja existente: 2.", iae.getMessage());
		
	}
	
	@Test
	@DisplayName("teste exibe usuario")
	void testeExibeUsuario() {
		c.adicionaDoador("1", "Rick", "rick@gmail.com", "87979222", "PESSOA_FISICA");
		assertTrue(c.existeUsuario("1"));
		assertTrue(c.existeUsuario("2"));
		assertFalse(c.existeUsuario("5"));
	}
	
	@Test
	@DisplayName("teste pesquisa usuario nome vazio")
	void testPesquisaUsuarioNomeVazio() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			c.pesquisaUsuarioPorNome("");
		});
		assertEquals("Entrada invalida: nome nao pode ser vazio ou nulo.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste pesquisa usuario nome nulo")
	void testPesquisaUsuarioNomeNulo() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			c.pesquisaUsuarioPorNome(null);
		});
		assertEquals("Entrada invalida: nome nao pode ser vazio ou nulo.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste pesquisa usuario nome nao encontrado")
	void testPesquisaUsuarioNomeNaoEncontrado() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			c.pesquisaUsuarioPorNome("Davidson");
		});
		assertEquals("Usuario nao encontrado: Davidson.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste pesquisa usuario id vazio")
	void testPesquisaUsuarioIDVazio() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			c.pesquisaUsuarioPorId("");
		});
		assertEquals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste pesquisa usuario id nulo")
	void testPesquisaUsuarioIDNulo() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			c.pesquisaUsuarioPorId(null);
		});
		assertEquals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste pesquisa usuario id nao encontrado")
	void testPesquisaUsuarioIDNaoEncontrado() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			c.pesquisaUsuarioPorId("8");
		});
		assertEquals("Usuario nao encontrado: 8.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste atualiza usuario id vazio")
	void testAtualizaUsuarioIDVazio() {
		c.adicionaDoador("90", "Igor", "igor@gmail.com", "88111848", "ONG");
		assertTrue(c.existeUsuario("90"));
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			c.atualizaUsuario("", "Pereira", "pereira@gmail.com", "88842556");
		});
		assertEquals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste atualiza usuario id nulo")
	void testAtualizaUsuarioIDNulo() {
		c.adicionaDoador("41", "Jonathan", "Jonathan@gmail.com", "88653244", "ONG");
		assertTrue(c.existeUsuario("41"));
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			c.atualizaUsuario(null, "Pereira", "pereira@gmail.com", "88842556");
		});
		assertEquals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste atualiza usuario id nao encontrado")
	void testAtualizaUsuarioIDNaoEncontrado() {
		c.adicionaDoador("80", "Paulo", "paulo@gmail.com", "81864626", "PESSOA_FISICA");
		assertTrue(c.existeUsuario("80"));
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			c.atualizaUsuario("51", "Pereira", "pereira@gmail.com", "88842556");
		});
		assertEquals("Usuario nao encontrado: 51.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste remove usuario id vazio")
	void testRemoveUsuarioIDVazio() {
		c.adicionaDoador("80", "Paulo", "paulo@gmail.com", "81864626", "PESSOA_FISICA");
		assertTrue(c.existeUsuario("80"));
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			c.removeUsuario("");
		});
		assertEquals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste remove usuario id nulo")
	void testRemoveUsuarioIDNulo() {
		c.adicionaDoador("80", "Paulo", "paulo@gmail.com", "81864626", "PESSOA_FISICA");
		assertTrue(c.existeUsuario("80"));
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			c.removeUsuario(null);
		});
		assertEquals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste remove usuario id nao encontrado")
	void testRemoveUsuarioIDNaoEncontrado() {
		c.adicionaDoador("80", "Paulo", "paulo@gmail.com", "81864626", "PESSOA_FISICA");
		assertTrue(c.existeUsuario("80"));
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			c.removeUsuario("99");
		});
		assertEquals("Usuario nao encontrado: 99.", iae.getMessage());
	}
}