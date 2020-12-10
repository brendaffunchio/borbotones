package ar.edu.unlam.tallerweb1.servicios;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.DireccionDuplicadaException;

import ar.edu.unlam.tallerweb1.modelo.DireccionNoValidaException;
import ar.edu.unlam.tallerweb1.modelo.FotoInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.InmuebleInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.InmuebleNoDisponibleException;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.UsuarioInexistenteException;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDireccion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioInmueble;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

@Service
@Transactional
public class ServicioInmuebleImpl implements ServicioInmueble {

	private RepositorioInmueble repositorioInmueble;
	private RepositorioUsuario repositorioUsuario;
	private RepositorioDireccion repositorioDireccion;

	@Autowired
	public ServicioInmuebleImpl(RepositorioInmueble repositorioInmueble, RepositorioUsuario repositorioUsuario,
			RepositorioDireccion repositorioDireccion) {

		this.repositorioInmueble = repositorioInmueble;
		this.repositorioUsuario = repositorioUsuario;
		this.repositorioDireccion = repositorioDireccion;
	}

	@Override
	public List<Inmueble> mostrarInmuebles() {

		return repositorioInmueble.todosLosInmuebles();
	}

	@Override
	public void guardarInmueble(Inmueble inmueble, Direccion direccion) throws DireccionDuplicadaException, DireccionNoValidaException{
		if (direccion == null) throw new DireccionNoValidaException();
		
		for(Inmueble aux:repositorioInmueble.todosLosInmuebles()) {
		
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
		Usuario usuario=repositorioUsuario.consultarUsuarioPorId(usuarioId);
		if (usuario == null) throw new UsuarioInexistenteException();
		
		//(inmueble.getDisponible().equals(false)) throw new InmuebleNoDisponibleException();
		if (inmueble.getDisponible().equals(true)) {
			inmueble.setInquilino(usuario);
			inmueble.setDisponible(false);
			repositorioInmueble.modificarInmueble(inmueble);

		}else {
			throw new InmuebleNoDisponibleException();
		}

	}

	@Override
	public List<Inmueble> filtrarInmueblesPorPrecio(Double desdePrecio, Double hastaPrecio) {
		
		return repositorioInmueble.filtrarInmueblesPorPrecio(desdePrecio,hastaPrecio);
	}


}
