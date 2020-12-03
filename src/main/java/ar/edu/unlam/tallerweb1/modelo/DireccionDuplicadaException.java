package ar.edu.unlam.tallerweb1.modelo;

public class DireccionDuplicadaException extends Exception {

	public DireccionDuplicadaException() {
		super("Ya existe un inmueble con la misma dirección");
	
	}

}
