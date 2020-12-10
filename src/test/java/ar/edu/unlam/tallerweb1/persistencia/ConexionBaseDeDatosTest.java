package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Ciudad;
import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.Provincia;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Ruta;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

// Clase que prueba la conexion a la base de datos. Hereda de SpringTest por lo que corre dentro del contexto
// de spring
public class ConexionBaseDeDatosTest extends SpringTest {

	@Test
	@Transactional
	@Rollback
	public void pruebaConexion() {
		assertThat(session().isConnected()).isTrue();
	}

	@Transactional
	@Rollback
	private Provincia provincia() {
		Provincia provincia = new Provincia();
		provincia.setNombre("Buenos Aires");
		session().save(provincia);
		return provincia;
	}

	@Transactional
	@Rollback
	private Ciudad ciudad() {
		Ciudad ciudad = new Ciudad();
		Provincia provincia = provincia();
		ciudad.setNombre("Cañuelas");
		ciudad.setCodigoPostal("B1814");
		ciudad.setProvincia(provincia);
		session().save(ciudad);
		return ciudad;
	}

	@Transactional
	@Rollback
	private Direccion direccionInmueble() {
		Direccion direccionInmueble = new Direccion();
		Ciudad ciudad = ciudad();
		direccionInmueble.setCalle("Libertad");
		direccionInmueble.setNumero(325);
		direccionInmueble.setCiudad(ciudad);
		session().save(direccionInmueble);
		return direccionInmueble;

	}

	@Transactional
	@Rollback
	private Direccion direccionUsuario() {
		Direccion direccionUsuario = new Direccion();
		Ciudad ciudad = ciudad();
		direccionUsuario.setCalle("Rivadavia");
		direccionUsuario.setNumero(1356);
		direccionUsuario.setCiudad(ciudad);
		session().save(direccionUsuario);
		return direccionUsuario;

	}
	
	@Test
	public void VerificarQueLaRutaDeLasImagenesDelInmuebleSeanCorrectas () {
		Ruta ruta = new Ruta();
		
		String rutaEsperada =  "C://Producto//inmuebles";
		
		assertThat(ruta.getRutaInmueble()).isEqualTo(rutaEsperada);
		

	}
	

	@Transactional
	@Rollback
	private Usuario usuario() {
		Usuario usuario = new Usuario();
		Direccion direccionUsuario = direccionUsuario();
		usuario.setNombre("Brenda");
		usuario.setApellido("Daffunchio");
		usuario.setEmail("bren@gmail.com");
		usuario.setPassword("1234");
		usuario.setRol("admin");
		usuario.setTorGanados(2);
		usuario.setDireccion(direccionUsuario);
		session().save(usuario);
		return usuario;

	}

	@Transactional
	@Rollback
	private Torneo torneo() {

		Torneo torneo = new Torneo();
		Inmueble inmueble = inmueble();
		torneo.setCategoria("deporte");
		torneo.setJuego("fifa");
		torneo.setCupo(2);
		torneo.setEstadoCompleto(false);
		torneo.setFecha("12/04/2021");
		torneo.setFoto("foto");
		torneo.setHorario("10:30hs");
		torneo.setInscriptos(0);
		torneo.setInmuebleDelTorneo(inmueble);
		session().save(torneo);
		return torneo;
	}

	@Transactional
	@Rollback
	private Inmueble inmueble() {

		Inmueble inmueble = new Inmueble();
		Direccion direccionInmueble = direccionInmueble();
		inmueble.setDireccion(direccionInmueble);
		inmueble.setNombre("Depto gamer");
		inmueble.setDisponible(true);
		inmueble.setFoto("foto");
		inmueble.setPrecio(2000d);
		session().save(inmueble);
		return inmueble;
	}

	// test del repositorio usuario
	@Test
	@Transactional
	@Rollback
	public void crearUsuarioConRolAdmin() {
		Usuario usuario = usuario();

		assertThat(usuario.getId()).isNotNull();
	}

	@Test
	@Transactional
	@Rollback
	public void crearUsuarioConRolInvitado() {
		Usuario usuario = usuario();
		usuario.setRol("invitado");
		session().update(usuario);

		assertThat(usuario.getId()).isNotNull();
	}

	@Test
	@Transactional
	@Rollback
	public void mostrarListaInmueblesAlquiladosPorUnUsuario() {
		Usuario usuario = usuario();
		Inmueble inmueble = inmueble();
		inmueble.setInquilino(usuario);

		Long usuarioId = usuario.getId();

		session().update(inmueble);

		Criteria criteria = session().getSession().createCriteria(Inmueble.class)
				.add(Restrictions.eq("inquilino.id", usuarioId));

		assertThat(criteria.list()).hasSize(1);
		assertThat(criteria.list()).isNotEmpty();

	}

