package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface RepositorioTorneo {

	
	
	List <Torneo>torneos();

	void guardarTorneo(Torneo torneo);

	void guardarParticipante(Usuario usuario);

	List<Torneo> buscarTorneo(String categoria, String juego);

	Torneo verDetallesTorneo(Long id);


}
