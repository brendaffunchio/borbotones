package ar.edu.unlam.tallerweb1.modelo;

public class TorneoInexistenteException extends Exception {

	public TorneoInexistenteException() {
		super("No existe el torneo");
		
	}

}
