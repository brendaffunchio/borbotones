package ar.edu.unlam.tallerweb1.controladores;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.fileupload.FileUploadException;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.CupoExcedidoException;
import ar.edu.unlam.tallerweb1.modelo.FotoInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.GanadorYaExisteException;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.InmuebleInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.ParticipanteDuplicadoException;
import ar.edu.unlam.tallerweb1.modelo.ParticipanteInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.TorneoInexistenteException;
import ar.edu.unlam.tallerweb1.servicios.ServicioFoto;
import ar.edu.unlam.tallerweb1.servicios.ServicioTorneo;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuarios;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.UsuarioInexistenteException;

import static org.mockito.Mockito.*;

public class ControladorTorneoTest {

	private ServicioTorneo servicioTorneoMock = mock(ServicioTorneo.class);
	private ServicioUsuarios servicioUsuarioMock = mock(ServicioUsuarios.class);
	private ServicioFoto servicioFotoMock = mock(ServicioFoto.class);
	private ControladorTorneo controladorTorneo = new ControladorTorneo(servicioTorneoMock, servicioUsuarioMock,
			servicioFotoMock);
	private List<Torneo> torneosMock = mock(List.class);
	private List<Inmueble> inmueblesAlquiladosPorElUsuario = mock(List.class);
	private MultipartFile fotoMock = mock(MultipartFile.class);
	private RedirectAttributes flashMock = mock(RedirectAttributes.class);
	private final static Long usuarioId = 1L;
	private final static Long usuarioGanadorId = 1L;
	private final static Long creadorId = 1L;
	private final static Long inmuebleId = 1L;
	private final static Long torneoId = 1L;
	private final static Long torneoGanadoId = 1L;
	private final static Double desdeKm = 1D;
	private final static Double hastaKm = 1D;
	private final static String categoria = "Deporte";
	private final static String juego = "FIFA";

	@Test
	public void queRetorneALaVistaToreosParaParticiparConLosTorneosParaParticiparFiltradosConLaDistanciaDelUsuario() {

		// preparacion
		when(servicioTorneoMock.listarTorneosConDistancia(usuarioId)).thenReturn(torneosMock);

		// ejecucion
		final ModelAndView vista = controladorTorneo.mostrarTorneos(usuarioId);

		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("torneosParaParticipar");

		// verificacion
		verify(servicioTorneoMock, times(1)).listarTorneosConDistancia(usuarioId);
		verify(servicioTorneoMock, never()).listarTodosLosTorneos();

	}

	@Test
	public void queRetorneALaVistaToreosParaParticiparConLosTorneosParaParticiparDeLaAplicacion() {

		// preparacion

		final Long Id = null;
		when(servicioTorneoMock.listarTodosLosTorneos()).thenReturn(torneosMock);

		// ejecucion
		final ModelAndView vista = controladorTorneo.mostrarTorneos(Id);

		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("torneosParaParticipar");

		// verificacion
		verify(servicioTorneoMock, never()).listarTorneosConDistancia(Id);
		verify(servicioTorneoMock, times(1)).listarTodosLosTorneos();

	}

	@Test
	public void queRetorneALaVistaToreosParaParticiparConLosTorneosOrdenadosPorDistanciaRespectoALaUbicacionDelUsuario() {

		// preparacion
		when(servicioTorneoMock.ordenarTorneosSegunDistancia()).thenReturn(torneosMock);

		// ejecucion
		final ModelAndView vista = controladorTorneo.mostrarTorneosSegunDistancia();
		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("torneosParaParticipar");
		// verificacion
		verify(servicioTorneoMock, times(1)).ordenarTorneosSegunDistancia();

	}

	@Test
	public void queRetorneALaVistaToreosParaParticiparConLosTorneosFiltradosPorRadioRespectoALaUbicacionDelUsuario() {

		// preparacion
		when(servicioTorneoMock.filtrarTorneosPorDistancia(desdeKm, hastaKm)).thenReturn(torneosMock);

		// ejecucion
		final ModelAndView vista = controladorTorneo.filtrarTorneosSegunRadio(desdeKm, hastaKm);

		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("torneosParaParticipar");

		// verificacion
		verify(servicioTorneoMock, times(1)).filtrarTorneosPorDistancia(desdeKm, hastaKm);
	}

