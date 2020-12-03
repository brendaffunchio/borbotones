package ar.edu.unlam.tallerweb1.servicios;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;

import ar.edu.unlam.tallerweb1.modelo.CupoExcedidoException;
import ar.edu.unlam.tallerweb1.modelo.FotoInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.GanadorYaExistenteException;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.ParticipanteDuplicadoException;
import ar.edu.unlam.tallerweb1.modelo.ParticipanteInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.TorneoInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import net.bytebuddy.dynamic.scaffold.MethodGraph.Linked;

public interface ServicioTorneo {

	
	public List<Torneo> mostrarTorneosConDistancia(Long usuarioId);
	
	public List<Torneo> mostrarTorneos();
	
	public void guardarTorneo(Torneo torneo, Long creadorId, Long inmuebleId);

	public List <Torneo> buscarTorneo(String categoria, String juego);

	public Torneo verDetallesTorneo(Long id);
	
	void agregarParticipante(Torneo torneo, Usuario usuario) throws ParticipanteDuplicadoException, CupoExcedidoException;

	public void eliminarParticipante(Torneo torneo, Usuario usuario) throws ParticipanteInexistenteException, TorneoInexistenteException;

	public Set<Usuario> mostrarParticipantesDelTorneo(Long torneoId);

	public void elegirGanador(Torneo torneo, Usuario ganador) throws GanadorYaExistenteException;
	
	public Torneo consultarTorneoPorId(Long torneoId);
	public List<Torneo> ordenarTorneosSegunDistancia();
}
