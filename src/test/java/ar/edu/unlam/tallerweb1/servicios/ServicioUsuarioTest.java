package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.DireccionNoValidaException;
import ar.edu.unlam.tallerweb1.modelo.PasswordVaciaException;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.UsuarioYaExisteException;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class ServicioUsuarioTest {
	
	
	private RepositorioUsuario repositorioUsuarioMock = mock(RepositorioUsuario.class);
	
	private ServicioUsuarios servicioUsuario = new ServicioUsuariosImpl(repositorioUsuarioMock);


	private Usuario crearUsuario() {
				
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNombre("Carlos");
		usuario.setApellido("Solis");
		usuario.setRol("Invitado");
		usuario.setEmail("carlitos@hotmail.com");
		usuario.setPassword("1234");
		usuario.setTorGanados(0);
			
		return usuario;
	}
	private Direccion crearDireccion() {
		Direccion direccion= new Direccion();
		direccion.setCalle("Libertad");
		direccion.setNumero(325);
		return direccion;
	}

	
	@Test
	public void queSePuedaGuardarUnUsuario() throws PasswordVaciaException, UsuarioYaExisteException, DireccionNoValidaException {
		
		//preparacion
		Direccion direccion = crearDireccion();
		Usuario usuario1 = crearUsuario();
		usuario1.setEmail("user@mail.com");
		Usuario usuario2 = crearUsuario();
		
		List <Usuario>usuarios= new LinkedList();
		usuarios.add(usuario1);
			
		//ejecucion
		when(repositorioUsuarioMock.listarTodosLosUsuarios()).thenReturn(usuarios);
		servicioUsuario.guardarUsuario(usuario2, direccion);
		
		//comprobacion
		verify(repositorioUsuarioMock, times(1)).guardarUsuario(usuario2);
		
	}
	
	@Test(expected = DireccionNoValidaException.class)
	public void cuandoUnUsuarioSeRegistraConUnaDireccionInexistenteLanzaDireccionNoValidaException() throws PasswordVaciaException, UsuarioYaExisteException, DireccionNoValidaException {
		
		//preparacion
		Direccion direccion = null;
		Usuario usuario1 = crearUsuario();
		usuario1.setEmail("user@mail.com");
		Usuario usuario2 = crearUsuario();
		
		List <Usuario>usuarios= new LinkedList();
		usuarios.add(usuario1);
			
		//ejecucion
		when(repositorioUsuarioMock.listarTodosLosUsuarios()).thenReturn(usuarios);
		servicioUsuario.guardarUsuario(usuario2, direccion);
	
		//ejecucion
		
		servicioUsuario.guardarUsuario(usuario2, direccion);
	
	
		//comprobacion
		verify(repositorioUsuarioMock, never()).guardarUsuario(usuario2);
	
	
	}
	
	@Test(expected = PasswordVaciaException.class)
	public void cuandoUnUsuarioSeRegistraConUnaPasswordVaciaLanzaPasswordVaciaException() throws PasswordVaciaException, UsuarioYaExisteException, DireccionNoValidaException {
		
		//preparacion
		Direccion direccion = crearDireccion();
		Usuario usuario1 = crearUsuario();
		usuario1.setEmail("user@mail.com");
		Usuario usuario2 = crearUsuario();
		usuario2.setPassword(" ");
		
		List <Usuario>usuarios= new LinkedList();
		usuarios.add(usuario1);
			
		//ejecucion
		when(repositorioUsuarioMock.listarTodosLosUsuarios()).thenReturn(usuarios);
		servicioUsuario.guardarUsuario(usuario2, direccion);
	
		//comprobacion
		verify(repositorioUsuarioMock, never()).guardarUsuario(usuario2);
	
	}
	@Test(expected = UsuarioYaExisteException.class)
	public void cuandoUnUsuarioSeRegistraConElMismoEmailQueOtroUsuarioLanzaUsuarioYaExisteException() throws PasswordVaciaException, UsuarioYaExisteException, DireccionNoValidaException {
		
		//preparacion
		Direccion direccion = crearDireccion();
		Usuario usuario1 = crearUsuario();
		Usuario usuario2 = crearUsuario();

		List <Usuario>usuarios= new LinkedList();
		usuarios.add(usuario1);
			
		//ejecucion
		when(repositorioUsuarioMock.listarTodosLosUsuarios()).thenReturn(usuarios);
		servicioUsuario.guardarUsuario(usuario2, direccion);
	
		//comprobacion
		verify(repositorioUsuarioMock, never()).guardarUsuario(usuario2);
	
	}
	@Test
	public void queSeMuestreLaListaDeInmueblesAlquiladosPorElUsuario() {
		
		//preparacion
		Usuario usuario = crearUsuario();
				
		//ejecucion
		servicioUsuario.listarInmueblesAlquiladosDeUnUsuario(usuario.getId());
		
		//comprobacion
		verify(repositorioUsuarioMock, times(1)).listarInmueblesAlquiladosDeUnUsuario(usuario.getId());
	}

	@Test 
	public void queSeMuestreLaListaDeTorneosQueParticipaUnUsuario() {
		
		//preparacion
		Usuario usuario= crearUsuario();
		
		//ejecucion
		servicioUsuario.listarTorneosQueParticipaUnUsuario(usuario.getId());
		
		//comprobacion
		verify(repositorioUsuarioMock,times(1)).listarTorneosQueParticipaUnUsuario(usuario.getId());
		
	}
	@Test
	public void queSeMuestreLaListaDeUsuariosMasGanadores() {
		
		//preparacion
		Usuario usuario = crearUsuario();
		
		//ejecucion
		servicioUsuario.listarUsuariosMasGanadores();
		
		//comprobacion
		verify(repositorioUsuarioMock, times(1)).listarUsuariosMasGanadores();
	}
	
	@Test
	public void queDevuelvaElUsuarioBuscadoPorId() {
		
		//preparacion
		Usuario usuario= crearUsuario();
		
		//ejecucion
		servicioUsuario.consultarUsuarioPorId(usuario.getId());
		
		//comprobacion
		verify(repositorioUsuarioMock, times(1)).consultarUsuarioPorId(usuario.getId());
	}
	
	
}
