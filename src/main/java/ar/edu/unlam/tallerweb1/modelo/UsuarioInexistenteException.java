package ar.edu.unlam.tallerweb1.modelo;

public class UsuarioInexistenteException extends Exception {

	public UsuarioInexistenteException() {
		super("No existe el usuario");
		
	}

	
}
