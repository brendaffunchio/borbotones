package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Inmueble;

public interface ServicioInmueble {

	public List<Inmueble> mostrarInmuebles();

	public void guardarInmueble(Inmueble inmueble);

	public List<Inmueble> buscarInmueblePorProvincia(String provincia);

	public List<Inmueble> buscarInmueblePorCiudad(String ciudad);
	
}
