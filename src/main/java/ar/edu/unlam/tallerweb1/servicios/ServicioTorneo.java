package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import java.util.Set;

import ar.edu.unlam.tallerweb1.modelo.CupoExcedidoException;
import ar.edu.unlam.tallerweb1.modelo.InmuebleInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.ParticipanteDuplicadoException;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import net.bytebuddy.dynamic.scaffold.MethodGraph.Linked;

public interface ServicioTorneo {

	
	public List<Torneo> mostrarTorneosConDistancia(Long usuarioId);
	public List<Torneo> mostrarTorneos();

	public void guardarTorneo(Torneo torneo, Long creadorId, Long inmuebleId) throws InmuebleInexistenteException;

	public List <Torneo> buscarTorneo(String categoria, String juego);

	public Torneo verDetallesTorneo(Long id);
	
	void agregarParticipante(Long torneoId, Long usuarioId) throws ParticipanteDuplicadoException, CupoExcedidoException;

	public void eliminarParticipante(Long torneoId, Long usuarioId);

	public Set<Usuario> mostrarParticipantesDelTorneo(Long torneoId);

	public void elegirGanador(Long ganadorId, Long torneoGanadoId);
	
	public Torneo consultarTorneoPorId(Long torneoId);
	public List<Torneo> ordenarTorneosSegunDistancia();
}
