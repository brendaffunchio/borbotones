package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;

public interface RepositorioInmueble {

	public List<Inmueble>todosLosInmuebles();
	
	public void guardarInmueble(Inmueble inmueble,Direccion direccion);

	public List<Inmueble> buscarInmueble(String provinciaId, String nombreCiudad);

	public Inmueble consultarInmueblePorId(Long inmuebleId);
	
	public void modificarInmueble(Inmueble inmueble);
}
