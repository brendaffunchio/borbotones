package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Inmueble;

public interface RepositorioInmueble {

	public List<Inmueble>todosLosInmuebles();
	
	public void guardarInmueble(Inmueble inmueble);

	public List<Inmueble> buscarInmueblePorProvincia(String provincia);

	public List<Inmueble> buscarInmueblePorLocalidad(String localidad);
}
