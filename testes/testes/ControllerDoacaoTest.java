package testes;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.text.ParseException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import controllers.ControllerDoacao;
import controllers.ControllerItens;
import controllers.ControllerUsuario;

import excecoes.DataInvalidaException;
import excecoes.DescitorJaExistenteException;
import excecoes.DescricaoInvalidaException;
import excecoes.IdInvalidoException;
import excecoes.ItemNaoEncontradoException;
import excecoes.ItensIguaisException;
import excecoes.QuantidadeInvalidaException;
import excecoes.UsuarioDeveSerReceptorException;
import excecoes.UsuarioJaExistenteException;
import excecoes.UsuarioNaoEncontradoException;
import excecoes.UsuarioNaoPossuiItensCadastradosException;

class ControllerDoacaoTest {
	
	private ControllerDoacao doador;
	private ControllerUsuario usuario;
	private ControllerItens item;
	
	@BeforeEach
	void setUp() throws IOException, UsuarioJaExistenteException, DescricaoInvalidaException, IdInvalidoException, QuantidadeInvalidaException, UsuarioNaoEncontradoException, UsuarioDeveSerReceptorException, DescitorJaExistenteException {
		usuario = new ControllerUsuario();
		item = new ControllerItens(usuario);
		doador = new ControllerDoacao(item);
		usuario.lerReceptores("arquivos_sistema/novosReceptores.csv");
		usuario.adicionaDoador("17", "Davidson", "davidson@outlook.com", "81864626", "pessoa_fisica");
		
		item.adicionaDescritor("video game");
		item.adicionaItemNecessario("57091431030", "video game", 2, "ultima geracao, dois controles");
		item.adicionaItemParaDoacao("17", "video game", 5, "ultima geracao, dois controles");
		
	}

	@Test
	@DisplayName("teste realizando uma doacao.")
	void testRealizaDoacao() throws ParseException, DataInvalidaException, IdInvalidoException, ItensIguaisException, ItemNaoEncontradoException, UsuarioNaoEncontradoException, UsuarioNaoPossuiItensCadastradosException, UsuarioDeveSerReceptorException, DescricaoInvalidaException, DescitorJaExistenteException {
		assertEquals("13/12/2018 - doador: Davidson/17, item: video game, quantidade: 2, receptor: Lucca Iago/57091431030", doador.realizaDoacao(1, 1, "13/12/2018").toString());
	}
	
	@Test
	@DisplayName("teste realizando uma doacao, id do item negativo.")
	void testRealizaDoacaoIdNegativo() throws ParseException, DataInvalidaException, IdInvalidoException, ItensIguaisException, ItemNaoEncontradoException, UsuarioNaoEncontradoException, UsuarioNaoPossuiItensCadastradosException, UsuarioDeveSerReceptorException, DescricaoInvalidaException, DescitorJaExistenteException {
		IdInvalidoException iae = assertThrows(IdInvalidoException.class, () -> {
			doador.realizaDoacao(-1, 1, "29/06/1999");
		});
		assertEquals("Entrada invalida: id do item nao pode ser negativo.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste realizando uma doacao, item nao encontrado.")
	void testRealizaDoacaoItemNaoEncontrado() throws ParseException, DataInvalidaException, IdInvalidoException, ItensIguaisException, ItemNaoEncontradoException, UsuarioNaoEncontradoException, UsuarioNaoPossuiItensCadastradosException, UsuarioDeveSerReceptorException, DescricaoInvalidaException, DescitorJaExistenteException {
		ItemNaoEncontradoException iae = assertThrows(ItemNaoEncontradoException.class, () -> {
			doador.realizaDoacao(199, 1, "29/06/1999");
		});
		assertEquals("Item nao encontrado: 199.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste realizando uma doacao, descricoes diferentes.")
	void testRealizaDoacaoDescricoesDiferentes() throws ParseException, DataInvalidaException, IdInvalidoException, ItensIguaisException, ItemNaoEncontradoException, UsuarioNaoEncontradoException, UsuarioNaoPossuiItensCadastradosException, UsuarioDeveSerReceptorException, DescricaoInvalidaException, DescitorJaExistenteException {
		ItensIguaisException iae = assertThrows(ItensIguaisException.class, () -> {
			item.adicionaDescritor("cesta basica");
			item.adicionaItemParaDoacao("17", "toalhas", 5, "para criancas");
			doador.realizaDoacao(1, 2, "29/06/1999");
		});
		assertEquals("Os itens nao tem descricoes iguais.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste realizando uma doacao, data vazia.")
	void testRealizaDoacaoDataVazia() throws ParseException, DataInvalidaException, IdInvalidoException, ItensIguaisException, ItemNaoEncontradoException, UsuarioNaoEncontradoException, UsuarioNaoPossuiItensCadastradosException, UsuarioDeveSerReceptorException, DescricaoInvalidaException, DescitorJaExistenteException {
		DataInvalidaException iae = assertThrows(DataInvalidaException.class, () -> {
			doador.realizaDoacao(1, 1, "");
		});
		assertEquals("Entrada invalida: data nao pode ser vazia ou nula.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste realizando uma doacao, data nula.")
	void testRealizaDoacaoDataNula() throws ParseException, DataInvalidaException, IdInvalidoException, ItensIguaisException, ItemNaoEncontradoException, UsuarioNaoEncontradoException, UsuarioNaoPossuiItensCadastradosException, UsuarioDeveSerReceptorException, DescricaoInvalidaException, DescitorJaExistenteException {
		DataInvalidaException iae = assertThrows(DataInvalidaException.class, () -> {
			doador.realizaDoacao(1, 1, null);
		});
		assertEquals("Entrada invalida: data nao pode ser vazia ou nula.", iae.getMessage());
	}
	
	@Test
	@DisplayName("teste realizando listagem de doacoes.")
	void testListaDoaces() throws ParseException, DataInvalidaException, IdInvalidoException, ItensIguaisException, ItemNaoEncontradoException, UsuarioNaoEncontradoException, UsuarioNaoPossuiItensCadastradosException, UsuarioDeveSerReceptorException, DescricaoInvalidaException, DescitorJaExistenteException, QuantidadeInvalidaException {
		doador.realizaDoacao(1, 1, "27/12/2005");
		assertEquals("27/12/2005 - doador: Davidson/17, item: video game, quantidade: 2, receptor: Lucca Iago/57091431030", doador.listaDoacoes());
	}
	
	@Test
	@DisplayName("teste exibindo um item.")
	void testExibeItem() throws ParseException, DataInvalidaException, IdInvalidoException, ItensIguaisException, ItemNaoEncontradoException, UsuarioNaoEncontradoException, UsuarioNaoPossuiItensCadastradosException, UsuarioDeveSerReceptorException, DescricaoInvalidaException, DescitorJaExistenteException, QuantidadeInvalidaException {
		assertEquals("1 - video game, tags: [ultima geracao,  dois controles], quantidade: 5", item.exibeItem("17", 1));
	}
	
	@Test
	@DisplayName("teste exibindo um item.")
	void testExibeItemNaoEncontrado() throws ParseException, DataInvalidaException, IdInvalidoException, ItensIguaisException, ItemNaoEncontradoException, UsuarioNaoEncontradoException, UsuarioNaoPossuiItensCadastradosException, UsuarioDeveSerReceptorException, DescricaoInvalidaException, DescitorJaExistenteException, QuantidadeInvalidaException {
		ItemNaoEncontradoException iae = assertThrows(ItemNaoEncontradoException.class, () -> {
			item.exibeItem("17", 25);
		});
		assertEquals("Item nao encontrado: 25.", iae.getMessage());
	}
}
