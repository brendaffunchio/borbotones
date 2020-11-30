package ar.edu.unlam.tallerweb1.servicios;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTorneo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

@Service
@Transactional
public class ServicioTorneoImpl implements ServicioTorneo {

	public RepositorioTorneo repositorioTorneo;
	public RepositorioUsuario repositorioUsuario;

	@Autowired
	public ServicioTorneoImpl(RepositorioTorneo repositorioTorneo,RepositorioUsuario repositorioUsuario) {

		this.repositorioTorneo = repositorioTorneo;
		this.repositorioUsuario = repositorioUsuario;
	}

	@Override
	public List<Torneo> mostrarTorneosConDistancia(Long usuarioId) {
       List<Torneo> torneos=repositorioTorneo.torneos();
       
       Usuario usuario = repositorioUsuario.consultarUsuarioPorId(usuarioId);
		Direccion direccionUsuario = usuario.getDireccion();
		
		for (Torneo torneo:torneos) {
			Inmueble inmueble=torneo.getInmuebleDelTorneo();
			Direccion direccionTorneo = inmueble.getDireccion();
			Double distancia = torneo.getDistanciaConUsuario();
			distancia= -1*(6371*Math.asin(Math.cos(direccionUsuario.getLatitud())
		    		*Math.cos(direccionTorneo.getLatitud())+Math.sin(direccionUsuario.getLatitud())
		    		*Math.sin(direccionTorneo.getLatitud())
		    		-Math.cos(direccionUsuario.getLongitud()-direccionTorneo.getLongitud())));
		    
		    torneo.setDistanciaConUsuario(distancia);
		    repositorioTorneo.modificarTorneo(torneo);
		}

		
		return torneos;
          
	}

	@Override
	public void guardarTorneo(Torneo torneo, Long creadorId, Long inmuebleId) {

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
	public void agregarParticipante(Long torneoId, Long usuarioId) {

		Torneo torneo = repositorioTorneo.consultarTorneoPorId(torneoId);
		Usuario participante = repositorioUsuario.consultarUsuarioPorId(usuarioId);
		
		if (!torneo.equals(null)&&!participante.equals(null)
				&& torneo.getCupo() > torneo.getInscriptos()) {
			torneo.agregarParticipante(participante);
			participante.participarEnTorneo(torneo);
			repositorioTorneo.modificarTorneo(torneo);
			repositorioUsuario.modificarUsuario(participante);
			
		}
		
		if (torneo.getInscriptos() >= torneo.getCupo()) {
			torneo.setEstadoCompleto(true);
			repositorioTorneo.modificarTorneo(torneo);

			
		}
		
	}

	@Override
	public void eliminarParticipante(Long torneoId, Long usuarioId) {
		Torneo torneo = repositorioTorneo.consultarTorneoPorId(torneoId);
		Usuario participante = repositorioUsuario.consultarUsuarioPorId(usuarioId);
		
		if (!torneo.equals(null)&&!participante.equals(null)){
			torneo.eliminarParticipante(participante);
			participante.desuscribirseDeTorneo(torneo);
			repositorioTorneo.modificarTorneo(torneo);;
			repositorioUsuario.modificarUsuario(participante);
		}
		if (torneo.getInscriptos() < torneo.getCupo()) {
			torneo.setEstadoCompleto(false);
			repositorioTorneo.modificarTorneo(torneo);

		}
		
	}

	@Override
	public Set<Usuario> mostrarParticipantesDelTorneo(Long torneoId) {
		return repositorioTorneo.mostrarParticipantesDelTorneo(torneoId);
	}

	@Override
	public void elegirGanador(Long ganadorId, Long torneoGanadoId) {
		Torneo torneo = repositorioTorneo.consultarTorneoPorId(torneoGanadoId);
		Usuario ganador = repositorioUsuario.consultarUsuarioPorId(ganadorId);
		Integer torGanados= ganador.getTorGanados();
		if (!torneo.equals(null)&&!ganador.equals(null)){
		torGanados++;
		ganador.setTorGanados(torGanados);
		torneo.setGanador(ganador);
		repositorioTorneo.modificarTorneo(torneo);
		repositorioUsuario.modificarUsuario(ganador);
	
		}
		
	}

	
		
		/*=6371*ACOS(COS(RADIANES(90-A6))*COS(RADIANES(90-C6))+SENO(RADIANES(90-
				A6))*SENO(RADIANES(90-C6))*COS(RADIANES(B6-D6)))
			*/
	

	@Override
	public Torneo consultarTorneoPorId(Long torneoId) {
		
		return repositorioTorneo.consultarTorneoPorId(torneoId);
	}

	@Override
	public List<Torneo> mostrarTorneos() {
		
		return repositorioTorneo.torneos();
	}
	

}
