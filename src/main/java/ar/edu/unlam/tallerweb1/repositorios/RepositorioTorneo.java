package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;
import java.util.Set;

import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface RepositorioTorneo {

	
	
	public List <Torneo>listarTodosLosTorneos();

	public void guardarTorneo(Torneo torneo);

	public List<Torneo> buscarTorneo(String categoria, String juego);

	public void modificarTorneo(Torneo torneo);
	
	public Torneo consultarTorneoPorId(Long torneoId);

	public List<Torneo> ordenarTorneosSegunDistancia();

	public List<Torneo> filtrarTorneosPorDistancia(Double desdeKm, Double hastaKm);

	

}