	@Test
	public void queRetorneALaVistaOrganizarTorneosCuandoSeQuieraOrganizarUnTorneo() {

		// preparacion
		when(servicioUsuarioMock.listarInmueblesAlquiladosDeUnUsuario(usuarioId))
				.thenReturn(inmueblesAlquiladosPorElUsuario);

		// ejecucion
		final ModelAndView vista = controladorTorneo.nuevoTorneo(usuarioId);

		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("organizarTorneos");

		// verificacion
		verify(servicioUsuarioMock, times(1)).listarInmueblesAlquiladosDeUnUsuario(usuarioId);

	}

	@Test
	public void queRetorneALaVistaTorneoExitosoCuandoSeCreeUnTorneo() throws FotoInexistenteException, FileUploadException, IOException,
			InmuebleInexistenteException, UsuarioInexistenteException {

		// preparacion
		Torneo torneo = new Torneo();

		// ejecucion
		final ModelAndView vista = controladorTorneo.crearTorneo(fotoMock, creadorId, inmuebleId, torneo, flashMock);

		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("torneoExitoso");

		// verificacion
		verify(servicioFotoMock, times(1)).guardarFoto(torneo, fotoMock);

		verify(servicioTorneoMock, times(1)).guardarTorneo(torneo, creadorId, inmuebleId);
	}

	@Test
	public void queAlIntentarCrearUnTorneoSinUnInmuebleRetorneALaVistaErrores()
			throws FotoInexistenteException, FileUploadException, IOException, InmuebleInexistenteException,
			UsuarioInexistenteException {

		// preparacion
		Torneo torneo = new Torneo();
		doThrow(InmuebleInexistenteException.class).when(servicioTorneoMock).guardarTorneo(torneo, creadorId,
				inmuebleId);

		// ejecucion
		final ModelAndView vista = controladorTorneo.crearTorneo(fotoMock, creadorId, inmuebleId, torneo, flashMock);

		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("errores");

		// verificacion
		verify(servicioFotoMock, times(1)).guardarFoto(torneo, fotoMock);
		verify(servicioTorneoMock, times(1)).guardarTorneo(torneo, creadorId, inmuebleId);
	}

	@Test
	public void queAlIntentarCrearUnTorneoConUnUsuarioInexistenteRetorneALaVistaErrores()
			throws FotoInexistenteException, FileUploadException, IOException, InmuebleInexistenteException,
			UsuarioInexistenteException {

		// preparacion
		Torneo torneo = new Torneo();
		doThrow(UsuarioInexistenteException.class).when(servicioTorneoMock).guardarTorneo(torneo, creadorId,
				inmuebleId);

		// ejecucion
		final ModelAndView vista = controladorTorneo.crearTorneo(fotoMock, creadorId, inmuebleId, torneo, flashMock);

		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("errores");

		// verificacion
		verify(servicioFotoMock, times(1)).guardarFoto(torneo, fotoMock);
		verify(servicioTorneoMock, times(1)).guardarTorneo(torneo, creadorId, inmuebleId);
	}

	@Test
	public void queRetorneALaVistaParticipacionExitosaCunadoSeAgregueUnParticipanteAUnTorneo() throws ParticipanteDuplicadoException, CupoExcedidoException,
			TorneoInexistenteException, UsuarioInexistenteException {

		// ejecucion
		final ModelAndView vista = controladorTorneo.agregarParticipante(torneoId, usuarioId);

		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("participacionExitosa");

		// verificacion
		verify(servicioTorneoMock, times(1)).agregarParticipante(torneoId, usuarioId);
		;
	}

	@Test
	public void queAlIntentarAgregarUnParticipanteDuplicadoAUnTorneoRetorneALaVistaErrores()
			throws ParticipanteDuplicadoException, CupoExcedidoException, TorneoInexistenteException,
			UsuarioInexistenteException {
		// preparacion

		doThrow(ParticipanteDuplicadoException.class).when(servicioTorneoMock).agregarParticipante(torneoId, usuarioId);

		// ejecucion
		final ModelAndView vista = controladorTorneo.agregarParticipante(torneoId, usuarioId);

		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("errores");

		// verificacion
		verify(servicioTorneoMock, times(1)).agregarParticipante(torneoId, usuarioId);
		;

	}

