package ar.edu.unlam.tallerweb1.modelo;

public class CupoExcedidoException extends Exception {

	public CupoExcedidoException() {
		super("Cupo del torneo excedido, no se puede participar");
		
	}

}
