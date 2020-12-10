package ar.edu.unlam.tallerweb1.servicios;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ar.edu.unlam.tallerweb1.modelo.CupoExcedidoException;
import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.FotoInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.GanadorYaExistenteException;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.InmuebleInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.ParticipanteDuplicadoException;
import ar.edu.unlam.tallerweb1.modelo.ParticipanteInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.TorneoInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.UsuarioInexistenteException;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioInmueble;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTorneo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

@Service
@Transactional
public class ServicioTorneoImpl implements ServicioTorneo {

	public RepositorioTorneo repositorioTorneo;
	public RepositorioUsuario repositorioUsuario;
	public RepositorioInmueble repositorioInmueble;

	@Autowired
	public ServicioTorneoImpl(RepositorioTorneo repositorioTorneo, RepositorioUsuario repositorioUsuario,
			RepositorioInmueble repositorioInmueble) {

		this.repositorioTorneo = repositorioTorneo;
		this.repositorioUsuario = repositorioUsuario;
		this.repositorioInmueble = repositorioInmueble;
	}

	@Override
	public List<Torneo> mostrarTorneosConDistancia(Long usuarioId) {
		List<Torneo> torneos = repositorioTorneo.torneos();

		Usuario usuario = repositorioUsuario.consultarUsuarioPorId(usuarioId);
		Direccion direccionUsuario = usuario.getDireccion();

		for (Torneo torneo : torneos) {
			Inmueble inmueble = torneo.getInmuebleDelTorneo();
			Direccion direccionTorneo = inmueble.getDireccion();

			Double distancia = torneo.getDistanciaConUsuario();
			distancia = 6371.137
					* Math.acos(Math.cos(direccionUsuario.getLatitud()) * Math.cos(direccionTorneo.getLatitud())
							* Math.cos(direccionTorneo.getLongitud() - direccionUsuario.getLongitud())
							+ Math.sin(direccionUsuario.getLatitud()) * Math.sin(direccionTorneo.getLatitud()));

			distancia = redondearDecimales(distancia, 2);

			torneo.setDistanciaConUsuario(distancia);
			repositorioTorneo.modificarTorneo(torneo);
		}

		return torneos;

	}

	@Override
	public void guardarTorneo(Torneo torneo, Long creadorId, Long inmuebleId) throws InmuebleInexistenteException, UsuarioInexistenteException {
        if(inmuebleId == null) throw new InmuebleInexistenteException();
		
        Inmueble inmueble = repositorioInmueble.consultarInmueblePorId(inmuebleId);
		if (inmueble == null)
			throw new InmuebleInexistenteException();

		Usuario creador = repositorioUsuario.consultarUsuarioPorId(creadorId);

		if (creador == null)
			throw new UsuarioInexistenteException();

		
		torneo.setEstadoCompleto(false);
		torneo.setInscriptos(0);
		torneo.setCreador(creador);
		torneo.setInmuebleDelTorneo(inmueble);

		repositorioTorneo.guardarTorneo(torneo, creadorId, inmuebleId);

	}

	@Override
	public List<Torneo> buscarTorneo(String categoria, String juego) {

		return repositorioTorneo.buscarTorneo(categoria, juego);

	}

	@Override
	public Torneo verDetallesTorneo(Long id) {

		return repositorioTorneo.consultarTorneoPorId(id);

	}

	@Override
	public void agregarParticipante(Long torneoId, Long usuarioId) throws ParticipanteDuplicadoException,
			CupoExcedidoException, TorneoInexistenteException, UsuarioInexistenteException {
		Torneo torneo = repositorioTorneo.consultarTorneoPorId(torneoId);
		if (torneo == null)
			throw new TorneoInexistenteException();
		Usuario usuario = repositorioUsuario.consultarUsuarioPorId(usuarioId);
		if (usuario == null)
			throw new UsuarioInexistenteException();

		if (torneo.getParticipantes().contains(usuario)) {

			throw new ParticipanteDuplicadoException();

		}
		if (torneo.getCupo() > torneo.getInscriptos()) {
			torneo.agregarParticipante(usuario);
			usuario.participarEnTorneo(torneo);
			repositorioTorneo.modificarTorneo(torneo);
			repositorioUsuario.modificarUsuario(usuario);

		} else {
			throw new CupoExcedidoException();
		}

		if (torneo.getInscriptos().equals(torneo.getCupo())) {
			torneo.setEstadoCompleto(true);
			repositorioTorneo.modificarTorneo(torneo);

		}

	}

