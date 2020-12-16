package ar.edu.unlam.tallerweb1.servicios;

import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.Ciudad;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCiudad;


public class ServicioCiudadTest {

	private RepositorioCiudad repositorioCiudadMock = mock(RepositorioCiudad.class);
	private ServicioCiudad servicio= new ServicioCiudadImpl(repositorioCiudadMock);
	
	@Test
	public void queSeListenTodasLasCiudades() {
		//preparacion
		Ciudad ciudad= new Ciudad();
		List<Ciudad> ciudades = new LinkedList<Ciudad>();
		ciudades.add(ciudad);
		//ejecucion
		when(repositorioCiudadMock.listarCiudades()).thenReturn(ciudades);
		servicio.listarCiudades();
		
		//comprobacion
		verify(repositorioCiudadMock,times(1)).listarCiudades();
		
	}
}
