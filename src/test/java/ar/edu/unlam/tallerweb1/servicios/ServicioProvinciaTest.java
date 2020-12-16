package ar.edu.unlam.tallerweb1.servicios;

import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.Provincia;

import ar.edu.unlam.tallerweb1.repositorios.RepositorioProvincia;

public class ServicioProvinciaTest {

	private RepositorioProvincia repositorioProvinciaMock = mock(RepositorioProvincia.class);
	private ServicioProvincia servicio = new ServicioProvinciaImpl(repositorioProvinciaMock);

	@Test
	public void queSeListenTodasLasProvincias() {
		// preparacion
		Provincia provincia = new Provincia();
		List<Provincia> provincias = new LinkedList<Provincia>();
		provincias.add(provincia);
		//ejecucion
		when(repositorioProvinciaMock.listarTodasLasProvincias()).thenReturn(provincias);
		servicio.listarTodasProvincias();
		
		//comprobacion
		verify(repositorioProvinciaMock,times(1)).listarTodasLasProvincias();
	}

}
