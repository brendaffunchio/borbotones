package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface RepositorioTorneo {

	
	
	public List <Torneo>torneos();

	void guardarTorneo(Torneo torneo, Long creadorId, Long inmuebleId);

	public List<Torneo> buscarTorneo(String categoria, String juego);

	public Torneo verDetallesTorneo(Long id);

	public void agregarParticipante(Long torneoId, Long usuarioId);

	public void eliminarParticipante(Long torneoId, Long usuarioId);

	

	

}
