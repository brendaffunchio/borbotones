package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioTorneo {

	// public void agregarParticipanteAlTorneo(Usuario participante);
	
	public List<Torneo> mostrarTorneos();
}
