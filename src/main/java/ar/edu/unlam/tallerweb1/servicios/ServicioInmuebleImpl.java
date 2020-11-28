package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioInmueble;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

@Service
@Transactional
public class ServicioInmuebleImpl implements ServicioInmueble {

	private RepositorioInmueble repositorioInmueble;
	private RepositorioUsuario repositorioUsuario;

	@Autowired
	public ServicioInmuebleImpl(RepositorioInmueble repositorioInmueble,
			RepositorioUsuario repositorioUsuario) {

		this.repositorioInmueble = repositorioInmueble;
		this.repositorioUsuario = repositorioUsuario;
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
	public List<Inmueble> buscarInmueble(String provinciaId, String nombreCiudad) {

		return repositorioInmueble.buscarInmueble(provinciaId, nombreCiudad);

	}

	@Override

	public Inmueble verDetallesInmueble(Long inmuebleId) {
		
		return repositorioInmueble.verDetallesInmueble(inmuebleId);
		
		
	}

	@Override
	public void agregarInquilino(Long inmuebleId, Long usuarioId) {
		
		Inmueble inmueble=repositorioInmueble.consultarInmueblePorId(inmuebleId);
		Usuario inquilino = repositorioUsuario.consultarUsuarioPorId(usuarioId);
		
		if (!inmueble.equals(null)&&!inquilino.equals(null)
				&&inmueble.getDisponible().equals(true)){
			inmueble.setInquilino(inquilino);
			inmueble.setDisponible(false);
			repositorioInmueble.modificarInmueble(inmueble);
			
			
		}
		
		
	}

}
