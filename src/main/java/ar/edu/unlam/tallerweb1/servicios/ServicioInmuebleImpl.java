package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioInmueble;

@Service
@Transactional
public class ServicioInmuebleImpl implements ServicioInmueble{


	private RepositorioInmueble repositorioInmueble;
	
	
	@Autowired
	public ServicioInmuebleImpl(RepositorioInmueble repositorioInmueble) {
		
		this.repositorioInmueble = repositorioInmueble;
	}

	@Override
	public List<Inmueble> mostrarInmuebles() {
		
				
		return repositorioInmueble.todosLosInmuebles();
	}

	@Override
	public void guardarInmueble(Inmueble inmueble) {
		
	repositorioInmueble.guardarInmueble(inmueble);
	
	}

}
