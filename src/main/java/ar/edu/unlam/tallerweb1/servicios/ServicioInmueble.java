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
import ar.edu.unlam.tallerweb1.modelo.InmueblesBuscadosException;

public interface ServicioInmueble {

	public List<Inmueble> mostrarInmuebles();

	public void guardarInmueble(Inmueble inmueble,Direccion direccion) throws DireccionDuplicadaException;
	
	public List<Inmueble> buscarInmueble(Long provinciaId, String nombreCiudad);

	public Inmueble verDetallesInmueble(Long inmuebleId);

	public void agregarInquilino(Long detalleInmueble, Long usuarioId);

	
	
}
