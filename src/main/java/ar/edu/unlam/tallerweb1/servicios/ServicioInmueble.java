package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.Torneo;

public interface ServicioInmueble {

	public List<Inmueble> mostrarInmuebles();

	public void guardarInmueble(Inmueble inmueble,Direccion direccion);

	public List<Inmueble> buscarInmueble(Long provinciaId, String nombreCiudad);

	public Inmueble verDetallesInmueble(Long inmuebleId);

	public void agregarInquilino(Long detalleInmueble, Long usuarioId);

	
	
}
