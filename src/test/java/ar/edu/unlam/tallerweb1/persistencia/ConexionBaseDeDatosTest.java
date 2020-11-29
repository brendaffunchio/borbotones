package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.hibernate.Criteria;
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

	// test del repositorio usuario
	@Test
	@Transactional
	@Rollback
	public void crearUsuarioConRolAdmin() {
		Usuario usuario = new Usuario();
		Direccion direccion = new Direccion();
		usuario.setNombre("Brenda");
		usuario.setApellido("Daffunchio");
		usuario.setEmail("bren@gmail.com");
		usuario.setPassword("1234");
		usuario.setRol("admin");
		usuario.setTorGanados(2);
		usuario.setDireccion(direccion);
		session().save(usuario);
		assertThat(usuario.getId()).isNotNull();
	}

	@Test
	@Transactional
	@Rollback
	public void crearUsuarioConRolInvitado() {
		Usuario usuario = new Usuario();
		Direccion direccion = new Direccion();
		usuario.setNombre("Brenda");
		usuario.setApellido("Daffunchio");
		usuario.setEmail("bren@gmail.com");
		usuario.setPassword("1234");
		usuario.setRol("invitado");
		usuario.setTorGanados(2);
		usuario.setDireccion(direccion);
		session().save(usuario);
		assertThat(usuario.getId()).isNotNull();
	}

	@Test
	@Transactional
	@Rollback
	public void mostrarListaInmueblesAlquiladosPorUnUsuario() {
		Usuario usuario = new Usuario();
		Direccion direccionUsuario = new Direccion();
		direccionUsuario.setCalle("Libertad");
		direccionUsuario.setNumero(325);
		session().save(direccionUsuario);
		Direccion direccionInmueble = new Direccion();
		direccionInmueble.setCalle("Rivadavia");
		direccionInmueble.setNumero(1356);
		session().save(direccionInmueble);
		usuario.setNombre("Brenda");
		usuario.setApellido("Daffunchio");
		usuario.setEmail("bren@gmail.com");
		usuario.setPassword("1234");
		usuario.setRol("invitado");
		usuario.setTorGanados(2);
		usuario.setDireccion(direccionUsuario);
		session().save(usuario);
		Long usuarioId = usuario.getId();
		Inmueble inmueble = new Inmueble();
		inmueble.setDireccion(direccionInmueble);
		inmueble.setNombre("Depto gamer");
		inmueble.setDisponible(true);
		inmueble.setFoto("foto");
		inmueble.setPrecio(2000d);
		inmueble.setInquilino(usuario);

		session().save(inmueble);

		Criteria criteria = session().createCriteria(Inmueble.class).add(Restrictions.eq("inquilino.id", usuarioId));

		assertThat(criteria.list()).hasSize(1);
		assertThat(criteria.list()).isNotEmpty();

	}

	@Test
	@Transactional
	@Rollback
	public void mostrarListaTorneosQueParticipaUnUsuario() {
		Usuario usuario = new Usuario();
		Direccion direccion1 = new Direccion();
		direccion1.setCalle("Libertad");
		direccion1.setNumero(325);
		session().save(direccion1);
		usuario.setNombre("Brenda");
		usuario.setApellido("Daffunchio");
		usuario.setEmail("bren@gmail.com");
		usuario.setPassword("1234");
		usuario.setRol("admin");
		usuario.setTorGanados(2);
		usuario.setDireccion(direccion1);
		session().save(usuario);
		Inmueble inmueble = new Inmueble();
		Direccion direccion = new Direccion();
		direccion.setCalle("Rivadavia");
		direccion.setNumero(1356);
		session().save(direccion);
		inmueble.setDireccion(direccion);
		inmueble.setNombre("Depto gamer");
		inmueble.setDisponible(true);
		inmueble.setFoto("foto");
		inmueble.setPrecio(2000d);
		session().save(inmueble);
		Torneo torneo = new Torneo();
		torneo.setCategoria("deporte");
		torneo.setCreador(usuario);
		torneo.setCupo(2);
		torneo.setEstadoCompleto(false);
		torneo.setFecha("12/04/2021");
		torneo.setFoto("foto");
		torneo.setHorario("10:30hs");
		torneo.setInscriptos(0);
		session().save(torneo);
		torneo.agregarParticipante(usuario);
		usuario.participarEnTorneo(torneo);
		session().update(usuario);
		session().update(torneo);

		assertThat(usuario.getTorneosParticipa()).hasSize(1);
		assertThat(usuario.getTorneosParticipa()).isNotEmpty();

	}

	@Test
	@Transactional
	@Rollback
	public void mostrarListaTorneosQueCreoUnUsuario() {
		Usuario usuario = new Usuario();
		Direccion direccion1 = new Direccion();
		direccion1.setCalle("Libertad");
		direccion1.setNumero(325);
		session().save(direccion1);
		usuario.setNombre("Brenda");
		usuario.setApellido("Daffunchio");
		usuario.setEmail("bren@gmail.com");
		usuario.setPassword("1234");
		usuario.setRol("admin");
		usuario.setTorGanados(2);
		usuario.setDireccion(direccion1);
		session().save(usuario);
		Inmueble inmueble = new Inmueble();
		Direccion direccion = new Direccion();
		direccion.setCalle("Rivadavia");
		direccion.setNumero(1356);
		session().save(direccion);
		inmueble.setDireccion(direccion);
		inmueble.setNombre("Depto gamer");
		inmueble.setDisponible(true);
		inmueble.setFoto("foto");
		inmueble.setPrecio(2000d);
		session().save(inmueble);
		Torneo torneo = new Torneo();
		torneo.setCategoria("deporte");
		torneo.setCreador(usuario);
		torneo.setCupo(2);
		torneo.setEstadoCompleto(false);
		torneo.setFecha("12/04/2021");
		torneo.setFoto("foto");
		torneo.setHorario("10:30hs");
		torneo.setInscriptos(0);
		session().save(torneo);
		Long usuarioId=usuario.getId();
		Criteria criteria=session().createCriteria(Torneo.class)
		.add(Restrictions.eq("creador.id",usuarioId));
		
		assertThat(criteria.list()).isNotEmpty();
		assertThat(criteria.list()).hasSize(1);
	}

	@Test
	@Transactional
	@Rollback
	public void mostrarUsuariosMasGanadores() {
		Usuario usuario = new Usuario();
		Direccion direccion = new Direccion();
		direccion.setCalle("Rivadavia");
		direccion.setNumero(1356);
		session().save(direccion);
		usuario.setNombre("Brenda");
		usuario.setApellido("Daffunchio");
		usuario.setEmail("bren@gmail.com");
		usuario.setPassword("1234");
		usuario.setRol("admin");
		usuario.setTorGanados(2);
		usuario.setDireccion(direccion);
		session().save(usuario);
		Criteria criteria= session().createCriteria(Usuario.class)
			.add(Restrictions.gt("torGanados", 0))
			.addOrder(Order.desc("torGanados"));
		
		assertThat(criteria.list()).hasSize(1);
		assertThat(criteria.list()).isNotEmpty();
	}

	@Test
	@Transactional
	@Rollback
	public void actualizarUsuario() {
		Usuario usuario = new Usuario();
		Direccion direccion = new Direccion();
		direccion.setCalle("Rivadavia");
		direccion.setNumero(1356);
		session().save(direccion);
		usuario.setNombre("Brenda");
		usuario.setApellido("Daffunchio");
		usuario.setEmail("bren@gmail.com");
		usuario.setPassword("1234");
		usuario.setRol("admin");
		usuario.setTorGanados(2);
		usuario.setDireccion(direccion);
		session().save(usuario);

		usuario.setApellido("Perez");
		session().update(usuario);
		assertThat(usuario.getApellido()).isEqualTo("Perez");
	}

	@Test
	@Transactional
	@Rollback
	public void consultarUsuario() {
		Usuario usuario = new Usuario();
		Direccion direccion = new Direccion();
		direccion.setCalle("Rivadavia");
		direccion.setNumero(1356);
		session().save(direccion);
		usuario.setNombre("Brenda");
		usuario.setApellido("Daffunchio");
		usuario.setEmail("bren@gmail.com");
		usuario.setPassword("1234");
		usuario.setRol("admin");
		usuario.setTorGanados(2);
		usuario.setDireccion(direccion);
		session().save(usuario);
		
		Criteria criteria=session().createCriteria(Usuario.class)
		.add(Restrictions.eq("email", usuario.getEmail()))
		.add(Restrictions.eq("password", usuario.getPassword()));
		
		assertThat(criteria.uniqueResult()).isNotNull();
	}

	@Test
	@Transactional
	@Rollback
	public void consultarUsuarioPorId() {
		Usuario usuario = new Usuario();
		Direccion direccion = new Direccion();
		direccion.setCalle("Rivadavia");
		direccion.setNumero(1356);
		session().save(direccion);
		usuario.setNombre("Brenda");
		usuario.setApellido("Daffunchio");
		usuario.setEmail("bren@gmail.com");
		usuario.setPassword("1234");
		usuario.setRol("admin");
		usuario.setTorGanados(2);
		usuario.setDireccion(direccion);
		session().save(usuario);
		Long usuarioId=usuario.getId();
		Usuario usuarioBuscado =session().get(Usuario.class, usuarioId);
		
		assertThat(usuarioBuscado.getId()).isNotNull();
		assertThat(usuario.getId()).isEqualTo(usuarioBuscado.getId());
	}

