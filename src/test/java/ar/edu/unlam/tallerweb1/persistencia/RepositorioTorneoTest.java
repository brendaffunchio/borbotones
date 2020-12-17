package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Ciudad;
import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.Provincia;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTorneo;

public class RepositorioTorneoTest extends SpringTest {

	@Autowired
	RepositorioTorneo repositorio;

	@Transactional
	@Rollback
	private Provincia crearProvincia() {
		Provincia provincia = new Provincia();
		provincia.setNombre("Buenos Aires");
		session().save(provincia);
		return provincia;
	}

	@Transactional
	@Rollback
	private Ciudad crearCiudad() {
		Ciudad ciudad = new Ciudad();
		Provincia provincia = crearProvincia();
		ciudad.setNombre("Cañuelas");
		ciudad.setCodigoPostal("B1814");
		ciudad.setProvincia(provincia);
		session().save(ciudad);
		return ciudad;
	}

	@Transactional
	@Rollback
	private Direccion crearDireccion() {
		Direccion direccion = new Direccion();
		Ciudad ciudad = crearCiudad();
		direccion.setCalle("Libertad");
		direccion.setNumero(325);
		direccion.setCiudad(ciudad);
		session().save(direccion);

		return direccion;
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

	@Transactional
	@Rollback
	private Usuario crearUsuario1() {

		Usuario usuario = new Usuario();
		usuario.setNombre("Marta");
		usuario.setApellido("Gomez");
		usuario.setEmail("marta@gmail.com");
		usuario.setPassword("1234");
		usuario.setRol("invitado");
		usuario.setTorGanados(0);
		session().save(usuario);
		return usuario;
	}

	@Transactional
	@Rollback
	private Usuario crearUsuario2() {

		Usuario usuario2 = new Usuario();
		usuario2.setNombre("Jose");
		usuario2.setApellido("Sanchez");
		usuario2.setEmail("jose@gmail.com");
		usuario2.setPassword("1234");
		usuario2.setRol("invitado");
		usuario2.setTorGanados(0);
		session().save(usuario2);
		return usuario2;
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
	public void guardarTorneo() {
		// preparacion
		Torneo torneo = crearTorneo();

		// ejecucion
		repositorio.guardarTorneo(torneo);

		// comprobacion
		Torneo buscado = session().get(Torneo.class, torneo.getId());
		assertThat(buscado).isNotNull();
	}

	@Test
	@Transactional
	@Rollback
	public void listarTodosLosTorneos() {
		// preparacion
		Torneo torneo1 = crearTorneo();
		Torneo torneo2 = crearTorneo();
		session().save(torneo1);
		session().save(torneo2);

		// ejecucion
		List<Torneo> torneos = repositorio.listarTodosLosTorneos();

		// comprobacion
		assertThat(torneos).isNotEmpty();
		assertThat(torneos).hasSize(2);
	}

	@Test
	@Transactional
	@Rollback
	public void buscarTorneo() {
		// preparacion
		Torneo torneo = crearTorneo();
		session().save(torneo);

		// ejecucion
		List<Torneo> buscados = repositorio.buscarTorneo(torneo.getCategoria(), torneo.getJuego());

		// comprobacion
		assertThat(buscados).isNotEmpty();
		assertThat(buscados).hasSize(1);
	}

	@Test
	@Transactional
	@Rollback
	public void modificarTorneo() {
		// preparacion
		Torneo torneo = crearTorneo();
		session().save(torneo);
		torneo.setCupo(15);

		// ejecucion
		repositorio.modificarTorneo(torneo);

		// comprobacion
		Torneo buscado = session().get(Torneo.class, torneo.getId());
		assertThat(buscado).isNotNull();
		assertThat(buscado.getCupo()).isEqualTo(15);
	}

	@Test
	@Transactional
	@Rollback
	public void consultarTorneoPorId() {
		// preparacion
		Torneo torneo = crearTorneo();
		session().save(torneo);

		// ejecucion
		Torneo buscado = repositorio.consultarTorneoPorId(torneo.getId());

		// comprobacion
		assertThat(buscado).isNotNull();
		assertThat(buscado).isEqualTo(torneo);

	}

	@Test
	@Transactional
	@Rollback
	public void ordenarTorneosSegunDistancia() {
		// preparacion
		Torneo torneo = crearTorneo();
		session().save(torneo);

		// ejecucion
		List<Torneo> ordenadosSegunDistancia = repositorio.ordenarTorneosSegunDistancia();

		// comprobacion
		assertThat(ordenadosSegunDistancia).isNotEmpty();
		assertThat(ordenadosSegunDistancia).hasSize(1);
	}

	@Test
	@Transactional
	@Rollback
	public void filtrarTorneosPorDistancia() {
		// preparacion
		Torneo torneo = crearTorneo();
		torneo.setDistanciaConUsuario(20d);
		session().save(torneo);

		// ejecucion
		List<Torneo> filtradosPorDistancia = repositorio.filtrarTorneosPorDistancia(15d, 20d);

		// comprobacion
		assertThat(filtradosPorDistancia).isNotEmpty();
		assertThat(filtradosPorDistancia).hasSize(1);
	}

}
