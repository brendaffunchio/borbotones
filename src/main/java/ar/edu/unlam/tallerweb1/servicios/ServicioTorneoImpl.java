package ar.edu.unlam.tallerweb1.servicios;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTorneo;

@Service
public class ServicioTorneoImpl implements ServicioTorneo{

	
	
//	@Override
//	public void agregarParticipanteAlTorneo(Usuario participante) {
//		
//		List <Usuario> listaParticipantes= new LinkedList<Usuario>();
//		
//		listaParticipantes.add(participante);
//				
//		
//	}

	@Autowired
	public RepositorioTorneo repositorioTorneo;
	
	@Override
	public List<Torneo> mostrarTorneos() {
		
		
		return repositorioTorneo.torneos();
	}

}
