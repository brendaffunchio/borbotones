package ar.edu.unlam.tallerweb1.servicios;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;

import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.DireccionDuplicadaException;

import ar.edu.unlam.tallerweb1.modelo.DireccionNoValidaException;
import ar.edu.unlam.tallerweb1.modelo.FotoInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.InmuebleInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.InmuebleNoDisponibleException;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.UsuarioInexistenteException;

public interface ServicioInmueble {

	public List<Inmueble> mostrarInmuebles();

	public void guardarInmueble(Inmueble inmueble,Direccion direccion) throws DireccionDuplicadaException,DireccionNoValidaException;
	
	public List<Inmueble> buscarInmueble(Long provinciaId, String nombreCiudad);

	public Inmueble consultarInmueblePorId(Long inmuebleId);

	public void agregarInquilino(Long inmuebleId, Long usuarioId) throws InmuebleNoDisponibleException, InmuebleInexistenteException, UsuarioInexistenteException;

	public List<Inmueble> filtrarInmueblesPorPrecio(Double desdePrecio, Double hastaPrecio);

	
	
}
