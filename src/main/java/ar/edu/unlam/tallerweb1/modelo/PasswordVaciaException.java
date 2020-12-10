package ar.edu.unlam.tallerweb1.modelo;

public class PasswordVaciaException extends Exception {

	public PasswordVaciaException() {
		super("La clave está vacia");
	}
	
}
