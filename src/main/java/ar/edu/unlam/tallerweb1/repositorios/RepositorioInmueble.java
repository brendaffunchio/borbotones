package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Inmueble;

public interface RepositorioInmueble {

	public List<Inmueble>todosLosInmuebles();
	
	public void guardarInmueble(Inmueble inmueble);

	List<Inmueble> buscarInmueble(String provincia, String localidad);

	public Inmueble verDetallesInmueble(Long id_inmueble);
}
