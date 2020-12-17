package ar.edu.unlam.tallerweb1.servicios;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.CupoExcedidoException;
import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.GanadorYaExisteException;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.InmuebleInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.ParticipanteDuplicadoException;
import ar.edu.unlam.tallerweb1.modelo.ParticipanteInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.TorneoInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.UsuarioInexistenteException;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioInmueble;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTorneo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

import static org.mockito.Mockito.*;

public class ServicioTorneoTest {

	private RepositorioTorneo repositorioTorneoMock = mock(RepositorioTorneo.class);
	private RepositorioUsuario repositorioUsuarioMock = mock(RepositorioUsuario.class);
	private RepositorioInmueble repositorioInmuebleMock = mock(RepositorioInmueble.class);

	private ServicioTorneo servicio = new ServicioTorneoImpl(repositorioTorneoMock, repositorioUsuarioMock,
			repositorioInmuebleMock);

	private Direccion crearDireccion() {
		Direccion direccion = new Direccion();
		direccion.setCalle("Libertad");
		direccion.setNumero(325);
		direccion.setLatitud(0.6119);
		direccion.setLongitud(1.0255);

		return direccion;
	}

	private Inmueble crearInmueble() {

		Inmueble inmueble = new Inmueble();
		Direccion direccion = crearDireccion();
		inmueble.setId(1L);
		inmueble.setNombre("Depto Gamer 1");
		inmueble.setDisponible(true);
		inmueble.setPrecio(3000d);
		inmueble.setFoto("foto");
		inmueble.setDireccion(direccion);

		return inmueble;
	}

	private Usuario crearUsuario() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setEmail("user@mail.com");
		usuario.setTorGanados(0);

