package testes;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import controllers.ControllerUsuario;
import excecoes.IdInvalidoException;
import excecoes.NomeInvalidoException;
import excecoes.UsuarioJaExistenteException;
import excecoes.UsuarioNaoEncontradoException;

class ControllerUsuarioTest {

	private ControllerUsuario c;

	@BeforeEach
	void setUp() throws UsuarioJaExistenteException {
		c = new ControllerUsuario();
		c.adicionaDoador("2", "Rick", "rick@gmail.com", "87979222", "PESSOA_FISICA");
	}

	@Test
	@DisplayName("teste adiciona doador")
	void testAddDoador() throws UsuarioJaExistenteException {
		c.adicionaDoador("1", "Rick", "rick@gmail.com", "87979222", "PESSOA_FISICA");
		assertTrue(c.existeUsuario("1"));
	}

	@Test
	@DisplayName("teste adiciona doador ja existente")
	void testAddDoadorExistente() {
		UsuarioJaExistenteException iae = assertThrows(UsuarioJaExistenteException.class, () -> {
			c.adicionaDoador("2", "Rick", "rick@gmail.com", "87979222", "PESSOA_FISICA");
		});
		assertEquals("Usuario ja existente: 2.", iae.getMessage());

	}

	@Test
	@DisplayName("teste existe usuario")
	void testExisteUsuario() throws UsuarioJaExistenteException {
		c.adicionaDoador("1", "Rick", "rick@gmail.com", "87979222", "PESSOA_FISICA");
		assertTrue(c.existeUsuario("1"));
		assertTrue(c.existeUsuario("2"));
		assertFalse(c.existeUsuario("5"));
	}

	@Test
	@DisplayName("teste pesquisa usuario por nome")
	void testPesquisaUsuarioNome() throws UsuarioJaExistenteException, UsuarioNaoEncontradoException, NomeInvalidoException {
		c.adicionaDoador("104", "Davidson", "davidson@gmail.com", "87979222", "PESSOA_FISICA");
		assertEquals("Davidson/104, davidson@gmail.com, 87979222, status: doador",
				c.pesquisaUsuarioPorNome("Davidson"));
	}

	@Test
	@DisplayName("teste pesquisa usuario nome vazio")
	void testPesquisaUsuarioNomeVazio() {
		NomeInvalidoException iae = assertThrows(NomeInvalidoException.class, () -> {
			c.pesquisaUsuarioPorNome("");
		});
		assertEquals("Entrada invalida: nome nao pode ser vazio ou nulo.", iae.getMessage());
	}

	@Test
	@DisplayName("teste pesquisa usuario nome nulo")
	void testPesquisaUsuarioNomeNulo() {
		NomeInvalidoException iae = assertThrows(NomeInvalidoException.class, () -> {
			c.pesquisaUsuarioPorNome(null);
		});
		assertEquals("Entrada invalida: nome nao pode ser vazio ou nulo.", iae.getMessage());
	}

	@Test
	@DisplayName("teste pesquisa usuario nome nao encontrado")
	void testPesquisaUsuarioNomeNaoEncontrado() {
		UsuarioNaoEncontradoException iae = assertThrows(UsuarioNaoEncontradoException.class, () -> {
			c.pesquisaUsuarioPorNome("Davidson");
		});
		assertEquals("Usuario nao encontrado: Davidson.", iae.getMessage());
	}

	@Test
	@DisplayName("teste pesquisa usuario por ID")
	void testPesquisaUsuarioID() throws UsuarioJaExistenteException, UsuarioNaoEncontradoException, NomeInvalidoException {
		c.adicionaDoador("104", "Davidson", "davidson@gmail.com", "87979222", "PESSOA_FISICA");
		assertEquals("Davidson/104, davidson@gmail.com, 87979222, status: doador", c.pesquisaUsuarioPorNome("Davidson"));
	}

	@Test
	@DisplayName("teste pesquisa usuario id vazio")
	void testPesquisaUsuarioIDVazio() {
		IdInvalidoException iae = assertThrows(IdInvalidoException.class, () -> {
			c.pesquisaUsuarioPorId("");
		});
		assertEquals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.", iae.getMessage());
	}

	@Test
	@DisplayName("teste pesquisa usuario id nulo")
	void testPesquisaUsuarioIDNulo() {
		IdInvalidoException iae = assertThrows(IdInvalidoException.class, () -> {
			c.pesquisaUsuarioPorId(null);
		});
		assertEquals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.", iae.getMessage());
	}

