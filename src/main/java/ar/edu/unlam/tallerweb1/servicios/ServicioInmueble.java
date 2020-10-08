package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Inmueble;

public interface ServicioInmueble {

	public List<Inmueble>mostrarInmuebles();

	public void guardarInmueble(Inmueble inmueble);
	
}
