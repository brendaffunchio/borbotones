package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;
import java.util.Set;

import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface RepositorioTorneo {

	
	
	public List <Torneo>torneos();

	public Boolean guardarTorneo(Torneo torneo, Long creadorId, Long inmuebleId);

	public List<Torneo> buscarTorneo(String categoria, String juego);

	public Torneo verDetallesTorneo(Long id);

	public void agregarParticipante(Long torneoId, Long usuarioId);

	public void eliminarParticipante(Long torneoId, Long usuarioId);

	public Set<Usuario> mostrarParticipantesDelTorneo(Long torneoId);

	public void elegirGanador(Long ganadorId, Long torneoGanadoId);

	

	

}
