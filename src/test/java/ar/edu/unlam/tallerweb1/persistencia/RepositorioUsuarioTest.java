package ar.edu.unlam.tallerweb1.persistencia;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;

import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

public class RepositorioUsuarioTest extends SpringTest {

	@Autowired
	RepositorioUsuario repositorio;

	private Usuario crearUsuario1() {

		Usuario usuario = new Usuario();
		usuario.setNombre("Marta");
		usuario.setApellido("Gomez");
		usuario.setEmail("marta@gmail.com");
		usuario.setPassword("1234");
		usuario.setRol("invitado");
		usuario.setTorGanados(0);

		return usuario;
	}

	private Usuario crearUsuario2() {

		Usuario usuario2 = new Usuario();
		usuario2.setNombre("Jose");
		usuario2.setApellido("Sanchez");
		usuario2.setEmail("jose@gmail.com");
		usuario2.setPassword("1234");
		usuario2.setRol("invitado");
		usuario2.setTorGanados(0);

		return usuario2;
	}

	@Transactional
	@Rollback
	private Inmueble crearInmueble() {

		Inmueble inmueble = new Inmueble();
		inmueble.setNombre("Depto gamer");
		inmueble.setDisponible(true);
		inmueble.setFoto("foto");
		inmueble.setPrecio(2000d);

		session().save(inmueble);

		return inmueble;
	}

	private Torneo crearTorneo() {

		Torneo torneo = new Torneo();
		Inmueble inmueble = crearInmueble();
		torneo.setCategoria("deporte");
		torneo.setJuego("fifa");
		torneo.setCupo(2);
		torneo.setEstadoCompleto(false);
		torneo.setFecha("12/04/2021");
		torneo.setFoto("foto");
		torneo.setHorario("10:30hs");
		torneo.setInscriptos(0);
		torneo.setInmuebleDelTorneo(inmueble);
		return torneo;
	}

	@Test
	@Transactional
	@Rollback
	public void guardarUsuario() {
		// preparacion
		Usuario usuario1 = crearUsuario1();

		// ejecucion
		repositorio.guardarUsuario(usuario1);

		// comprobacion
		Usuario guardado = session().get(Usuario.class, usuario1.getId());
		assertThat(guardado).isNotNull();
	}

	@Test
	@Transactional
	@Rollback
	public void consultarUsuario() {
		// preparacion
		Usuario usuario2 = crearUsuario2();
		session().save(usuario2);

		// ejecucion
		Usuario buscado = repositorio.consultarUsuario(usuario2);

		// comprobacion
		assertThat(buscado).isNotNull();
		assertThat(buscado).isEqualTo(usuario2);
	}

	@Test
	@Transactional
	@Rollback
	public void consultarUsuarioPorId() {
		// preparacion
		Usuario usuario2 = crearUsuario2();
		session().save(usuario2);

		// ejecucion
		Usuario buscado = repositorio.consultarUsuarioPorId(usuario2.getId());

		// comprobacion
		assertThat(buscado).isNotNull();
		assertThat(buscado).isEqualTo(usuario2);
		assertThat(buscado.getId()).isEqualTo(usuario2.getId());
	}

	@Test
	@Transactional
	@Rollback
	public void listarUsuarios() {
		// preparacion
		Usuario usuario1 = crearUsuario1();
		Usuario usuario2 = crearUsuario2();

		session().save(usuario1);
		session().save(usuario2);

		// ejecucion
		List<Usuario> usuarios = repositorio.listarTodosLosUsuarios();

		// comprobacion

		assertThat(usuarios).isNotEmpty();
		assertThat(usuarios).hasSize(2);
	}

	@Test
	@Transactional
	@Rollback
	public void listarInmueblesAlquiladosPorUnUsuario() {
		// preparacion
		Usuario usuario1 = crearUsuario1();
		session().save(usuario1);
		Inmueble inmueble = crearInmueble();
		inmueble.setInquilino(usuario1);
		session().update(inmueble);

		// ejecucion

		List<Inmueble> inmuebles = repositorio.listarInmueblesAlquiladosDeUnUsuario(usuario1.getId());

		// comprobacion
		assertThat(inmuebles).isNotEmpty();
		assertThat(inmuebles).hasSize(1);
	}
	
	@Test
	@Transactional
	@Rollback
	public void mostrarTorneosQueCree() {

		// preparacion
		Usuario usuario1 = crearUsuario1();
		session().save(usuario1);
		Torneo torneo = crearTorneo();
		torneo.setCreador(usuario1);
		session().save(torneo);

		// ejecucion
		List<Torneo> torneos = repositorio.listarTorneosQueCreoUnUsuario(usuario1.getId());

		// comprobacion
		assertThat(torneos).isNotEmpty();
		assertThat(torneos).hasSize(1);

	}

	@Test
	@Transactional
	@Rollback
	public void listarUsuariosMasGanadores() {
		// preparacion
		Usuario marta = crearUsuario1();
		marta.setTorGanados(3);
		Usuario jose = crearUsuario2();
		jose.setTorGanados(5);
		session().save(marta);
		session().save(jose);

        //ejecucion
		List<Usuario> masGanadores = repositorio.listarUsuariosMasGanadores();

        //comprobacion
		assertThat(masGanadores).isNotEmpty();
		assertThat(masGanadores).hasSize(2);

	}

	@Test
	@Transactional
	@Rollback
	public void modificarUsuario() {
		// preparacion
		Usuario usuario1 = crearUsuario1();
		usuario1.setTorGanados(0);
		session().save(usuario1);
		usuario1.setTorGanados(2);

		// ejecucion
		repositorio.modificarUsuario(usuario1);

		// comprobacion
		Usuario modificado = session().get(Usuario.class, usuario1.getId());
		assertThat(modificado).isNotNull();
		assertThat(modificado.getTorGanados()).isEqualTo(2);

	}

}
