package ar.edu.unlam.tallerweb1.controladores;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Ciudad;
import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.DireccionDuplicadaException;
import ar.edu.unlam.tallerweb1.modelo.DireccionNoValidaException;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.PasswordVaciaException;
import ar.edu.unlam.tallerweb1.modelo.Provincia;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.UsuarioYaExisteException;
import ar.edu.unlam.tallerweb1.servicios.ServicioCiudad;
import ar.edu.unlam.tallerweb1.servicios.ServicioDireccion;
import ar.edu.unlam.tallerweb1.servicios.ServicioInmueble;
import ar.edu.unlam.tallerweb1.servicios.ServicioProvincia;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuarios;

public class ControladorUsuarioTest {
	
	private ServicioUsuarios servicioUsuariosMock = mock(ServicioUsuarios.class);
	private ServicioCiudad servicioCiudadMock = mock(ServicioCiudad.class);
	private ServicioDireccion servicioDireccionMock = mock(ServicioDireccion.class);
	private ServicioProvincia servicioProvinciaMock = mock(ServicioProvincia.class);
	private ControladorUsuario controladorUsuario = new ControladorUsuario(servicioUsuariosMock, servicioCiudadMock, servicioProvinciaMock, servicioDireccionMock);
	private HttpServletRequest requestMock = mock(HttpServletRequest.class);
	HttpSession sessionMock = mock(HttpSession.class);
	private final static String CALLE = "Machado";
	private final static Integer NUMERO = 730;
	private final static Long USUARIO_ID = 1L;
	private final static String JUEGO = "FIFA";
	
	@Test
	public void queCuandoSeQuieraRegistrarUnUsuarioLoRetorneALaVistaFormularioUsuario() {
		
		//preparacion
		List<Provincia>provincias = new LinkedList<Provincia>();
		List<Ciudad>ciudades = new LinkedList<Ciudad>();
		when(servicioProvinciaMock.listarTodasProvincias()).thenReturn(provincias);
		when(servicioCiudadMock.listarCiudades()).thenReturn(ciudades);
		
		// ejecucion
		final ModelAndView vista = controladorUsuario.nuevoUsuario();

		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("formularioUsuario");
		
		// verificacion
		
		verify(servicioProvinciaMock, times(1)).listarTodasProvincias();
		verify(servicioCiudadMock, times(1)).listarCiudades();
	}
	
	@Test
	public void queCuandoSeCreeUnNuevoUsuarioRetorneALaVistaRegistracionExitosa() throws PasswordVaciaException, UsuarioYaExisteException, DireccionNoValidaException {
		
		//preparacion
		Usuario usuario = new Usuario();
		Direccion direccion = new Direccion();
		when(servicioDireccionMock.buscarDireccion(CALLE, NUMERO)).thenReturn(direccion);
		
		// ejecucion
		final ModelAndView vista = controladorUsuario.crearUsuario(CALLE, NUMERO, usuario, requestMock);
		
		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("registracionExitosa");
		
		// verificacion
		verify(servicioDireccionMock, times(1)).buscarDireccion(CALLE, NUMERO);
		verify(servicioUsuariosMock, times(1)).guardarUsuario(usuario, direccion);;
	}
	
	@Test
	public void queCuandoElUsuarioIngreseUnaPasswordVaciaRetorneLaVistaErrores() throws PasswordVaciaException, UsuarioYaExisteException, DireccionNoValidaException {
		
		//preparacion
		Usuario usuario = new Usuario();
		Direccion direccion = new Direccion();
		doThrow(PasswordVaciaException.class).when(servicioUsuariosMock).guardarUsuario(usuario, direccion);
		when(servicioDireccionMock.buscarDireccion(CALLE, NUMERO)).thenReturn(direccion);
		
		// ejecucion
		final ModelAndView vista = controladorUsuario.crearUsuario(CALLE, NUMERO, usuario, requestMock);
		
		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("errores");
		
		// verificacion
		verify(servicioDireccionMock, times(1)).buscarDireccion(CALLE, NUMERO);
		verify(servicioUsuariosMock, times(1)).guardarUsuario(usuario, direccion);;
	}
	