	@Test
	@DisplayName("teste pesquisa usuario id nao encontrado")
	void testPesquisaUsuarioIDNaoEncontrado() {
		UsuarioNaoEncontradoException iae = assertThrows(UsuarioNaoEncontradoException.class, () -> {
			c.pesquisaUsuarioPorId("8");
		});
		assertEquals("Usuario nao encontrado: 8.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste atualiza usuario")
	void testAtualizaUsuario() throws UsuarioJaExistenteException, IdInvalidoException, UsuarioNaoEncontradoException, NomeInvalidoException {
		c.adicionaDoador("80", "Paulo", "paulo@gmail.com", "81864626", "PESSOA_FISICA");
		assertTrue(c.existeUsuario("80"));
		c.atualizaUsuario("80", "Cayan", "cayan@outlook.com", "88445577");
		assertEquals("Cayan/80, cayan@outlook.com, 88445577, status: doador", c.pesquisaUsuarioPorNome("Cayan"));
	}

	@Test
	@DisplayName("teste atualiza usuario id vazio")
	void testAtualizaUsuarioIDVazio() throws UsuarioJaExistenteException {
		c.adicionaDoador("90", "Igor", "igor@gmail.com", "88111848", "ONG");
		assertTrue(c.existeUsuario("90"));
		IdInvalidoException iae = assertThrows(IdInvalidoException.class, () -> {
			c.atualizaUsuario("", "Pereira", "pereira@gmail.com", "88842556");
		});
		assertEquals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.", iae.getMessage());
	}

	@Test
	@DisplayName("teste atualiza usuario id nulo")
	void testAtualizaUsuarioIDNulo() throws UsuarioJaExistenteException {
		c.adicionaDoador("41", "Jonathan", "Jonathan@gmail.com", "88653244", "ONG");
		assertTrue(c.existeUsuario("41"));
		IdInvalidoException iae = assertThrows(IdInvalidoException.class, () -> {
			c.atualizaUsuario(null, "Pereira", "pereira@gmail.com", "88842556");
		});
		assertEquals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.", iae.getMessage());
	}

	@Test
	@DisplayName("teste atualiza usuario id nao encontrado")
	void testAtualizaUsuarioIDNaoEncontrado() throws UsuarioJaExistenteException {
		c.adicionaDoador("80", "Paulo", "paulo@gmail.com", "81864626", "PESSOA_FISICA");
		assertTrue(c.existeUsuario("80"));
		UsuarioNaoEncontradoException iae = assertThrows(UsuarioNaoEncontradoException.class, () -> {
			c.atualizaUsuario("51", "Pereira", "pereira@gmail.com", "88842556");
		});
		assertEquals("Usuario nao encontrado: 51.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste remove usuario")
	void testRemoveUsuario() throws UsuarioJaExistenteException, IdInvalidoException, UsuarioNaoEncontradoException {
		c.adicionaDoador("80", "Paulo", "paulo@gmail.com", "81864626", "PESSOA_FISICA");
		assertTrue(c.existeUsuario("80"));
		c.removeUsuario("80");
		assertFalse(c.existeUsuario("80"));
	}

	@Test
	@DisplayName("teste remove usuario id vazio")
	void testRemoveUsuarioIDVazio() throws UsuarioJaExistenteException {
		c.adicionaDoador("80", "Paulo", "paulo@gmail.com", "81864626", "PESSOA_FISICA");
		assertTrue(c.existeUsuario("80"));
		IdInvalidoException iae = assertThrows(IdInvalidoException.class, () -> {
			c.removeUsuario("");
		});
		assertEquals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.", iae.getMessage());
	}

	@Test
	@DisplayName("teste remove usuario id nulo")
	void testRemoveUsuarioIDNulo() throws UsuarioJaExistenteException {
		c.adicionaDoador("80", "Paulo", "paulo@gmail.com", "81864626", "PESSOA_FISICA");
		assertTrue(c.existeUsuario("80"));
		IdInvalidoException iae = assertThrows(IdInvalidoException.class, () -> {
			c.removeUsuario(null);
		});
		assertEquals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.", iae.getMessage());
	}

	@Test
	@DisplayName("teste remove usuario id nao encontrado")
	void testRemoveUsuarioIDNaoEncontrado() throws UsuarioJaExistenteException {
		c.adicionaDoador("80", "Paulo", "paulo@gmail.com", "81864626", "PESSOA_FISICA");
		assertTrue(c.existeUsuario("80"));
		UsuarioNaoEncontradoException iae = assertThrows(UsuarioNaoEncontradoException.class, () -> {
			c.removeUsuario("99");
		});
		assertEquals("Usuario nao encontrado: 99.", iae.getMessage());
	}
}