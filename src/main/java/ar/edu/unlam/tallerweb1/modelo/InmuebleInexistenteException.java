package ar.edu.unlam.tallerweb1.modelo;

public class InmuebleInexistenteException extends Exception {

	
	public InmuebleInexistenteException() {
		super("No existe el inmueble");
	}
}
