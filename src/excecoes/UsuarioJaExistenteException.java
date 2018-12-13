package excecoes;

public class UsuarioJaExistenteException extends Exception {
	
	public UsuarioJaExistenteException(String mensagem) {
		super(mensagem);
	}

}