	@Test
	public void queAlIntentarAgregarUnParticipanteAUnTorneoLlenoRetorneALaVistaErrores()
			throws ParticipanteDuplicadoException, CupoExcedidoException, TorneoInexistenteException,
			UsuarioInexistenteException {
		// preparacion

		doThrow(CupoExcedidoException.class).when(servicioTorneoMock).agregarParticipante(torneoId, usuarioId);

		// ejecucion
		final ModelAndView vista = controladorTorneo.agregarParticipante(torneoId, usuarioId);

		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("errores");

		// verificacion
		verify(servicioTorneoMock, times(1)).agregarParticipante(torneoId, usuarioId);
		;

	}

	@Test
	public void queAlIntentarAgregarUnParticipanteAUnTorneoInexistenteRetorneALaVistaErrores()
			throws ParticipanteDuplicadoException, CupoExcedidoException, TorneoInexistenteException,
			UsuarioInexistenteException {
		// preparacion

		doThrow(TorneoInexistenteException.class).when(servicioTorneoMock).agregarParticipante(torneoId, usuarioId);

		// ejecucion
		final ModelAndView vista = controladorTorneo.agregarParticipante(torneoId, usuarioId);

		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("errores");

		// verificacion
		verify(servicioTorneoMock, times(1)).agregarParticipante(torneoId, usuarioId);
		;

	}

	@Test
	public void queAlIntentarAgregarUnParticipanteInexistenteAUnTorneoLanceRetorneALaVistaErrores()
			throws ParticipanteDuplicadoException, CupoExcedidoException, TorneoInexistenteException,
			UsuarioInexistenteException {
		// preparacion

		doThrow(UsuarioInexistenteException.class).when(servicioTorneoMock).agregarParticipante(torneoId, usuarioId);

		// ejecucion
		final ModelAndView vista = controladorTorneo.agregarParticipante(torneoId, usuarioId);

		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("errores");

		// verificacion
		verify(servicioTorneoMock, times(1)).agregarParticipante(torneoId, usuarioId);
		;

	}

	@Test
	public void queRetorneAlaVistaPerfilUsuarioCuandoSeDesubscribaUnParticipanteDeUnTorneo()
			throws ParticipanteDuplicadoException, CupoExcedidoException, TorneoInexistenteException,
			UsuarioInexistenteException, ParticipanteInexistenteException {

		// ejecucion
		final ModelAndView vista = controladorTorneo.eliminarParticipante(torneoId, usuarioId);

		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("perfilUsuario");

		// verificacion
		verify(servicioTorneoMock, times(1)).eliminarParticipante(torneoId, usuarioId);
	}

	@Test
	public void queAlIntentarEliminarUnParticipanteInexistenteDelTorneoRetorneALaVistaErrores()
			throws ParticipanteInexistenteException, TorneoInexistenteException, UsuarioInexistenteException {

		// preparacion
		doThrow(ParticipanteInexistenteException.class).when(servicioTorneoMock).eliminarParticipante(torneoId,
				usuarioId);

		// ejecucion
		final ModelAndView vista = controladorTorneo.eliminarParticipante(torneoId, usuarioId);

		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("errores");

		// verificacion
		verify(servicioTorneoMock, times(1)).eliminarParticipante(torneoId, usuarioId);
	}

	@Test
	public void queAlIntentarEliminarUnParticipanteDeUnTorneoInexistenteRetorneALaVistaErrores()
			throws ParticipanteInexistenteException, TorneoInexistenteException, UsuarioInexistenteException {

		// preparacion
		doThrow(TorneoInexistenteException.class).when(servicioTorneoMock).eliminarParticipante(torneoId, usuarioId);

		// ejecucion
		final ModelAndView vista = controladorTorneo.eliminarParticipante(torneoId, usuarioId);

		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("errores");

		// verificacion
		verify(servicioTorneoMock, times(1)).eliminarParticipante(torneoId, usuarioId);
	}

	@Test
	public void queAlIntentarEliminarUnUsuarioInexistenteDeUnTorneoInexistenteRetorneALaVistaErrores()
			throws ParticipanteInexistenteException, TorneoInexistenteException, UsuarioInexistenteException {

		// preparacion
		doThrow(UsuarioInexistenteException.class).when(servicioTorneoMock).eliminarParticipante(torneoId, usuarioId);

		// ejecucion
		final ModelAndView vista = controladorTorneo.eliminarParticipante(torneoId, usuarioId);

		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("errores");

		// verificacion
		verify(servicioTorneoMock, times(1)).eliminarParticipante(torneoId, usuarioId);
	}