	@Test
	@Transactional
	@Rollback
	public void mostrarListaTorneosQueParticipaUnUsuario() {
		Usuario usuario = usuario();
		Torneo torneo = torneo();
		torneo.setCreador(usuario);
		usuario.participarEnTorneo(torneo);
		session().update(usuario);

		assertThat(usuario.getTorneosParticipa()).hasSize(1);
		assertThat(usuario.getTorneosParticipa()).isNotEmpty();

	}

	@Test
	@Transactional
	@Rollback
	public void mostrarListaTorneosQueCreoUnUsuario() {
		Usuario usuario = usuario();

		Torneo torneo = torneo();
		torneo.setCreador(usuario);
		session().update(torneo);
		Long usuarioId = usuario.getId();
		Criteria criteria = session().getSession().createCriteria(Torneo.class)
				.add(Restrictions.eq("creador.id", usuarioId));

		assertThat(criteria.list()).isNotEmpty();
		assertThat(criteria.list()).hasSize(1);
	}

	@Test
	@Transactional
	@Rollback
	public void mostrarUsuariosMasGanadores() {
		Usuario usuario = usuario();
		usuario.setTorGanados(3);
		session().update(usuario);
		Criteria criteria = session().getSession().createCriteria(Usuario.class).add(Restrictions.gt("torGanados", 0))
				.addOrder(Order.desc("torGanados"));

		assertThat(criteria.list()).hasSize(1);
		assertThat(criteria.list()).isNotEmpty();
	}

	@Test
	@Transactional
	@Rollback
	public void actualizarUsuario() {
		Usuario usuario = usuario();

		usuario.setApellido("Perez");
		session().update(usuario);

		assertThat(usuario.getApellido()).isEqualTo("Perez");
	}

	@Test
	@Transactional
	@Rollback
	public void consultarUsuario() {
		Usuario usuario = usuario();

		Criteria criteria = session().getSession().createCriteria(Usuario.class)
				.add(Restrictions.eq("email", usuario.getEmail()))
				.add(Restrictions.eq("password", usuario.getPassword()));

		assertThat(criteria.uniqueResult()).isNotNull();
	}

	@Test
	@Transactional
	@Rollback
	public void consultarUsuarioPorId() {
		Usuario usuario = usuario();

		Long usuarioId = usuario.getId();
		Usuario usuarioBuscado = session().get(Usuario.class, usuarioId);

		assertThat(usuarioBuscado.getId()).isNotNull();
		assertThat(usuario.getId()).isEqualTo(usuarioBuscado.getId());
	}

//test del repositorioInmueble
	@Test
	@Transactional
	@Rollback
	public void crearInmueble() {
		Inmueble inmueble = inmueble();

		assertThat(inmueble.getId()).isNotNull();
	}

	@Test
	@Transactional
	@Rollback
	public void buscarInmueblePorProvinciaYCiudad() {
		Inmueble inmueble = inmueble();
		Long provinciaId = (long) 1;
		String nombreCiudad = "Cañuelas";
		
		Criteria criteria = session().getSession().createCriteria(Inmueble.class);

		criteria.createAlias("direccion", "direccionBuscada");

		criteria.createAlias("direccionBuscada.ciudad", "ciudad");
		criteria.createAlias("ciudad.provincia", "provincia");

		if (provinciaId != null && provinciaId != 0) {
			criteria.add(Restrictions.like("provincia.id", provinciaId));
		}

		if (nombreCiudad != null && nombreCiudad != "") {

			criteria.add(Restrictions.like("ciudad.nombre", nombreCiudad, MatchMode.ANYWHERE));
		}

		List<Inmueble> inmuebles = criteria.list();
		assertThat(inmuebles).isNotEmpty();
		assertThat(inmuebles).hasSize(1);

	}

	@Test
	@Transactional
	@Rollback
	public void buscarInmueblePorProvinciaVaciaYCiudadVaciaYQueDevuelvaTodosLosInmuebles() {
		Inmueble inmueble = inmueble();
		Criteria criteria = session().getSession().createCriteria(Inmueble.class);

		criteria.createAlias("direccion", "direccionBuscada");

		criteria.createAlias("direccionBuscada.ciudad", "ciudad");

		Long provinciaId = 0l;
		String nombreCiudad = "";
		if (provinciaId != null && provinciaId != 0) {
			criteria.add(Restrictions.like("ciudad.provincia.id", provinciaId));
		}

		if (nombreCiudad != null && nombreCiudad != "") {

			criteria.add(Restrictions.like("ciudad.nombre", nombreCiudad, MatchMode.ANYWHERE));
		}

		assertThat(criteria.list()).hasSize(1);
		assertThat(criteria.list()).isNotEmpty();

	}

	@Test
	@Transactional
	@Rollback
	public void mostrarListaDeTodosLosInmuebles() {
		Inmueble inmueble = inmueble();

		Criteria criteria = session().getSession().createCriteria(Inmueble.class)
				.add(Restrictions.eq("disponible", true));

		assertThat(criteria.list()).isNotEmpty();
		assertThat(criteria.list()).hasSize(1);

	}

