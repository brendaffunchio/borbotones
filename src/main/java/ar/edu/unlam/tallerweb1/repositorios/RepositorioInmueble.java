package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Inmueble;

public interface RepositorioInmueble {

	public List<Inmueble>todosLosInmuebles();
	
	public void guardarInmueble(Inmueble inmueble, String calle, Integer numero);

	List<Inmueble> buscarInmueble(String nombreProvincia, String nombreCiudad);

	public Inmueble verDetallesInmueble(Long id);
}
