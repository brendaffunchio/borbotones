package ar.edu.unlam.tallerweb1.modelo;

public class ParticipanteInexistenteException extends Exception {

	public ParticipanteInexistenteException() {
		super("El participante seleccionado no se encuentra inscripto en el torneo");
		
	}

}
