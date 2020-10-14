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
public class ServicioTorneoImpl implements ServicioTorneo{


	@Autowired
	public RepositorioTorneo repositorioTorneo;
	
	@Override
	public List<Torneo> mostrarTorneos() {
		
		
		return repositorioTorneo.torneos();
	}

	@Override
	public void guardarTorneo(Torneo torneo) {
		
		repositorioTorneo.guardarTorneo(torneo);
		
	}

	

}
