package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.DireccionDuplicadaException;

import ar.edu.unlam.tallerweb1.modelo.DireccionNoValidaException;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.InmuebleInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.InmuebleNoDisponibleException;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.UsuarioInexistenteException;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioInmueble;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

@Service
@Transactional
public class ServicioInmuebleImpl implements ServicioInmueble {

	private RepositorioInmueble repositorioInmueble;
	private RepositorioUsuario repositorioUsuario;
	

	@Autowired
	public ServicioInmuebleImpl(RepositorioInmueble repositorioInmueble, RepositorioUsuario repositorioUsuario) {

		this.repositorioInmueble = repositorioInmueble;
		this.repositorioUsuario = repositorioUsuario;
	
	
	}

	@Override
	public List<Inmueble> listarTodosLosInmueblesDisponibles() {

		return repositorioInmueble.listarTodosLosInmueblesDisponibles();
	}

	
	@Override
	public void guardarInmueble(Inmueble inmueble, Direccion direccion)
			throws DireccionDuplicadaException, DireccionNoValidaException {
		if (direccion == null) throw new DireccionNoValidaException();
		
		for(Inmueble aux:repositorioInmueble.listarTodosLosInmuebles()) {
		
	     if (aux.getDireccion().equals(direccion)) {
	    	 
	    	 throw new DireccionDuplicadaException();
		
	     }  
	}
	     inmueble.setDireccion(direccion);
		 inmueble.setDisponible(true);
		
		repositorioInmueble.guardarInmueble(inmueble);
		
	}

			

	@Override
	public List<Inmueble> buscarInmueble(Long provinciaId, String nombreCiudad) {

		return repositorioInmueble.buscarInmueble(provinciaId, nombreCiudad);

	}

	@Override
	public Inmueble consultarInmueblePorId(Long inmuebleId) {

		return repositorioInmueble.consultarInmueblePorId(inmuebleId);

	}

	@Override
	public void agregarInquilino(Long inmuebleId,Long usuarioId) throws InmuebleNoDisponibleException, InmuebleInexistenteException,UsuarioInexistenteException {
        Inmueble inmueble = repositorioInmueble.consultarInmueblePorId(inmuebleId);
		if(inmueble== null) throw new InmuebleInexistenteException();
		
		if (inmueble.getDisponible().equals(false)) throw new InmuebleNoDisponibleException();
		
		Usuario usuario=repositorioUsuario.consultarUsuarioPorId(usuarioId);
		if (usuario == null) throw new UsuarioInexistenteException();
				
			inmueble.setInquilino(usuario);
			inmueble.setDisponible(false);
			repositorioInmueble.modificarInmueble(inmueble);

	}

	@Override
	public List<Inmueble> filtrarInmueblesPorPrecio(Double desdePrecio, Double hastaPrecio) {
		
		return repositorioInmueble.filtrarInmueblesPorPrecio(desdePrecio,hastaPrecio);
	}



	
}
