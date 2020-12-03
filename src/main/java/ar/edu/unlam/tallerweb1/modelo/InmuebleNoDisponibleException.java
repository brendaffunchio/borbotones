package ar.edu.unlam.tallerweb1.modelo;

public class InmuebleNoDisponibleException extends Exception {

	public InmuebleNoDisponibleException() {
		super("El inmueble no se encuentra disponible para alquilar");
		
	}

	
	
}
