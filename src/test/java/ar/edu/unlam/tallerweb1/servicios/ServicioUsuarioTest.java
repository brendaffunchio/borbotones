package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.controladores.ControladorUsuario;
import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.DireccionNoValidaException;
import ar.edu.unlam.tallerweb1.modelo.PasswordVaciaException;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.UsuarioYaExisteException;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDireccion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


import org.junit.Test;



public class ServicioUsuarioTest {
	
	private Usuario usuario = new Usuario();

	private Direccion direccion = new Direccion();
	
	private RepositorioUsuario repositorioUsuarioMock = mock(RepositorioUsuario.class);
	
	private RepositorioDireccion repositorioDireccionMock = mock(RepositorioDireccion.class);
	
	private ServicioUsuarios servicioUsuario = new ServicioUsuariosImpl(repositorioUsuarioMock, repositorioDireccionMock);
	
	
	@Test
	public void queSePuedaGuardarUnUsuario() throws PasswordVaciaException, UsuarioYaExisteException, DireccionNoValidaException {
		
		//preparacción
		usuario.setEmail("matias@hotmail.com");
		usuario.setPassword("1234");
		usuario.setTorGanados(0);
		usuario.setDireccion(direccion);
		
		
	
	
		//ejecucion.
		servicioUsuario.guardarUsuario(usuario, direccion);
		
		
		//valdiación
		verify(repositorioUsuarioMock, times(1)).mostrarUsuarios();
		verify(repositorioUsuarioMock, times(1)).guardarUsuario(usuario);
		
	}
}