	@Test
	public void queCuandoSeBusqueTorneoPorCategoriaYJuegoRetorneALaVistaTorneosParaParticiparConUnaListaDeTorneos() {

		// preparacion
		Torneo torneo = new Torneo();
		List<Torneo> torneos = new LinkedList<Torneo>();
		torneos.add(torneo);

		when(servicioTorneoMock.buscarTorneo(categoria, juego)).thenReturn(torneos);

		// ejecucion
		final ModelAndView vista = controladorTorneo.mostrarTorneosPorJuego(categoria, juego);
		final ModelMap modelo = vista.getModelMap();

		// comprobacion
		assertTrue(modelo.containsKey("torneos"));
		assertThat(vista.getViewName()).isEqualTo("torneosParaParticipar");

		// verificacion

		verify(servicioTorneoMock, times(2)).buscarTorneo(categoria, juego);

	}

	@Test
	public void queAlBuscarTorneoPorCategoriaYJuegoRetorneALaVistaTorneosParaParticiparConUnaListaDeTorneosVacia() {

		// preparacion
		List<Torneo> torneo = new LinkedList<Torneo>();
		when(servicioTorneoMock.buscarTorneo(categoria, juego)).thenReturn(torneo);

		// ejecucion
		final ModelAndView vista = controladorTorneo.mostrarTorneosPorJuego(categoria, juego);
		final ModelMap modelo = vista.getModelMap();

		// comprobacion
		assertTrue(modelo.containsKey("error"));
		assertThat(vista.getViewName()).isEqualTo("torneosParaParticipar");

		// verificacion
		verify(servicioTorneoMock, times(1)).buscarTorneo(categoria, juego);

	}

	@Test
	public void queAlVerDetallesTorneoRetorneALaVistaDetallesTorneo() {

		// preparacion
		Torneo torneo = new Torneo();
		when(servicioTorneoMock.verDetallesTorneo(torneoId)).thenReturn(torneo);

		// ejecucion
		final ModelAndView vista = controladorTorneo.verDetallesTorneo(torneoId);

		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("detallesTorneo");

		// verificacion
		verify(servicioTorneoMock, times(1)).verDetallesTorneo(torneoId);

	}

	@Test
	public void queAlVerDetallesDelTorneoQueElUsuarioCreoRetorneALaVistaTorneoCreadoDetalle() {

		// preparacion
		Torneo torneo = new Torneo();
		when(servicioTorneoMock.verDetallesTorneo(torneoId)).thenReturn(torneo);

		// ejecucion
		final ModelAndView vista = controladorTorneo.verDetallesMiTorneoCreado(torneoId);

		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("torneoCreadoDetalle");

		// verificacion
		verify(servicioTorneoMock, times(1)).verDetallesTorneo(torneoId);

	}

	@Test
	public void queAlVerDetallesDeLosTorneosQueParticipaElUsuarioRetorneALaVistaTorneosParticipoDetalle() {

		// preparacion
		Torneo torneo = new Torneo();
		when(servicioTorneoMock.verDetallesTorneo(torneoId)).thenReturn(torneo);

		// ejecucion
		final ModelAndView vista = controladorTorneo.verDetallesTorneoQueParticipo(torneoId);

		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("torneosParticipoDetalle");

		// verificacion
		verify(servicioTorneoMock, times(1)).verDetallesTorneo(torneoId);

	}

	@Test
	public void queAlVerLaListaDeParticipantesDeUnTorneoMeRetorneALaVistaParticipantes() {

		// preparacion
		Set<Usuario> usuarios = new TreeSet<Usuario>();
		when(servicioTorneoMock.listarParticipantesDelTorneo(torneoId)).thenReturn(usuarios);

		// ejecucion
		final ModelAndView vista = controladorTorneo.verListaDeParticipantesDelTorneo(torneoId);

		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("participantes");

		// verificacion
		verify(servicioTorneoMock, times(1)).listarParticipantesDelTorneo(torneoId);

	}

