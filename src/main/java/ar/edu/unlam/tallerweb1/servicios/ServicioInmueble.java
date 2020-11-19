package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.Torneo;

public interface ServicioInmueble {

	public List<Inmueble> mostrarInmuebles();

	public void guardarInmueble(Inmueble inmueble,Direccion direccion);

	public List<Inmueble> buscarInmueble(String nombreProvincia, String nombreCiudad);

	public Inmueble verDetallesInmueble(Long id);

	public void agregarInquilino(Long inmuebleId, Long usuarioId);

	
	
}