		return usuario;
	}

	private Torneo crearTorneo() {

		Torneo torneo = new Torneo();
		torneo.setId(1L);
		torneo.setCategoria("deporte");
		torneo.setJuego("fifa");
		torneo.setCupo(2);
		torneo.setEstadoCompleto(false);
		torneo.setFecha("12/04/2021");
		torneo.setFoto("foto");
		torneo.setHorario("10:30hs");
		torneo.setInscriptos(0);

		return torneo;
	}

	@Test
	public void queSePuedaGuardarUnTorneo() throws InmuebleInexistenteException, UsuarioInexistenteException {

		// preparacion
		Inmueble inmueble = crearInmueble();
		Usuario creador = crearUsuario();

		Torneo torneo = crearTorneo();

		// ejecucion
		when(repositorioInmuebleMock.consultarInmueblePorId(inmueble.getId())).thenReturn(inmueble);
		when(repositorioUsuarioMock.consultarUsuarioPorId(creador.getId())).thenReturn(creador);
		servicio.guardarTorneo(torneo, creador.getId(), inmueble.getId());

		// comprobacion
		verify(repositorioTorneoMock, times(1)).guardarTorneo(torneo);
	}

	@Test(expected = InmuebleInexistenteException.class)
	public void queLanzeInmuebleInexistenteExceptionSiElIdDelInmuebleEsNuloCuandoSeQuieraGuardarUnTorneo()
			throws InmuebleInexistenteException, UsuarioInexistenteException {

		// preparacion
		Inmueble inmueble = crearInmueble();
		inmueble.setId(null);
		Usuario creador = crearUsuario();

		Torneo torneo = crearTorneo();

		// ejecucion
		when(repositorioInmuebleMock.consultarInmueblePorId(inmueble.getId())).thenReturn(inmueble);
		when(repositorioUsuarioMock.consultarUsuarioPorId(creador.getId())).thenReturn(creador);
		servicio.guardarTorneo(torneo, creador.getId(), inmueble.getId());

		// comprobacion
		verify(repositorioTorneoMock, never()).guardarTorneo(torneo);
	}

	@Test(expected = InmuebleInexistenteException.class)
	public void queLanzeInmuebleInexistenteExceptionSiElInmuebleNoExisteCuandoSeQuieraGuardarUnTorneo()
			throws InmuebleInexistenteException, UsuarioInexistenteException {

		// preparacion
		Inmueble inmueble = crearInmueble();
		Usuario creador = crearUsuario();
		Torneo torneo = crearTorneo();

		// ejecucion
		when(repositorioInmuebleMock.consultarInmueblePorId(inmueble.getId())).thenReturn(null);
		when(repositorioUsuarioMock.consultarUsuarioPorId(creador.getId())).thenReturn(creador);
		servicio.guardarTorneo(torneo, creador.getId(), inmueble.getId());

		// comprobacion
		verify(repositorioTorneoMock, never()).guardarTorneo(torneo);
	}

	@Test(expected = UsuarioInexistenteException.class)
	public void queLanzeUsuarioInexistenteExceptionSiElUsuarioCreadorNoExisteCuandoSeQuieraGuardarUnTorneo()
			throws InmuebleInexistenteException, UsuarioInexistenteException {

		// preparacion
		Inmueble inmueble = crearInmueble();
		Usuario creador = crearUsuario();

		Torneo torneo = crearTorneo();

		// ejecucion
		when(repositorioInmuebleMock.consultarInmueblePorId(inmueble.getId())).thenReturn(inmueble);
		when(repositorioUsuarioMock.consultarUsuarioPorId(creador.getId())).thenReturn(null);
		servicio.guardarTorneo(torneo, creador.getId(), inmueble.getId());

		// comprobacion
		verify(repositorioTorneoMock, never()).guardarTorneo(torneo);
	}

	@Test
	public void listarTorneosConDistancia() {

		// preparacion
		Direccion direccion = crearDireccion();
		Inmueble inmueble = crearInmueble();
		Torneo torneo = crearTorneo();
		torneo.setInmuebleDelTorneo(inmueble);
		Usuario usuario = crearUsuario();
		usuario.setDireccion(direccion);

		List<Torneo> torneos = new LinkedList<Torneo>();
		torneos.add(torneo);

		// ejecucion
		when(repositorioTorneoMock.listarTodosLosTorneos()).thenReturn(torneos);
		when(repositorioUsuarioMock.consultarUsuarioPorId(usuario.getId())).thenReturn(usuario);
		servicio.listarTorneosConDistancia(usuario.getId());

		// comprobacion
		verify(repositorioTorneoMock, times(1)).modificarTorneo(torneo);
	}

	@Test
	public void listarTodosLosTorneos() {

		// preparacion
		Torneo torneo = crearTorneo();
		List<Torneo> torneos = new LinkedList<Torneo>();
		torneos.add(torneo);

		// ejecucion
		when(repositorioTorneoMock.listarTodosLosTorneos()).thenReturn(torneos);
		servicio.listarTodosLosTorneos();

		// comprobacion
		verify(repositorioTorneoMock, times(1)).listarTodosLosTorneos();
	}

	@Test
	public void queSeBusqueTorneoSegunCategoriaYJuego() {
//preparacion
		Torneo torneo = crearTorneo();
		String categoria = "deporte";
		String juego = "fifa";
		List<Torneo> torneos = new LinkedList<Torneo>();
		torneos.add(torneo);

		// ejecucion
		when(repositorioTorneoMock.buscarTorneo(categoria, juego)).thenReturn(torneos);
		servicio.buscarTorneo(categoria, juego);

		// comprobacion
		verify(repositorioTorneoMock, times(1)).buscarTorneo(categoria, juego);
	}

	@Test
	public void queConsulteUnTorneoParaPoderVerSusDetalles() {

		// preparacion
		Torneo torneo = crearTorneo();

		// ejecucion
		when(repositorioTorneoMock.consultarTorneoPorId(torneo.getId())).thenReturn(torneo);
		servicio.consultarTorneoPorId(torneo.getId());

		// comprobacion
		verify(repositorioTorneoMock, times(1)).consultarTorneoPorId(torneo.getId());
	}

	@Test
	public void queSePuedaAgregarParticipanteAUnTorneo() throws ParticipanteDuplicadoException, CupoExcedidoException,
			TorneoInexistenteException, UsuarioInexistenteException {

		// preparacion
		Torneo torneo = crearTorneo();
		Usuario usuario = crearUsuario();

		// ejecucion
		when(repositorioTorneoMock.consultarTorneoPorId(torneo.getId())).thenReturn(torneo);
		when(repositorioUsuarioMock.consultarUsuarioPorId(usuario.getId())).thenReturn(usuario);
		servicio.agregarParticipante(torneo.getId(), usuario.getId());

		// comprobacion
		verify(repositorioTorneoMock, times(1)).modificarTorneo(torneo);
		verify(repositorioUsuarioMock, times(1)).modificarUsuario(usuario);

	}

	@Test(expected = TorneoInexistenteException.class)
	public void queLanzeTorneoInexistenteExceptionCuandoSeQuieraAgregarParticipanteAUnTorneoInexistente()
			throws ParticipanteDuplicadoException, CupoExcedidoException, TorneoInexistenteException,
			UsuarioInexistenteException {

		// preparacion
		Torneo torneo = crearTorneo();

		Usuario usuario = crearUsuario();

		// ejecucion
		when(repositorioTorneoMock.consultarTorneoPorId(torneo.getId())).thenReturn(null);
		when(repositorioUsuarioMock.consultarUsuarioPorId(usuario.getId())).thenReturn(usuario);
		servicio.agregarParticipante(torneo.getId(), usuario.getId());

		// comprobacion
		verify(repositorioTorneoMock, never()).modificarTorneo(torneo);
		verify(repositorioUsuarioMock, never()).modificarUsuario(usuario);

	}

	@Test(expected = UsuarioInexistenteException.class)
	public void queLanzeUsuarioInexistenteExceptionCuandoSeQuieraAgregarUnUsuarioInexistenteAUnTorneo()
			throws ParticipanteDuplicadoException, CupoExcedidoException, TorneoInexistenteException,
			UsuarioInexistenteException {

		// preparacion
		Torneo torneo = crearTorneo();

		Usuario usuario = crearUsuario();

		// ejecucion
		when(repositorioTorneoMock.consultarTorneoPorId(torneo.getId())).thenReturn(torneo);
		when(repositorioUsuarioMock.consultarUsuarioPorId(usuario.getId())).thenReturn(null);
		servicio.agregarParticipante(torneo.getId(), usuario.getId());

		// comprobacion
		verify(repositorioTorneoMock, never()).modificarTorneo(torneo);
		verify(repositorioUsuarioMock, never()).modificarUsuario(usuario);

	}

	@Test(expected = ParticipanteDuplicadoException.class)
	public void queLanzeParticipanteDuplicadoExceptionCuandoSeQuieraAgregarUnParticipanteAUnTorneoQueYaEstaParticipando()
			throws ParticipanteDuplicadoException, CupoExcedidoException, TorneoInexistenteException,
			UsuarioInexistenteException {

		// preparacion
		Torneo torneo = crearTorneo();

		Usuario usuario = crearUsuario();

		torneo.agregarParticipante(usuario);

		// ejecucion
		when(repositorioTorneoMock.consultarTorneoPorId(torneo.getId())).thenReturn(torneo);
		when(repositorioUsuarioMock.consultarUsuarioPorId(usuario.getId())).thenReturn(usuario);
		servicio.agregarParticipante(torneo.getId(), usuario.getId());

		// comprobacion
		verify(repositorioTorneoMock, never()).modificarTorneo(torneo);
		verify(repositorioUsuarioMock, never()).modificarUsuario(usuario);

	}

	@Test(expected = CupoExcedidoException.class)
	public void queLanzeCupoExcedidoExceptionCuandoSeQuieraAgregarUnParticipanteAUnTorneoConCupoLleno()
			throws ParticipanteDuplicadoException, CupoExcedidoException, TorneoInexistenteException,
			UsuarioInexistenteException {

		// preparacion
		Torneo torneo = crearTorneo();
		torneo.setCupo(2);
		torneo.setInscriptos(2);
		Usuario usuario = crearUsuario();

		// ejecucion
		when(repositorioTorneoMock.consultarTorneoPorId(torneo.getId())).thenReturn(torneo);
		when(repositorioUsuarioMock.consultarUsuarioPorId(usuario.getId())).thenReturn(usuario);
		servicio.agregarParticipante(torneo.getId(), usuario.getId());

		// comprobacion
		verify(repositorioTorneoMock, never()).modificarTorneo(torneo);
		verify(repositorioUsuarioMock, never()).modificarUsuario(usuario);

	}

	@Test
	public void queSePuedaEliminarUnParticipanteDeUnTorneo()
			throws ParticipanteInexistenteException, TorneoInexistenteException, UsuarioInexistenteException {
		// preparacion
		Usuario usuario = crearUsuario();
		Torneo torneo = crearTorneo();
		usuario.participarEnTorneo(torneo);

		torneo.agregarParticipante(usuario);

		// ejecucion
		when(repositorioTorneoMock.consultarTorneoPorId(torneo.getId())).thenReturn(torneo);
		when(repositorioUsuarioMock.consultarUsuarioPorId(usuario.getId())).thenReturn(usuario);
		servicio.eliminarParticipante(torneo.getId(), usuario.getId());
		;

		// comprobacion
		verify(repositorioTorneoMock, times(2)).modificarTorneo(torneo);
		verify(repositorioUsuarioMock, times(1)).modificarUsuario(usuario);

	}

	@Test(expected = TorneoInexistenteException.class)
	public void queLanzaTorneoInexistenteExceptionSiElTorneoParaDesubscribirseNoExiste()
			throws ParticipanteInexistenteException, TorneoInexistenteException, UsuarioInexistenteException {
		// preparacion
		Usuario usuario = crearUsuario();
		Torneo torneo = crearTorneo();

		// ejecucion
		when(repositorioTorneoMock.consultarTorneoPorId(torneo.getId())).thenReturn(null);
		when(repositorioUsuarioMock.consultarUsuarioPorId(usuario.getId())).thenReturn(usuario);
		servicio.eliminarParticipante(torneo.getId(), usuario.getId());
		;

		// comprobacion
		verify(repositorioTorneoMock, never()).modificarTorneo(torneo);
		verify(repositorioUsuarioMock, never()).modificarUsuario(usuario);

	}

	@Test(expected = UsuarioInexistenteException.class)
	public void queLanzaUsuarioInexistenteExceptionSiElUsuarioParaDesubscribirseDelTorneoNoExiste()
			throws ParticipanteInexistenteException, TorneoInexistenteException, UsuarioInexistenteException {
		// preparacion
		Usuario usuario = crearUsuario();
		Torneo torneo = crearTorneo();

		// ejecucion
		when(repositorioTorneoMock.consultarTorneoPorId(torneo.getId())).thenReturn(torneo);
		when(repositorioUsuarioMock.consultarUsuarioPorId(usuario.getId())).thenReturn(null);
		servicio.eliminarParticipante(torneo.getId(), usuario.getId());
		;

		// comprobacion
		verify(repositorioTorneoMock, never()).modificarTorneo(torneo);
		verify(repositorioUsuarioMock, never()).modificarUsuario(usuario);

	}

	@Test(expected = ParticipanteInexistenteException.class)
	public void queLanzaParticipanteInexistenteExceptionSiElTorneoNoContieneAlParticipanteQueSeQuiereEliminar()
			throws ParticipanteInexistenteException, TorneoInexistenteException, UsuarioInexistenteException {
		// preparacion
		Usuario usuario = crearUsuario();
		Torneo torneo = crearTorneo();
		usuario.participarEnTorneo(torneo);
		// ejecucion
		when(repositorioTorneoMock.consultarTorneoPorId(torneo.getId())).thenReturn(torneo);
		when(repositorioUsuarioMock.consultarUsuarioPorId(usuario.getId())).thenReturn(usuario);
		servicio.eliminarParticipante(torneo.getId(), usuario.getId());
		;

		// comprobacion
		verify(repositorioTorneoMock, never()).modificarTorneo(torneo);
		verify(repositorioUsuarioMock, never()).modificarUsuario(usuario);

	}

	@Test(expected = TorneoInexistenteException.class)
	public void queLanzaTorneoInexistenteExceptionSiElParticipanteParaDesubscribirseNoEstaParticipandoDelTorneo()
			throws ParticipanteInexistenteException, TorneoInexistenteException, UsuarioInexistenteException {
		// preparacion
		Usuario usuario = crearUsuario();
		Torneo torneo = crearTorneo();

		torneo.agregarParticipante(usuario);

		// ejecucion
		when(repositorioTorneoMock.consultarTorneoPorId(torneo.getId())).thenReturn(torneo);
		when(repositorioUsuarioMock.consultarUsuarioPorId(usuario.getId())).thenReturn(usuario);
		servicio.eliminarParticipante(torneo.getId(), usuario.getId());
		
		// comprobacion
		verify(repositorioTorneoMock, never()).modificarTorneo(torneo);
		verify(repositorioUsuarioMock, never()).modificarUsuario(usuario);

	}

	@Test
	public void listarParticipantesDelTorneo() {
		
		//preparacion
		Usuario usuario = new Usuario();
		Torneo torneo = crearTorneo();
		torneo.setId(1L);
		List<Usuario> usuarios = new LinkedList<Usuario>();
		usuarios.add(usuario);
		when(repositorioTorneoMock.consultarTorneoPorId(torneo.getId())).thenReturn(torneo);
		when(repositorioUsuarioMock.listarTodosLosUsuarios()).thenReturn(usuarios);
		

		// ejecucion
		servicio.listarParticipantesDelTorneo(torneo.getId());

		// comprobacion
		verify(repositorioTorneoMock, times(1)).consultarTorneoPorId(torneo.getId());
		verify(repositorioUsuarioMock, times(1)).listarTodosLosUsuarios();
	}

	@Test
	public void queSePuedaElegirGanador()
			throws GanadorYaExisteException, TorneoInexistenteException, UsuarioInexistenteException {

		// preparacion
		Torneo torneo = crearTorneo();
		Usuario ganador = crearUsuario();

		// ejecucion
		when(repositorioTorneoMock.consultarTorneoPorId(torneo.getId())).thenReturn(torneo);
		when(repositorioUsuarioMock.consultarUsuarioPorId(ganador.getId())).thenReturn(ganador);
		servicio.elegirGanador(torneo.getId(), ganador.getId());

		// comprobacion
		verify(repositorioTorneoMock, times(1)).modificarTorneo(torneo);
		verify(repositorioUsuarioMock, times(1)).modificarUsuario(ganador);

	}

	@Test(expected = TorneoInexistenteException.class)
	public void queLanzeTorneoInexistenteExceptionSiSeQuiereElegirGanadorDeUnTorneoInexistente()
			throws GanadorYaExisteException, TorneoInexistenteException, UsuarioInexistenteException {

		// preparacion
		Torneo torneo = crearTorneo();
		Usuario ganador = crearUsuario();

		// ejecucion
		when(repositorioTorneoMock.consultarTorneoPorId(torneo.getId())).thenReturn(null);
		when(repositorioUsuarioMock.consultarUsuarioPorId(ganador.getId())).thenReturn(ganador);
		servicio.elegirGanador(torneo.getId(), ganador.getId());

		// comprobacion
		verify(repositorioTorneoMock, never()).modificarTorneo(torneo);
		verify(repositorioUsuarioMock, never()).modificarUsuario(ganador);

	}

	@Test(expected = UsuarioInexistenteException.class)
	public void queLanzeUsuarioInexistenteExceptionSiSeQuiereElegirUnUsuarioInexistenteComoGanador()
			throws GanadorYaExisteException, TorneoInexistenteException, UsuarioInexistenteException {

		// preparacion
		Torneo torneo = crearTorneo();
		Usuario ganador = crearUsuario();

		// ejecucion
		when(repositorioTorneoMock.consultarTorneoPorId(torneo.getId())).thenReturn(torneo);
		when(repositorioUsuarioMock.consultarUsuarioPorId(ganador.getId())).thenReturn(null);
		servicio.elegirGanador(torneo.getId(), ganador.getId());

		// comprobacion
		verify(repositorioTorneoMock, never()).modificarTorneo(torneo);
		verify(repositorioUsuarioMock, never()).modificarUsuario(ganador);

	}

	@Test(expected = GanadorYaExisteException.class)
	public void queGanadorYaExisteExceptionSiSeQuiereElegirGanadorDeUnTorneoQueYaTieneUnGanador()
			throws GanadorYaExisteException, TorneoInexistenteException, UsuarioInexistenteException {

		// preparacion
		Torneo torneo = crearTorneo();
		Usuario ganador = crearUsuario();
		torneo.setGanador(ganador);
		// ejecucion
		when(repositorioTorneoMock.consultarTorneoPorId(torneo.getId())).thenReturn(torneo);
		when(repositorioUsuarioMock.consultarUsuarioPorId(ganador.getId())).thenReturn(ganador);
		servicio.elegirGanador(torneo.getId(), ganador.getId());

		// comprobacion
		verify(repositorioTorneoMock, never()).modificarTorneo(torneo);
		verify(repositorioUsuarioMock, never()).modificarUsuario(ganador);

	}

	@Test
	public void consultarTorneoPorId() {
		// preparacion
		Torneo torneo = crearTorneo();

		// ejecucion
		servicio.consultarTorneoPorId(torneo.getId());

		// comprobacion
		verify(repositorioTorneoMock, times(1)).consultarTorneoPorId(torneo.getId());
	}

	@Test
	public void ordenarTorneosSegunDistancia() {
		// preparacion
		Torneo torneo = crearTorneo();
		List<Torneo> torneos = new LinkedList<Torneo>();
		torneos.add(torneo);

		// ejecucion
		when(repositorioTorneoMock.ordenarTorneosSegunDistancia()).thenReturn(torneos);
		servicio.ordenarTorneosSegunDistancia();

		// comprobacion
		verify(repositorioTorneoMock, times(1)).ordenarTorneosSegunDistancia();
	}

	@Test
	public void filtrarTorneosPorDistancia() {
		// preparacion
		Torneo torneo = crearTorneo();
		Double desdeKm=15d;
		Double hastaKm=20d;
		List<Torneo> torneos = new LinkedList<Torneo>();
		torneos.add(torneo);

		// ejecucion
		when(repositorioTorneoMock.filtrarTorneosPorDistancia(desdeKm, hastaKm)).thenReturn(torneos);
		servicio.filtrarTorneosPorDistancia(15d, 20d);

		// comprobacion
		verify(repositorioTorneoMock,times(1)).filtrarTorneosPorDistancia(desdeKm, hastaKm);

	}
}
