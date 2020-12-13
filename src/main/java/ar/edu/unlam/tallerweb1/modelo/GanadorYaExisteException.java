package ar.edu.unlam.tallerweb1.modelo;

public class GanadorYaExisteException extends Exception {

	public GanadorYaExisteException() {
		super("El torneo ya contiene un ganador");
		
	}

}
