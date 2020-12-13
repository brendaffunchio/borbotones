package ar.edu.unlam.tallerweb1.persistencia;

import java.util.List;
import java.util.Set;

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
import ar.edu.unlam.tallerweb1.repositorios.RepositorioInmueble;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTorneo;

public class RepositorioTorneoTest extends SpringTest {

	@Autowired
	RepositorioTorneo repositorio;
	
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
	private Direccion direccion() {
		Direccion direccion = new Direccion();
		Ciudad ciudad = ciudad();
		direccion.setCalle("Libertad");
		direccion.setNumero(325);
		direccion.setCiudad(ciudad);
		session().save(direccion);

		return direccion;
	}

	@Transactional
	@Rollback
	private Inmueble inmueble() {

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
	private Usuario usuario1() {

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
	private Usuario usuario2() {

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
		return torneo;
	}

	

	@Test
	@Transactional
	@Rollback
	public void guardarTorneo() {
		//preparacion
		Usuario creador= usuario1();
		Inmueble inmueble= inmueble();
		Torneo torneo= torneo();
		
		//ejecucion
		repositorio.guardarTorneo(torneo, creador.getId(), inmueble.getId());
		
		//comprobacion 
		Torneo buscado = session().get(Torneo.class, torneo.getId());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void listarTodosLosTorneos() {
		
	}
	@Test
	@Transactional
	@Rollback
	public void buscarTorneo() {
		
	}

	@Test
	@Transactional
	@Rollback
	public void listarParticipantesDelTorneo() {
		
	}

	@Test
	@Transactional
	@Rollback
	public void modificarTorneo() {
		
	}

	@Test
	@Transactional
	@Rollback
	public void consultarTorneoPorId() {
		
	}

	@Test
	@Transactional
	@Rollback
	public void ordenarTorneosSegunDistancia() {
		
	}

	@Test
	@Transactional
	@Rollback
	public void filtrarTorneosPorDistancia() {
		
	}

	
	
}
