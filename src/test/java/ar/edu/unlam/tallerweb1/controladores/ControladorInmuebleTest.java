package ar.edu.unlam.tallerweb1.controladores;

import static org.mockito.Mockito.mock;

import java.io.IOException;

import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileUploadException;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ar.edu.unlam.tallerweb1.modelo.Ciudad;
import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.DireccionDuplicadaException;
import ar.edu.unlam.tallerweb1.modelo.DireccionNoValidaException;
import ar.edu.unlam.tallerweb1.modelo.FotoInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.InmuebleInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.InmuebleNoDisponibleException;
import ar.edu.unlam.tallerweb1.modelo.Provincia;
import ar.edu.unlam.tallerweb1.modelo.UsuarioInexistenteException;
import ar.edu.unlam.tallerweb1.servicios.ServicioCiudad;
import ar.edu.unlam.tallerweb1.servicios.ServicioDireccion;
import ar.edu.unlam.tallerweb1.servicios.ServicioFoto;
import ar.edu.unlam.tallerweb1.servicios.ServicioInmueble;
import ar.edu.unlam.tallerweb1.servicios.ServicioProvincia;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuarios;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class ControladorInmuebleTest {

	private ServicioInmueble servicioInmuebleMock = mock(ServicioInmueble.class);
	private ServicioUsuarios servicioUsuarioMock = mock(ServicioUsuarios.class);
	private ServicioCiudad servicioCiudadMock = mock(ServicioCiudad.class);
	private ServicioProvincia servicioProvinciaMock = mock(ServicioProvincia.class);
	private ServicioDireccion servicioDireccionMock = mock(ServicioDireccion.class);
	private ServicioFoto servicioFotoMock = mock(ServicioFoto.class);
	private List<Inmueble> inmueblesMock = mock(List.class);
	private List<Provincia> provinciasMock = mock(List.class);
	private List<Ciudad> ciudadesMock = mock(List.class);
	private MultipartFile fotoMock = mock(MultipartFile.class);
	private RedirectAttributes flashMock = mock(RedirectAttributes.class);
	private ControladorInmueble controlador = new ControladorInmueble(servicioInmuebleMock, servicioCiudadMock,
			servicioProvinciaMock, servicioDireccionMock, servicioUsuarioMock, servicioFotoMock);
	private HttpServletRequest requestMock = mock(HttpServletRequest.class);
	private final static Long inmuebleId = 1L;
	private final static Long usuarioId = 1L;
	private final static Long provinciaId = 1L;
	private final static String ciudad = "Moron";
	private final static Double precioDesde = 10D;
	private final static Double precioHasta = 20D;

	

	@Test
	public void queRetorneALaVistaInmueblesParaAlquilar() {
		//preparacion
		when(servicioInmuebleMock.listarTodosLosInmueblesDisponibles()).thenReturn(inmueblesMock);
		when(servicioProvinciaMock.listarTodasProvincias()).thenReturn(provinciasMock);

		//ejecucion
		final ModelAndView vista = controlador.mostrarInmuebles();

		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("InmueblesParaAlquilar");
	}

	@Test
	public void queCuandoSeQuieraPublicarUnInmuebleRetorneALaVistaPublicarInmueble() {

		// preparacion
		
		when(servicioProvinciaMock.listarTodasProvincias()).thenReturn(provinciasMock);
		when(servicioCiudadMock.listarCiudades()).thenReturn(ciudadesMock);

		// ejecucion
		final ModelAndView vista = controlador.nuevoInmueble();

		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("publicarInmueble");

	}

	@Test
	public void queRedirijaAVerInmueblesCuandoSeCreaUnInmueble() throws FotoInexistenteException, FileUploadException, IOException,
			DireccionDuplicadaException, DireccionNoValidaException {

		// preparacion
		Inmueble inmueble = new Inmueble();
		Direccion direccion = new Direccion();
		final String calle = "Machado";
		final Integer numero = 730;
		direccion.setCalle(calle);
		direccion.setNumero(numero);
		
		when (servicioDireccionMock.buscarDireccion(calle, numero)).thenReturn(direccion);

		// ejecucion
		final ModelAndView vista = controlador.crearInmueble(calle, numero, fotoMock, inmueble, flashMock);

		
		// verificacion
		verify(servicioFotoMock, times(1)).guardarFoto(inmueble, fotoMock);
		verify(servicioDireccionMock, times(1)).buscarDireccion(calle, numero);
		verify(servicioInmuebleMock, times(1)).guardarInmueble(inmueble, direccion);
		assertThat(vista.getViewName()).isEqualTo("redirect:/ver-inmuebles");
		
		
	}

	@Test
	public void queAlIngresarUnaDireccionDeInmuebleDuplicadaRetorneALaVistaErrores() throws FotoInexistenteException,
			FileUploadException, IOException, DireccionDuplicadaException, DireccionNoValidaException {

		// preparacion
		Inmueble inmueble = new Inmueble();
		Direccion direccion = new Direccion();
		final String calle = "Machado";
		final Integer numero = 730;
		direccion.setCalle(calle);
		direccion.setNumero(numero);
		
		doThrow(DireccionDuplicadaException.class).when(servicioDireccionMock).buscarDireccion(calle, numero);

		// ejecucion
		final ModelAndView vista = controlador.crearInmueble(calle, numero, fotoMock, inmueble, flashMock);
		
		// comprobacion
		verify(servicioFotoMock, times(1)).guardarFoto(inmueble, fotoMock);
		verify(servicioDireccionMock, times(1)).buscarDireccion(calle, numero);
		verify(servicioInmuebleMock, never()).guardarInmueble(inmueble, direccion);
		
		assertThat(vista.getViewName()).isEqualTo("errores");
		
		
	}
	
	@Test
	public void queAlIngresarUnaDireccionDeInmuebleNoValidaRetorneALaVistaErrores() throws FotoInexistenteException,
			FileUploadException, IOException, DireccionDuplicadaException, DireccionNoValidaException {

		// preparacion
		Inmueble inmueble = new Inmueble();
		Direccion direccion = new Direccion();
		final String calle = "Machado";
		final Integer numero = 730;
		direccion.setCalle(calle);
		direccion.setNumero(numero);
		
		doThrow(DireccionNoValidaException.class).when(servicioDireccionMock).buscarDireccion(calle, numero);

		// ejecucion
		final ModelAndView vista = controlador.crearInmueble(calle, numero, fotoMock, inmueble, flashMock);
		
		// comprobacion
		verify(servicioFotoMock, times(1)).guardarFoto(inmueble, fotoMock);
		verify(servicioDireccionMock, times(1)).buscarDireccion(calle, numero);
		verify(servicioInmuebleMock, never()).guardarInmueble(inmueble, direccion);
		
		assertThat(vista.getViewName()).isEqualTo("errores");
		
		
	}

	@Test
	public void queCuandoSeQuieraBuscarUnInmuebleRetorneALaVistaInmueblesParaAlquilarConUnaListaDeInmuebles() {

		// preparacion
		Inmueble inmueble = new Inmueble();
		
		List<Inmueble> inmueblesBuscados = new LinkedList<Inmueble>();
		inmueblesBuscados.add(inmueble);
		when(servicioInmuebleMock.buscarInmueble(provinciaId, ciudad)).thenReturn(inmueblesBuscados);

		// ejecucion
		final ModelAndView vista = controlador.buscarInmueble(requestMock, provinciaId);

		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("InmueblesParaAlquilar");
		
		
		
	}

	@Test
	public void queCuandoSeQuieraBuscarUnInmuebleRetorneALaVistaInmueblesParaAlquilarSinNingunInmueble() {

		// preaparacion
		when(servicioInmuebleMock.buscarInmueble(1L, ciudad)).thenReturn(inmueblesMock);

		// ejecucion
		final ModelAndView vista = controlador.buscarInmueble(requestMock, provinciaId);
		final ModelMap modelo = vista.getModelMap();

		// comprobacion

		assertTrue(modelo.containsKey("error"));
		assertThat(vista.getViewName()).isEqualTo("InmueblesParaAlquilar");
	}

	@Test
	public void queCuandoSeFiltreElPrecioDelInmuebleRetorneALavistaInmueblesParaAlquilar() {

		// preaparacion
		when(servicioInmuebleMock.filtrarInmueblesPorPrecio(precioDesde, precioHasta)).thenReturn(inmueblesMock);

		// ejecucion
		final ModelAndView vista = controlador.filtrarInmueblesPorPrecio(precioDesde, precioHasta);

		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("InmueblesParaAlquilar");
	}

	@Test
	public void queAlIrAlDetalleDeUnInmuebleMeLleveALaVistaInmuebleDetalle() {

		// preparacion
		Inmueble inmueble = new Inmueble();
		when(servicioInmuebleMock.consultarInmueblePorId(inmuebleId)).thenReturn(inmueble);

		// ejecucion
		final ModelAndView vista = controlador.verDetalle(inmuebleId);

		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("inmuebleDetalle");
	}

	@Test
	public void queAlAgregarUnInquilinoAlInmuebleMeRedirijaAVerTodosLosInmuebles()
			throws InmuebleNoDisponibleException, InmuebleInexistenteException, UsuarioInexistenteException {

		// ejecucion
		final ModelAndView vista = controlador.agregarInquilino(inmuebleId, usuarioId);

		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("redirect:/ver-inmuebles");

		// verificacion
		verify(servicioInmuebleMock, times(1)).agregarInquilino(inmuebleId, usuarioId);

	}

	
	@Test
	public void queAlAgregarUnInquilinoAUnInmuebleNoDisponibleRetorneALaVistaErrores()
			throws InmuebleNoDisponibleException, InmuebleInexistenteException, UsuarioInexistenteException {
		
		// preparacion
		doThrow(InmuebleNoDisponibleException.class).when(servicioInmuebleMock).agregarInquilino(inmuebleId, usuarioId);

		// ejecucion
		final ModelAndView vista = controlador.agregarInquilino(inmuebleId, usuarioId);
		

		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("errores");

		// verificacion
		verify(servicioInmuebleMock, times(1)).agregarInquilino(inmuebleId, usuarioId);

	}
	
	@Test
	public void queAlAgregarUnInquilinoAUnInmuebleInexistenteRetorneALaVistaErrores()
			throws InmuebleNoDisponibleException, InmuebleInexistenteException, UsuarioInexistenteException {
		
		// preparacion
		doThrow(InmuebleInexistenteException.class).when(servicioInmuebleMock).agregarInquilino(inmuebleId, usuarioId);

		// ejecucion
		final ModelAndView vista = controlador.agregarInquilino(inmuebleId, usuarioId);
		

		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("errores");

		// verificacion
		verify(servicioInmuebleMock, times(1)).agregarInquilino(inmuebleId, usuarioId);

	}
	
	@Test
	public void queAlAgregarUnUsuarioInexistenteAUnInmuebleRetorneALaVistaErrores()
			throws InmuebleNoDisponibleException, InmuebleInexistenteException, UsuarioInexistenteException {
		
		// preparacion
		doThrow(UsuarioInexistenteException.class).when(servicioInmuebleMock).agregarInquilino(inmuebleId, usuarioId);

		// ejecucion
		final ModelAndView vista = controlador.agregarInquilino(inmuebleId, usuarioId);
		
		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("errores");

		// verificacion
		verify(servicioInmuebleMock, times(1)).agregarInquilino(inmuebleId, usuarioId);

	}
	
	
	@Test
	public void queCuandoSeQuieraVerElDetalleDelInmuebleAlquiladoMeLLeveALaVistainmuebleDetalleAlquilado() {

		// preparacion
		Inmueble inmueble = new Inmueble();
		when(servicioInmuebleMock.consultarInmueblePorId(inmuebleId)).thenReturn(inmueble);

		// ejecucion
		final ModelAndView vista = controlador.verDetalleInmuebleAlquilado(inmuebleId);

		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("inmuebleDetalleAlquilado");
	}

}
