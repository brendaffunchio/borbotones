package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioInmueble;

@Service
public class ServicioInmuebleImpl implements ServicioInmueble{

@Autowired
	private RepositorioInmueble respositorioInmueble;
	
	@Override
	public List<Inmueble> mostrarInmuebles() {
		
				
		return respositorioInmueble.todosLosInmuebles();
	}

}
