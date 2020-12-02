package ar.edu.unlam.tallerweb1.servicios;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.CupoExcedidoException;
import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.InmuebleInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.ParticipanteDuplicadoException;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
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
	public ServicioTorneoImpl(RepositorioTorneo repositorioTorneo
			,RepositorioUsuario repositorioUsuario
			,RepositorioInmueble repositorioInmueble) {

		this.repositorioTorneo = repositorioTorneo;
		this.repositorioUsuario = repositorioUsuario;
		this.repositorioInmueble = repositorioInmueble;
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
			distancia=6371.137*Math.acos(Math.cos(direccionUsuario.getLatitud())
					*Math.cos(direccionTorneo.getLatitud())
					*Math.cos(direccionTorneo.getLongitud() - direccionUsuario.getLongitud())
					+ Math.sin(direccionUsuario.getLatitud())*Math.sin(direccionTorneo.getLatitud()));
			
			distancia = redondearDecimales(distancia, 2);
			
			torneo.setDistanciaConUsuario(distancia);
		    repositorioTorneo.modificarTorneo(torneo);
		}

		
		return torneos;
          
		
		
	}


	@Override
	public void guardarTorneo(Torneo torneo, Long creadorId, Long inmuebleId) throws InmuebleInexistenteException {

		
		Usuario creador = repositorioUsuario.consultarUsuarioPorId(creadorId);
		
		if (inmuebleId!=null) {
			Inmueble inmueble = repositorioInmueble.consultarInmueblePorId(inmuebleId);
			torneo.setEstadoCompleto(false);
			torneo.setInscriptos(0);
			torneo.setCreador(creador);
			torneo.setInmuebleDelTorneo(inmueble);
			
			repositorioTorneo.guardarTorneo(torneo, creadorId, inmuebleId);
			
		}else {
			
			throw new InmuebleInexistenteException();
		}
		
		
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
	public void agregarParticipante(Long torneoId, Long usuarioId) throws ParticipanteDuplicadoException
	, CupoExcedidoException {

		Torneo torneo = repositorioTorneo.consultarTorneoPorId(torneoId);
		Usuario participante = repositorioUsuario.consultarUsuarioPorId(usuarioId);
		
		if (torneo.getParticipantes().contains(participante)) {
			
			throw new ParticipanteDuplicadoException();
		}
		
		if (torneo.getCupo() > torneo.getInscriptos()) {
			torneo.agregarParticipante(participante);
			participante.participarEnTorneo(torneo);
			repositorioTorneo.modificarTorneo(torneo);
			repositorioUsuario.modificarUsuario(participante);
			
		} else if (torneo.getInscriptos().equals(torneo.getCupo())) {
			torneo.setEstadoCompleto(true);
			repositorioTorneo.modificarTorneo(torneo);

			
		} else if(torneo.getInscriptos()>torneo.getCupo()) {
			
			throw new CupoExcedidoException();
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

	@Override
	public List<Torneo> ordenarTorneosSegunDistancia() {
		
		return repositorioTorneo.ordenarTorneosSegunDistancia();
	}
	
	private Double redondearDecimales(Double valorInicial, Integer numeroDecimales) {
	        Double parteEntera, resultado;
	        resultado = valorInicial;
	        parteEntera = Math.floor(resultado);
	        resultado=(resultado-parteEntera)*Math.pow(10, numeroDecimales);
	        resultado= (double) Math.round(resultado);
	        resultado=(resultado/Math.pow(10, numeroDecimales))+parteEntera;
	        return resultado;
	    }
}
