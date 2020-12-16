package ar.edu.unlam.tallerweb1.servicios;

import static org.mockito.Mockito.*;

import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDireccion;

public class ServicioDireccionTest {

	private RepositorioDireccion repositorioDireccionMock = mock(RepositorioDireccion.class);
	private ServicioDireccion servicio= new ServicioDireccionImpl(repositorioDireccionMock);
	
	@Test
	public void queSePuedaBuscarUnaDireccionPorCalleYNumero() {
		//preparacion
		Direccion direccion= new Direccion();
		direccion.setCalle("Libertad");
		direccion.setNumero(325);
		
		//ejecucion
		when(repositorioDireccionMock.buscarDireccion(direccion.getCalle(), direccion.getNumero())).thenReturn(direccion);
		servicio.buscarDireccion(direccion.getCalle(), direccion.getNumero());
		
		//comprobacion
		verify(repositorioDireccionMock,times(1)).buscarDireccion(direccion.getCalle(),direccion.getNumero());
	
	}
}