	@Test
	public void queSiElUsuarioYaEstaRegistradoRetorneALaVistaErrores() throws PasswordVaciaException, UsuarioYaExisteException, DireccionNoValidaException {
		
		//preparacion
		Usuario usuario = new Usuario();
		Direccion direccion = new Direccion();
		doThrow(UsuarioYaExisteException.class).when(servicioUsuariosMock).guardarUsuario(usuario, direccion);
		when(servicioDireccionMock.buscarDireccion(CALLE, NUMERO)).thenReturn(direccion);
		
		// ejecucion
		final ModelAndView vista = controladorUsuario.crearUsuario(CALLE, NUMERO, usuario, requestMock);
		
		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("errores");
		
		// verificacion
		verify(servicioDireccionMock, times(1)).buscarDireccion(CALLE, NUMERO);
		verify(servicioUsuariosMock, times(1)).guardarUsuario(usuario, direccion);;
	}
	
	@Test
	public void queSiElUsuarioIngresaUnaDireccionNoValidaRetorneALaVistaErrores() throws PasswordVaciaException, UsuarioYaExisteException, DireccionNoValidaException {
		
		//preparacion
		Usuario usuario = new Usuario();
		Direccion direccion = new Direccion();
		doThrow(DireccionNoValidaException.class).when(servicioUsuariosMock).guardarUsuario(usuario, direccion);
		when(servicioDireccionMock.buscarDireccion(CALLE, NUMERO)).thenReturn(direccion);
		
		// ejecucion
		final ModelAndView vista = controladorUsuario.crearUsuario(CALLE, NUMERO, usuario, requestMock);
		
		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("errores");
		
		// verificacion
		verify(servicioDireccionMock, times(1)).buscarDireccion(CALLE, NUMERO);
		verify(servicioUsuariosMock, times(1)).guardarUsuario(usuario, direccion);;
	}
	
	@Test
	public void queRetorneALaVistaPerfilUsuario() {
		
		// ejecucion
		final ModelAndView vista = controladorUsuario.verPerfilDelUsuario();
		
		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("perfilUsuario");
		 
	}
	
	@Test
	public void queCuandoElUsuarioQuieraVerSusInmueblesAlquiladosLoRetorneALaVistaMisInmueblesAlquilados() {
		
		// preparacion
		Inmueble inmueble = new Inmueble();
		List <Inmueble> inmueblesAlquilados = new LinkedList<Inmueble>();
		inmueblesAlquilados.add(inmueble);
		when(servicioUsuariosMock.listarInmueblesAlquiladosDeUnUsuario(USUARIO_ID)).thenReturn(inmueblesAlquilados);
		
		// ejecucion
		final ModelAndView vista = controladorUsuario.mostrarMisInmuebles(USUARIO_ID);
		
		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("misInmueblesAlquilados");
		
		// verificacion
		
		verify(servicioUsuariosMock, times(2)).listarInmueblesAlquiladosDeUnUsuario(USUARIO_ID);
		
	}
	
	@Test
	public void queCuandoElUsuarioQuieraVerSusInmueblesAlquiladosSinTenerAlgunoAlquiladoLoRetorneALaVistaMisInmueblesAlquiladosConMensajeDeError() {
		
		// preparacion
		List <Inmueble> inmueblesAlquilados = new LinkedList<Inmueble>();
		when(servicioUsuariosMock.listarInmueblesAlquiladosDeUnUsuario(USUARIO_ID)).thenReturn(inmueblesAlquilados);
		
		// ejecucion
		final ModelAndView vista = controladorUsuario.mostrarMisInmuebles(USUARIO_ID);
		final ModelMap modelo = vista.getModelMap();
		
		// comprobacion
		assertTrue(modelo.containsKey("error"));
		assertThat(vista.getViewName()).isEqualTo("misInmueblesAlquilados");
		
		// verificacion
		
		verify(servicioUsuariosMock, times(1)).listarInmueblesAlquiladosDeUnUsuario(USUARIO_ID);
		
	}
	
