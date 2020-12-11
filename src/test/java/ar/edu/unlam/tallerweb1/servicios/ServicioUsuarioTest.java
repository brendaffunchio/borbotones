package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.controladores.ControladorUsuario;
import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.DireccionNoValidaException;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.PasswordVaciaException;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.UsuarioYaExisteException;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDireccion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.*;

import org.junit.Test;
import org.mockito.Mockito;


public class ServicioUsuarioTest {
	
	private Inmueble inmueble = new Inmueble();
	
	private Direccion direccion = new Direccion();
	
	private RepositorioUsuario repositorioUsuarioMock = mock(RepositorioUsuario.class);
	
	private RepositorioDireccion repositorioDireccionMock = mock(RepositorioDireccion.class);
	
	private ServicioUsuarios servicioUsuario = new ServicioUsuariosImpl(repositorioUsuarioMock, repositorioDireccionMock);
	
	private List<Inmueble> listaMock = mock(List.class);
	
	private Set<Torneo> treeSetMock = mock(TreeSet.class);
	
	private Usuario crearUsuario() {
		
		
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNombre("Carlos");
		usuario.setApellido("Solis");
		usuario.setRol("Invitado");
		usuario.setEmail("Carlitos@hotmail.com");
		usuario.setPassword("1234");
		usuario.setTorGanados(0);
		
		
		
		return usuario;
	}
	
	private void guardarUsuario() throws PasswordVaciaException, UsuarioYaExisteException, DireccionNoValidaException {
		
		
		Usuario usuario = crearUsuario();
		
		servicioUsuario.guardarUsuario(usuario, direccion);
	}
	
	@Test
	public void queSePuedaGuardarUnUsuario() throws PasswordVaciaException, UsuarioYaExisteException, DireccionNoValidaException {
		
		//preparación
		Usuario usuario = crearUsuario();
		
		usuario.setDireccion(direccion);
			
		//ejecucion.
		servicioUsuario.guardarUsuario(usuario, direccion);
		
		//valdiación
		verify(repositorioUsuarioMock, times(1)).listarTodosLosUsuarios();
		verify(repositorioUsuarioMock, times(1)).guardarUsuario(usuario);
		
	}
	
	@Test(expected = DireccionNoValidaException.class)
	public void cuandoUnUsuarioSeRegistraConUnaDireccionInexistenteLanzaDireccionNoValidaException() throws PasswordVaciaException, UsuarioYaExisteException, DireccionNoValidaException {
		
		//preparacion
		Usuario usuario = crearUsuario();
		
		Direccion direccionUsuario = null;
		
		//ejecucion
		
		servicioUsuario.guardarUsuario(usuario, direccionUsuario);
	
	
		//validacion.
		
	
		verify(repositorioUsuarioMock, never()).guardarUsuario(usuario);
	
	
	}
	
	@Test(expected = PasswordVaciaException.class)
	public void cuandoUnUsuarioSeRegistraConUnaPasswordVaciaLanzaPasswordVaciaException() throws PasswordVaciaException, UsuarioYaExisteException, DireccionNoValidaException {
		
		//preparacion
		Usuario usuario = crearUsuario();
		
		usuario.setPassword(" ");
		
		//ejecucion
		
		servicioUsuario.guardarUsuario(usuario, direccion);
	
	
		//validacion.
	
		verify(repositorioUsuarioMock, never()).guardarUsuario(usuario);
	
	}
	
	@Test
	public void queSeMuestreLaListaDeInmueblesAlquiladosPorElUsuario() {
		
		//preparación
		Usuario usuario = crearUsuario();
		
		inmueble.setInquilino(usuario);
		
		//ejecución 
		
		when(servicioUsuario.listarInmueblesAlquiladosDeUnUsuario(usuario.getId())).thenReturn(listaMock);
		servicioUsuario.listarInmueblesAlquiladosDeUnUsuario(usuario.getId());
		
		//validación
		
		verify(repositorioUsuarioMock, times(1)).listarInmueblesAlquiladosDeUnUsuario(usuario.getId());
	}
	
	@Test
	public void queSeMuestreLaListaDeTorneosQueParticipaElUsuario() {
		
		//preparación
		Usuario usuario = crearUsuario();
		
		
		//ejecución 
		
		when(servicioUsuario.listarTorneosQueParticipaUnUsuario(usuario.getId())).thenReturn(treeSetMock);
		servicioUsuario.listarTorneosQueParticipaUnUsuario(usuario.getId());
		
		//validación
		
		verify(repositorioUsuarioMock, times(1)).listarTorneosQueParticipaUnUsuario(usuario.getId());
	}
	
	
}