	@Test
	@Transactional
	@Rollback
	public void mostrarListaVaciaDeTodosLosInmuebles() {

		Criteria criteria = session().getSession().createCriteria(Inmueble.class);

		assertThat(criteria.list()).isEmpty();

	}

	@Test
	@Transactional
	@Rollback
	public void consultarInmueblePorId() {
		Inmueble inmueble = inmueble();

		Long inmuebleId = inmueble.getId();
		Inmueble inmuebleBuscado = session().get(Inmueble.class, inmuebleId);

		assertThat(inmuebleBuscado.getId()).isNotNull();
		assertThat(inmueble.getId()).isEqualTo(inmuebleBuscado.getId());
	}

	@Test
	@Transactional
	@Rollback
	public void actualizarInmueble() {
		Inmueble inmueble = inmueble();

		inmueble.setPrecio(3000d);
		session().update(inmueble);

		assertThat(inmueble.getPrecio()).isEqualTo(3000d);
	}

	// test del repositorio torneo
	@Test
	@Transactional
	@Rollback
	public void crearTorneo() {

		Torneo torneo = torneo();

		assertThat(torneo.getId()).isNotNull();
	}

	@Test
	@Transactional
	@Rollback
	public void mostrarListaDeTodosLosTorneos() {

		Torneo torneo = torneo();

		Criteria criteria = session().getSession().createCriteria(Torneo.class);

		assertThat(criteria.list()).isNotEmpty();
		assertThat(criteria.list()).hasSize(1);

	}

	@Test
	@Transactional
	@Rollback
	public void mostrarListaVaciaDeTodosLosTorneos() {

		Criteria criteria = session().getSession().createCriteria(Torneo.class);

		assertThat(criteria.list()).isEmpty();

	}

	@Test
	@Transactional
	@Rollback
	public void buscarTorneosPorCategoriaYJuego() {
		Torneo torneo = torneo();
		String categoria = "deporte";
		String juego = "fifa";
		Criteria criteria = session().getSession().createCriteria(Torneo.class);

		if (categoria != null && !categoria.equals("")) {
			criteria.add(Restrictions.like("categoria", categoria));

		}

		if (juego != null && !juego.equals("")) {
			criteria.add(Restrictions.like("juego", juego, MatchMode.ANYWHERE));
		}
		assertThat(criteria.list()).isNotEmpty();
		assertThat(criteria.list()).hasSize(1);

	}

	@Test
	@Transactional
	@Rollback
	public void buscarTorneosPorCategoriaVaciaYJuegoVacioYQueDevuelvaTodosLosTorneos() {
		Torneo torneo = torneo();
		String categoria = "";
		String juego = "";
		Criteria criteria = session().getSession().createCriteria(Torneo.class);

		if (categoria != null && !categoria.equals("")) {
			criteria.add(Restrictions.like("categoria", categoria));

		}

		if (juego != null && !juego.equals("")) {
			criteria.add(Restrictions.like("juego", juego, MatchMode.ANYWHERE));
		}

		assertThat(criteria.list()).isNotEmpty();
		assertThat(criteria.list()).hasSize(1);

	}

	@Test
	@Transactional
	@Rollback
	public void mostrarListaParticipantesDelTorneo() {
		Usuario usuario = usuario();
		Torneo torneo = torneo();
		torneo.agregarParticipante(usuario);
		;
		session().update(torneo);

		assertThat(torneo.getParticipantes()).hasSize(1);
		assertThat(torneo.getParticipantes()).isNotEmpty();
		assertThat(torneo.getInscriptos()).isEqualTo(1);

	}

	@Test
	@Transactional
	@Rollback
	public void actualizarTorneo() {

		Torneo torneo = torneo();

		torneo.setCupo(5);
		session().update(torneo);

		assertThat(torneo.getCupo()).isEqualTo(5);

	}

	@Test
	@Transactional
	@Rollback
	public void consultarTorneoPorId() {
		Torneo torneo = torneo();

		Long torneoId = torneo.getId();
		Torneo torneoBuscado = session().get(Torneo.class, torneoId);

		assertThat(torneoBuscado.getId()).isNotNull();
		assertThat(torneo.getId()).isEqualTo(torneoBuscado.getId());

	}

	// test de repositorio Provincia

	@Test
	@Transactional
	@Rollback
	public void mostrarListaDeProvincias() {
		Provincia buenosAires = provincia();

		Provincia Formosa = provincia();
		Formosa.setNombre("Formosa");
		session().update(Formosa);

		Criteria criteria = session().getSession().createCriteria(Provincia.class);

		assertThat(criteria.list()).isNotEmpty();
		assertThat(criteria.list()).hasSize(2);

	}
	// test de repositorio Ciudad

	@Test
	@Transactional
	@Rollback
	public void mostrarListaDeCiudades() {

		Ciudad canuelas = ciudad();

		Criteria criteria = session().getSession().createCriteria(Ciudad.class);

		assertThat(criteria.list()).isNotEmpty();
		assertThat(criteria.list()).hasSize(1);
	}
}