package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.Torneo;

public interface ServicioInmueble {

	public List<Inmueble> mostrarInmuebles();

	public void guardarInmueble(Inmueble inmueble);

	public List<Inmueble> buscarInmueblePorProvincia(String provincia);

	public List<Inmueble> buscarInmueblePorLocalidad(String localidad);

	public List<Inmueble> buscarInmueble(String provincia, String localidad);

	public Inmueble verDetallesInmueble(Long id);
	
}
