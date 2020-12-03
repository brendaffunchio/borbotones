package ar.edu.unlam.tallerweb1.modelo;

public class GanadorYaExistenteException extends Exception {

	public GanadorYaExistenteException() {
		super("El torneo ya contiene un ganador");
		
	}

}