//test del repositorioInmueble
	@Test
	@Transactional
	@Rollback
	public void crearInmueble() {
		Inmueble inmueble = new Inmueble();
		Direccion direccion = new Direccion();
		direccion.setCalle("Rivadavia");
		direccion.setNumero(1356);
		session().save(direccion);
		inmueble.setDireccion(direccion);
		inmueble.setNombre("Depto gamer");
		inmueble.setDisponible(true);
		inmueble.setFoto("foto");
		inmueble.setPrecio(2000d);
		session().save(inmueble);
		assertThat(inmueble.getId()).isNotNull();
	}
	@Test
	@Transactional
	@Rollback
	public void mostrarListaDeTodosLosInmuebles() {
		Inmueble inmueble = new Inmueble();
		Direccion direccion = new Direccion();
		direccion.setCalle("Rivadavia");
		direccion.setNumero(1356);
		session().save(direccion);
		inmueble.setDireccion(direccion);
		inmueble.setNombre("Depto gamer");
		inmueble.setDisponible(true);
		inmueble.setFoto("foto");
		inmueble.setPrecio(2000d);
		session().save(inmueble);
	
		Criteria criteria=session().createCriteria(Inmueble.class)
		.add(Restrictions.eq("disponible", true));
		
		assertThat(criteria.list()).isNotEmpty();
		assertThat(criteria.list()).hasSize(1);
		
	}
	@Test
	@Transactional
	@Rollback
	public void mostrarListaVaciaDeTodosLosInmuebles() {
			
		Criteria criteria=session().createCriteria(Inmueble.class);
		
		assertThat(criteria.list()).isEmpty();
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void consultarInmueblePorId() {
		Inmueble inmueble = new Inmueble();
		Direccion direccion = new Direccion();
		direccion.setCalle("Rivadavia");
		direccion.setNumero(1356);
		session().save(direccion);
		inmueble.setDireccion(direccion);
		inmueble.setNombre("Depto gamer");
		inmueble.setDisponible(true);
		inmueble.setFoto("foto");
		inmueble.setPrecio(2000d);
		session().save(inmueble);
		Long inmuebleId=inmueble.getId();
		Inmueble inmuebleBuscado =session().get(Inmueble.class, inmuebleId);
		
		assertThat(inmuebleBuscado.getId()).isNotNull();
		assertThat(inmueble.getId()).isEqualTo(inmuebleBuscado.getId());
	}
	
	@Test
	@Transactional
	@Rollback
	public void actualizarInmueble() {
		Inmueble inmueble = new Inmueble();
		Direccion direccion = new Direccion();
		direccion.setCalle("Rivadavia");
		direccion.setNumero(1356);
		session().save(direccion);
		inmueble.setDireccion(direccion);
		inmueble.setNombre("Depto gamer");
		inmueble.setDisponible(true);
		inmueble.setFoto("foto");
		inmueble.setPrecio(2000d);
		session().save(inmueble);
		
		inmueble.setPrecio(3000d);
		session().update(inmueble);
		
		assertThat(inmueble.getPrecio()).isEqualTo(3000d);
	}
	
	@Test
	@Transactional
	@Rollback
	public void buscarInmueblePorProvinciaYCiudad() {
		
	}
	
	// test del repositorio torneo
	@Test
	@Transactional
	@Rollback
	public void crearTorneo() {
		Usuario usuario = new Usuario();
		Direccion direccion1 = new Direccion();
		direccion1.setCalle("Libertad");
		direccion1.setNumero(325);
		session().save(direccion1);
		usuario.setNombre("Brenda");
		usuario.setApellido("Daffunchio");
		usuario.setEmail("bren@gmail.com");
		usuario.setPassword("1234");
		usuario.setRol("admin");
		usuario.setTorGanados(2);
		usuario.setDireccion(direccion1);
		session().save(usuario);
		Inmueble inmueble = new Inmueble();
		Direccion direccion = new Direccion();
		direccion.setCalle("Rivadavia");
		direccion.setNumero(1356);
		session().save(direccion);
		inmueble.setDireccion(direccion);
		inmueble.setNombre("Depto gamer");
		inmueble.setDisponible(true);
		inmueble.setFoto("foto");
		inmueble.setPrecio(2000d);
		session().save(inmueble);
		Torneo torneo = new Torneo();
		torneo.setCategoria("deporte");
		torneo.setCreador(usuario);
		torneo.setCupo(2);
		torneo.setEstadoCompleto(false);
		torneo.setFecha("12/04/2021");
		torneo.setFoto("foto");
		torneo.setHorario("10:30hs");
		torneo.setInscriptos(0);
		session().save(torneo);

		assertThat(torneo.getId()).isNotNull();
	}
	@Test
	@Transactional
	@Rollback
	public void mostrarListaDeTodosLosTorneos() {
		Usuario usuario = new Usuario();
		Direccion direccion1 = new Direccion();
		direccion1.setCalle("Libertad");
		direccion1.setNumero(325);
		session().save(direccion1);
		usuario.setNombre("Brenda");
		usuario.setApellido("Daffunchio");
		usuario.setEmail("bren@gmail.com");
		usuario.setPassword("1234");
		usuario.setRol("admin");
		usuario.setTorGanados(2);
		usuario.setDireccion(direccion1);
		session().save(usuario);
		Inmueble inmueble = new Inmueble();
		Direccion direccion = new Direccion();
		direccion.setCalle("Rivadavia");
		direccion.setNumero(1356);
		session().save(direccion);
		inmueble.setDireccion(direccion);
		inmueble.setNombre("Depto gamer");
		inmueble.setDisponible(true);
		inmueble.setFoto("foto");
		inmueble.setPrecio(2000d);
		session().save(inmueble);
		Torneo torneo = new Torneo();
		torneo.setCategoria("deporte");
		torneo.setCreador(usuario);
		torneo.setCupo(2);
		torneo.setEstadoCompleto(false);
		torneo.setFecha("12/04/2021");
		torneo.setFoto("foto");
		torneo.setHorario("10:30hs");
		torneo.setInscriptos(0);
		session().save(torneo);
		
		Criteria criteria=session().createCriteria(Torneo.class);
		
		assertThat(criteria.list()).isNotEmpty();
		assertThat(criteria.list()).hasSize(1);
		
	}
	@Test
	@Transactional
	@Rollback
	public void mostrarListaVaciaDeTodosLosTorneos() {
			
		Criteria criteria=session().createCriteria(Torneo.class);
		
		assertThat(criteria.list()).isEmpty();
		
	}
	@Test
	@Transactional
	@Rollback
	public void mostrarListaTorneosPorCategoriaYJuego() {
		
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void mostrarListaParticipantesDelTorneo() {
		
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void actualizarTorneo() {
		Usuario usuario = new Usuario();
		Direccion direccion1 = new Direccion();
		direccion1.setCalle("Libertad");
		direccion1.setNumero(325);
		session().save(direccion1);
		usuario.setNombre("Brenda");
		usuario.setApellido("Daffunchio");
		usuario.setEmail("bren@gmail.com");
		usuario.setPassword("1234");
		usuario.setRol("admin");
		usuario.setTorGanados(2);
		usuario.setDireccion(direccion1);
		session().save(usuario);
		Inmueble inmueble = new Inmueble();
		Direccion direccion = new Direccion();
		direccion.setCalle("Rivadavia");
		direccion.setNumero(1356);
		session().save(direccion);
		inmueble.setDireccion(direccion);
		inmueble.setNombre("Depto gamer");
		inmueble.setDisponible(true);
		inmueble.setFoto("foto");
		inmueble.setPrecio(2000d);
		session().save(inmueble);
		Torneo torneo = new Torneo();
		torneo.setCategoria("deporte");
		torneo.setCreador(usuario);
		torneo.setCupo(2);
		torneo.setEstadoCompleto(false);
		torneo.setFecha("12/04/2021");
		torneo.setFoto("foto");
		torneo.setHorario("10:30hs");
		torneo.setInscriptos(0);
		session().save(torneo);

		torneo.setCupo(5);
		session().update(torneo);
		
		assertThat(torneo.getCupo()).isEqualTo(5);
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void consultarTorneoPorId() {
		Usuario usuario = new Usuario();
		Direccion direccion1 = new Direccion();
		direccion1.setCalle("Libertad");
		direccion1.setNumero(325);
		session().save(direccion1);
		usuario.setNombre("Brenda");
		usuario.setApellido("Daffunchio");
		usuario.setEmail("bren@gmail.com");
		usuario.setPassword("1234");
		usuario.setRol("admin");
		usuario.setTorGanados(2);
		usuario.setDireccion(direccion1);
		session().save(usuario);
		Inmueble inmueble = new Inmueble();
		Direccion direccion = new Direccion();
		direccion.setCalle("Rivadavia");
		direccion.setNumero(1356);
		session().save(direccion);
		inmueble.setDireccion(direccion);
		inmueble.setNombre("Depto gamer");
		inmueble.setDisponible(true);
		inmueble.setFoto("foto");
		inmueble.setPrecio(2000d);
		session().save(inmueble);
		Torneo torneo = new Torneo();
		torneo.setCategoria("deporte");
		torneo.setCreador(usuario);
		torneo.setCupo(2);
		torneo.setEstadoCompleto(false);
		torneo.setFecha("12/04/2021");
		torneo.setFoto("foto");
		torneo.setHorario("10:30hs");
		torneo.setInscriptos(0);
		session().save(torneo);
		
		Long torneoId=torneo.getId();
		Torneo torneoBuscado = session().get(Torneo.class, torneoId);
		
		assertThat(torneoBuscado.getId()).isNotNull();
		assertThat(torneo.getId()).isEqualTo(torneoBuscado.getId());
		
	}
	
	
	
	
	
}
