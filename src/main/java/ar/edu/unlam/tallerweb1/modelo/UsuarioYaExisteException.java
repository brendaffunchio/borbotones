package ar.edu.unlam.tallerweb1.modelo;

public class UsuarioYaExisteException extends Exception {

	public UsuarioYaExisteException() {
	super("Ya existe un usuario registrado con el email ingresado");
}
	}
