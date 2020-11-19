package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import net.bytebuddy.dynamic.scaffold.MethodGraph.Linked;

public interface ServicioTorneo {

	
	public List<Torneo> mostrarTorneos();

	public Boolean guardarTorneo(Torneo torneo, Long creadorId, Long inmuebleId);

	public List <Torneo> buscarTorneo(String categoria, String juego);

	public Torneo verDetallesTorneo(Long id);
	
	void agregarParticipante(Long torneoId, Long usuarioId);

	public void eliminarParticipante(Long torneoId, Long usuarioId);

	

}