	@Override
	public void eliminarParticipante(Long torneoId, Long usuarioId)
			throws ParticipanteInexistenteException, TorneoInexistenteException, UsuarioInexistenteException {
		Torneo torneo = repositorioTorneo.consultarTorneoPorId(torneoId);
		if (torneo == null)
			throw new TorneoInexistenteException();

		Usuario participante = repositorioUsuario.consultarUsuarioPorId(usuarioId);
		if (participante == null)
			throw new UsuarioInexistenteException();

		if (!torneo.getParticipantes().contains(participante)) {

			throw new ParticipanteInexistenteException();

		}

		if (!participante.getTorneosParticipa().contains(torneo)) {

			throw new TorneoInexistenteException();

		}

		if (torneo.getParticipantes().contains(participante) && participante.getTorneosParticipa().contains(torneo)) {
			torneo.eliminarParticipante(participante);
			participante.desuscribirseDeTorneo(torneo);
			repositorioTorneo.modificarTorneo(torneo);
			;
			repositorioUsuario.modificarUsuario(participante);

			if (torneo.getInscriptos() < torneo.getCupo()) {
				torneo.setEstadoCompleto(false);
				repositorioTorneo.modificarTorneo(torneo);

			}
		}
	}

	@Override
	public Set<Usuario> mostrarParticipantesDelTorneo(Long torneoId) {
		return repositorioTorneo.mostrarParticipantesDelTorneo(torneoId);
	}

	@Override
	public void elegirGanador(Long torneoId, Long ganadorId)
			throws GanadorYaExistenteException, TorneoInexistenteException, UsuarioInexistenteException {
		Torneo torneo = repositorioTorneo.consultarTorneoPorId(torneoId);
		if (torneo == null)
			throw new TorneoInexistenteException();

		Usuario ganador = repositorioUsuario.consultarUsuarioPorId(ganadorId);
		if (ganador == null)
			throw new UsuarioInexistenteException();

		Integer torGanados = ganador.getTorGanados();

		if (torneo.getGanador() == null) {

			torGanados++;
			ganador.setTorGanados(torGanados);
			torneo.setGanador(ganador);
			repositorioTorneo.modificarTorneo(torneo);
			repositorioUsuario.modificarUsuario(ganador);

		} else {
			throw new GanadorYaExistenteException();
		}

	}

	@Override
	public Torneo consultarTorneoPorId(Long torneoId) {

		return repositorioTorneo.consultarTorneoPorId(torneoId);
	}

	@Override
	public List<Torneo> mostrarTorneos() {

		return repositorioTorneo.torneos();
	}

	@Override
	public List<Torneo> ordenarTorneosSegunDistancia() {

		return repositorioTorneo.ordenarTorneosSegunDistancia();
	}

	private Double redondearDecimales(Double valorInicial, Integer numeroDecimales) {
		Double parteEntera, resultado;
		resultado = valorInicial;
		parteEntera = Math.floor(resultado);
		resultado = (resultado - parteEntera) * Math.pow(10, numeroDecimales);
		resultado = (double) Math.round(resultado);
		resultado = (resultado / Math.pow(10, numeroDecimales)) + parteEntera;
		return resultado;
	}

	@Override
	public List<Torneo> filtrarTorneosPorDistancia(Double desdeKm, Double hastaKm) {


		return repositorioTorneo.filtrarTorneosPorDistancia(desdeKm,hastaKm);
	}

}