	@Test
	public void queCuandoElUsuarioQuieraVerLosTorneosALosQueParticipaLoRetorneALaVistaMisTorneosParticipo() {
		Torneo torneo = new Torneo();
		torneo.setJuego(JUEGO);
		Set <Torneo> torneosQueParticipaElUsuario = new TreeSet<Torneo>();
		torneosQueParticipaElUsuario.add(torneo);
		
		// preparacion
		when(servicioUsuariosMock.listarTorneosQueParticipaUnUsuario(USUARIO_ID)).thenReturn(torneosQueParticipaElUsuario);
		
		// ejecucion
		final ModelAndView vista = controladorUsuario.mostrarTorneosQueParticipaElUsuario(USUARIO_ID);
		
		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("misTorneosParticipo");
		
		// verificacion
		
		verify(servicioUsuariosMock, times(2)).listarTorneosQueParticipaUnUsuario(USUARIO_ID);
		
	}
	
	@Test
	public void queCuandoElUsuarioQuieraVerLosTorneosALosQueParticipaSinParticiparEnAlgunoLoRetorneALaVistaMisInmueblesAlquiladosConMensajeDeError() {
		
		// preparacion
		Set<Torneo> torneosEnLosQueParticipaUnUsuario = new TreeSet<Torneo>();
		when(servicioUsuariosMock.listarTorneosQueParticipaUnUsuario(USUARIO_ID)).thenReturn(torneosEnLosQueParticipaUnUsuario);
		
		// ejecucion
		final ModelAndView vista = controladorUsuario.mostrarTorneosQueParticipaElUsuario(USUARIO_ID);
		final ModelMap modelo = vista.getModelMap();
		
		// comprobacion
		assertTrue(modelo.containsKey("error"));
		assertThat(vista.getViewName()).isEqualTo("misTorneosParticipo");
		
		// verificacion
		
		verify(servicioUsuariosMock, times(1)).listarTorneosQueParticipaUnUsuario(USUARIO_ID);
		
		
	}
	
	@Test
	public void queCuandoElUsuarioQuieraVerLosTorneosQueCreoLoRetorneALaVistaMisTorneosCreados() {
		
		Torneo torneo = new Torneo();
		torneo.setJuego(JUEGO);
		List <Torneo> torneosQueCreaElUsuario = new LinkedList<Torneo>();
		torneosQueCreaElUsuario.add(torneo);
		
		// preparacion
		when(servicioUsuariosMock.listarTorneosQueCreoUnUsuario(USUARIO_ID)).thenReturn(torneosQueCreaElUsuario);
				
		// ejecucion
		final ModelAndView vista = controladorUsuario.mostrarTorneosQueCreoElUsuario(USUARIO_ID);
				
		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("misTorneosCreados");
				
		// verificacion
				
		verify(servicioUsuariosMock, times(2)).listarTorneosQueCreoUnUsuario(USUARIO_ID);
		
		
	}
	
	@Test
	public void queCuandoElUsuarioQuieraVerLosTorneosQueCreoSinCrearAlgunoLoRetorneALaVistaMisTorneosCreadosConMensajeDeError() {
		
		// preparacion
		List<Torneo> torneosCreadosPorElUsuario = new LinkedList<Torneo>();
		when(servicioUsuariosMock.listarTorneosQueCreoUnUsuario(USUARIO_ID)).thenReturn(torneosCreadosPorElUsuario);
		
		// ejecucion
		final ModelAndView vista = controladorUsuario.mostrarTorneosQueCreoElUsuario(USUARIO_ID);
		final ModelMap modelo = vista.getModelMap();
		
		// comprobacion
		assertTrue(modelo.containsKey("error"));
		assertThat(vista.getViewName()).isEqualTo("misTorneosCreados");
		
		// verificacion
		
		verify(servicioUsuariosMock, times(1)).listarTorneosQueCreoUnUsuario(USUARIO_ID);
		
		
	}
	
	
}
