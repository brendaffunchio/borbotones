package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import java.util.Set;

import ar.edu.unlam.tallerweb1.modelo.CupoExcedidoException;
import ar.edu.unlam.tallerweb1.modelo.GanadorYaExisteException;
import ar.edu.unlam.tallerweb1.modelo.InmuebleInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.ParticipanteDuplicadoException;
import ar.edu.unlam.tallerweb1.modelo.ParticipanteInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.TorneoInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.UsuarioInexistenteException;


public interface ServicioTorneo {

	public void guardarTorneo(Torneo torneo, Long creadorId, Long inmuebleId) throws 
	InmuebleInexistenteException, UsuarioInexistenteException;
	
	public List<Torneo> listarTorneosConDistancia(Long usuarioId);
	
	public List<Torneo> listarTodosLosTorneos();

	public List <Torneo> buscarTorneo(String categoria, String juego);

	public Torneo verDetallesTorneo(Long id);
	
	public void agregarParticipante(Long torneoId, Long usuarioId) throws ParticipanteDuplicadoException, CupoExcedidoException, TorneoInexistenteException, UsuarioInexistenteException;

	public void eliminarParticipante(Long torneoId,Long usuarioid) throws ParticipanteInexistenteException, TorneoInexistenteException, UsuarioInexistenteException;

	public Set<Usuario> listarParticipantesDelTorneo(Long torneoId);

	public void elegirGanador(Long torneoId, Long ganadorId) throws GanadorYaExisteException, TorneoInexistenteException, UsuarioInexistenteException;
	
	public Torneo consultarTorneoPorId(Long torneoId);
	
	public List<Torneo> ordenarTorneosSegunDistancia();

	public List<Torneo> filtrarTorneosPorDistancia(Double desdeKm, Double hastaKm);
}
