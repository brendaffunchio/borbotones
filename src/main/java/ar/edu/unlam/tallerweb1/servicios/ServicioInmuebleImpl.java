package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioInmueble;

@Service
@Transactional
public class ServicioInmuebleImpl implements ServicioInmueble {

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
	public void guardarInmueble(Inmueble inmueble,Direccion direccion) {

		repositorioInmueble.guardarInmueble(inmueble,direccion);

	}

	@Override
	public List<Inmueble> buscarInmueble(String nombreProvincia, String nombreCiudad) {

		return repositorioInmueble.buscarInmueble(nombreProvincia, nombreCiudad);

	}

	@Override

	public Inmueble verDetallesInmueble(Long inmuebleId) {
		
		return repositorioInmueble.verDetallesInmueble(inmuebleId);
		
		
	}

	@Override
	public void agregarInquilino(Long inmuebleId, Long usuarioId) {
		
		repositorioInmueble.agregarInquilino(inmuebleId,usuarioId);
		
	}

}
