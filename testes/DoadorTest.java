
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.edoe.models.Doador;

class DoadorTest {

	Doador doadorUm;
	Doador doadorDois;
	Doador doadorTres;

	@BeforeEach
	void setUp() throws Exception {
		doadorUm = new Doador("Fernando", "fernando@yahoo.com", "(85) 99456-8578", "11725897", "PESSOA_FISICA");
		doadorDois = new Doador("Fundacao Getulio Vargas", "fundacaogetulio@hotmail.com", "(11) 98456-8796", "2547896",
				"ONG");
		doadorTres = new Doador("Fundacao Juscelino Kubitschek", "fundacaojuscelino@hotmail.com", "(15) 3065-8855",
				"2547896", "ONG");
	}

	@Test
	@DisplayName("teste do nome nulo")
	void testNomeNulo() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			new Doador(null, "fernando@yahoo.com", "(85) 99456-8578", "11725897", "PESSOA_FISICA");
		});
		assertEquals("Entrada invalida: nome nao pode ser vazio ou nulo.", iae.getMessage());
	}

	@Test
	@DisplayName("teste do nome vazio")
	void testNomeVazio() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			new Doador("", "fernando@yahoo.com", "(85) 99456-8578", "11725897", "PESSOA_FISICA");
		});
		assertEquals("Entrada invalida: nome nao pode ser vazio ou nulo.", iae.getMessage());
	}

	@Test
	@DisplayName("teste do email nulo")
	void testEmailNulo() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			new Doador("Fernando", null, "(85) 99456-8578", "11725897", "PESSOA_FISICA");
		});
		assertEquals("Entrada invalida: email nao pode ser vazio ou nulo.", iae.getMessage());
	}

	@Test
	@DisplayName("teste do email vazio")
	void testEmailVazio() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			new Doador("Fernando", "", "(85) 99456-8578", "11725897", "PESSOA_FISICA");
		});
		assertEquals("Entrada invalida: email nao pode ser vazio ou nulo.", iae.getMessage());
	}

	@Test
	@DisplayName("teste do celular nulo")
	void testTelefoneNulo() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			new Doador("Fernando", "fernando@yahoo.com", null, "11725897", "PESSOA_FISICA");
		});
		assertEquals("Entrada invalida: celular nao pode ser vazio ou nulo.", iae.getMessage());
	}

	@Test
	@DisplayName("teste do celular vazio")
	void testTelefoneVazio() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			new Doador("Fernando", "fernando@yahoo.com", "", "11725897", "PESSOA_FISICA");
		});
		assertEquals("Entrada invalida: celular nao pode ser vazio ou nulo.", iae.getMessage());
	}

	@Test
	@DisplayName("teste do id nulo")
	void testIdNulo() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			new Doador("Fernando", "fernando@yahoo.com", "(85) 99456-8578", null, "PESSOA_FISICA");
		});
		assertEquals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.", iae.getMessage());
	}

	@Test
	@DisplayName("teste do id vazio")
	void testIdVazio() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			new Doador("Fernando", "fernando@yahoo.com", "(85) 99456-8578", "", "PESSOA_FISICA");
		});
		assertEquals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.", iae.getMessage());
	}

	@Test
	@DisplayName("teste da classe nula")
	void testClasseNula() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			new Doador("Fernando", "fernando@yahoo.com", "(85) 99456-8578", "11725897", null);
		});
		assertEquals("Entrada invalida: classe nao pode ser vazia ou nula.", iae.getMessage());
	}

	@Test
	@DisplayName("teste da classe vazia")
	void testClasseVazia() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			new Doador("Fernando", "fernando@yahoo.com", "(85) 99456-8578", "11725897", "");
		});
		assertEquals("Entrada invalida: classe nao pode ser vazia ou nula.", iae.getMessage());
	}

	@Test
	@DisplayName("teste da classe invalida")
	void testClasseInvalida() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			new Doador("Fernando", "fernando@yahoo.com", "(85) 99456-8578", "11725897", "Empresa");
		});
		assertEquals("Entrada invalida: opcao de classe invalida.", iae.getMessage());
	}

	@Test
	@DisplayName("teste dois usuarios diferentes")
	void testUsuariosDiferentes() {
		assertFalse(doadorUm.equals(doadorDois));
	}

	@Test
	@DisplayName("teste dois usuarios iguais")
	void testUsuariosIguais() {
		assertTrue(doadorDois.equals(doadorTres));
	}

}
