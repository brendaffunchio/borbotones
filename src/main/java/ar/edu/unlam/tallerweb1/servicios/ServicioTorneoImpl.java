package ar.edu.unlam.tallerweb1.servicios;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTorneo;

@Service
@Transactional
public class ServicioTorneoImpl implements ServicioTorneo {

	public RepositorioTorneo repositorioTorneo;

	@Autowired
	public ServicioTorneoImpl(RepositorioTorneo repositorioTorneo) {

		this.repositorioTorneo = repositorioTorneo;
	}

	@Override
	public List<Torneo> mostrarTorneos() {
       /* Torneo torneo= repositorioTorneo.verTorneo();
        List <Torneo> torneos= repositorioTorneo.torneos();
          
      if (torneo.getCupo()>=torneo.getInscriptos());
        	  
        	  return torneos;*/
		return repositorioTorneo.torneos();
          
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
		
		return repositorioTorneo.verDetallesTorneo(id);
		
	
	}

	@Override
	public void agregarParticipante(Long torneoId, Long usuarioId) {

		repositorioTorneo.agregarParticipante(torneoId, usuarioId);
	}

	@Override
	public void eliminarParticipante(Long torneoId, Long usuarioId) {
		repositorioTorneo.eliminarParticipante(torneoId,usuarioId); 
		
	}

}