	@Test
	public void queAlVerLaListaDeParticipantesParaElegirUnGanadorDeUnTorneoMeRetorneALaVistaVerListaDeParticipantesDelTorneoParaElegirGanador() {

		// preparacion
		Set<Usuario> usuarios = new TreeSet<Usuario>();
		when(servicioTorneoMock.listarParticipantesDelTorneo(torneoId)).thenReturn(usuarios);

		// ejecucion
		final ModelAndView vista = controladorTorneo.verListaDeParticipantesDelTorneoParaElegirGanador(torneoId);

		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("ganadorDelTorneo");

		// verificacion
		verify(servicioTorneoMock, times(1)).listarParticipantesDelTorneo(torneoId);

	}

	@Test
	public void queAlElegirAlGanadorDelTorneoMeRetorneALaVistaGanadorExitoso()
			throws GanadorYaExisteException, TorneoInexistenteException, UsuarioInexistenteException {
		// preparacion
		Torneo torneo = new Torneo();
		when(servicioTorneoMock.consultarTorneoPorId(torneoGanadoId)).thenReturn(torneo);

		// ejecucion
		final ModelAndView vista = controladorTorneo.elegirGanador(usuarioGanadorId, torneoGanadoId);

		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("ganadorExitoso");

		// verificacion
		verify(servicioTorneoMock, times(1)).consultarTorneoPorId(torneoGanadoId);
		verify(servicioTorneoMock, times(1)).elegirGanador(usuarioGanadorId, torneoGanadoId);

	}

	@Test
	public void queAlElegirAlGanadorDeUnTorneoGanadoMeRetorneALaVistaDeErrores()
			throws GanadorYaExisteException, TorneoInexistenteException, UsuarioInexistenteException {
		// preparacion
		Torneo torneo = new Torneo();
		doThrow(GanadorYaExisteException.class).when(servicioTorneoMock).elegirGanador(torneoGanadoId,
				usuarioGanadorId);
		when(servicioTorneoMock.consultarTorneoPorId(torneoGanadoId)).thenReturn(torneo);

		// ejecucion
		final ModelAndView vista = controladorTorneo.elegirGanador(usuarioGanadorId, torneoGanadoId);

		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("errores");

		// verificacion
		verify(servicioTorneoMock, times(1)).elegirGanador(usuarioGanadorId, torneoGanadoId);
		verify(servicioTorneoMock, never()).consultarTorneoPorId(torneoGanadoId);

	}

	@Test
	public void queAlElegirAlGanadorDeUnTorneoInexistenteMeRetorneALaVistaDeErrores()
			throws GanadorYaExisteException, TorneoInexistenteException, UsuarioInexistenteException {
		// preparacion
		Torneo torneo = new Torneo();
		doThrow(TorneoInexistenteException.class).when(servicioTorneoMock).elegirGanador(torneoGanadoId,
				usuarioGanadorId);
		when(servicioTorneoMock.consultarTorneoPorId(torneoGanadoId)).thenReturn(torneo);

		// ejecucion
		final ModelAndView vista = controladorTorneo.elegirGanador(usuarioGanadorId, torneoGanadoId);

		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("errores");

		// verificacion
		verify(servicioTorneoMock, times(1)).elegirGanador(usuarioGanadorId, torneoGanadoId);
		verify(servicioTorneoMock, never()).consultarTorneoPorId(torneoGanadoId);

	}

	@Test
	public void queAlElegirAlGanadorConUsuarioInexistenteDeUnTorneoMeRetorneALaVistaDeErrores()
			throws GanadorYaExisteException, TorneoInexistenteException, UsuarioInexistenteException {
		// preparacion
		Torneo torneo = new Torneo();
		doThrow(UsuarioInexistenteException.class).when(servicioTorneoMock).elegirGanador(torneoGanadoId,
				usuarioGanadorId);
		when(servicioTorneoMock.consultarTorneoPorId(torneoGanadoId)).thenReturn(torneo);

		// ejecucion
		final ModelAndView vista = controladorTorneo.elegirGanador(usuarioGanadorId, torneoGanadoId);

		// comprobacion
		assertThat(vista.getViewName()).isEqualTo("errores");

		// verificacion
		verify(servicioTorneoMock, times(1)).elegirGanador(usuarioGanadorId, torneoGanadoId);
		verify(servicioTorneoMock, never()).consultarTorneoPorId(torneoGanadoId);

	}
	
	
}
