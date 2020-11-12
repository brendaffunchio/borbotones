package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface RepositorioTorneo {

	
	
	List <Torneo>torneos();

	void guardarTorneo(Torneo torneo);

	List<Torneo> buscarTorneo(String categoria, String juego);

	Torneo verDetallesTorneo(Long id);

	void agregarParticipante(Long torneoId, Long usuarioId);

	void eliminarParticipante(Long torneoId, Long usuarioId);

}
