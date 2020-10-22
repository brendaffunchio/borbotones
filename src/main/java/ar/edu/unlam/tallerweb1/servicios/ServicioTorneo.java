package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import net.bytebuddy.dynamic.scaffold.MethodGraph.Linked;

public interface ServicioTorneo {

	
	public List<Torneo> mostrarTorneos();

	public void guardarTorneo(Torneo torneo);

	public void guardarParticipante(Usuario usuario);

	public List <Torneo> buscarTorneoPorJuego(String juego);

	public List <Torneo> buscarTorneoPorCategoria(String categoria);

}
