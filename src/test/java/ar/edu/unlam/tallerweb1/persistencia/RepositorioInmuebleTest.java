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
import ar.edu.unlam.tallerweb1.repositorios.RepositorioInmueble;

public class RepositorioInmuebleTest extends SpringTest {

	@Autowired
	RepositorioInmueble repositorio;

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

	private Inmueble inmueble() {

		Inmueble inmueble = new Inmueble();
		Direccion direccion = direccion();
		inmueble.setNombre("Depto Gamer 1");
		inmueble.setDisponible(true);
		inmueble.setPrecio(3000d);
		inmueble.setFoto("foto");
		inmueble.setDireccion(direccion);
		return inmueble;
	}

	@Test
	@Transactional
	@Rollback
	public void guardarInmueble() {
		// preparacion
		Inmueble inmueble = inmueble();

		// ejecucion
		repositorio.guardarInmueble(inmueble);

		// comprobacion
		Inmueble buscado = session().get(Inmueble.class, inmueble.getId());
		assertThat(buscado).isNotNull();
	}

	@Test
	@Transactional
	@Rollback
	public void listarTodosLosInmuebles() {
		// preparacion
		Inmueble inmueble1 = inmueble();
		Inmueble inmueble2 = inmueble();
		session().save(inmueble1);
		session().save(inmueble2);

		// ejecucion
		List<Inmueble> inmuebles = repositorio.listarTodosLosInmueblesDisponibles();

		// comprobacion
		assertThat(inmuebles).isNotEmpty();
		assertThat(inmuebles).hasSize(2);

	}

	@Test
	@Transactional
	@Rollback
	public void buscarInmueble() {
		// preparacion
		Inmueble inmueble = inmueble();
		session().save(inmueble);
		Ciudad ciudad = ciudad();

		// ejecucion
		List<Inmueble> buscados = repositorio.buscarInmueble(inmueble.getDireccion().getCiudad().getProvincia().getId(),
				ciudad.getNombre());

		// comprobacion
		assertThat(buscados).isNotEmpty();
		assertThat(buscados).hasSize(1);
	}

	@Test
	@Transactional
	@Rollback
	public void consultarInmueblePorId() {
		// preparacion
		Inmueble inmueble = inmueble();
		session().save(inmueble);

		// ejecucion
		Inmueble buscado = repositorio.consultarInmueblePorId(inmueble.getId());

		// comprobacion
		assertThat(buscado).isNotNull();
		assertThat(buscado).isEqualTo(inmueble);
		assertThat(buscado.getId()).isEqualTo(inmueble.getId());
	}

	@Test
	@Transactional
	@Rollback
	public void modificarInmueble() {
		// preparacion
		Inmueble inmueble = inmueble();
		session().save(inmueble);
		inmueble.setNombre("Sala de youtubers");
		
		//ejecucion
		repositorio.modificarInmueble(inmueble);
		
		//comprobacion
		Inmueble buscado = session().get(Inmueble.class, inmueble.getId());
		assertThat(buscado).isNotNull();
		assertThat(buscado.getNombre()).isEqualTo("Sala de youtubers");
	}

	@Test
	@Transactional
	@Rollback
	public void filtrarInmueblesPorPrecio() {

		//preparacion
		Inmueble inmueble = inmueble();
		session().save(inmueble);
		
		//ejecucion
		List<Inmueble>filtrados= repositorio.filtrarInmueblesPorPrecio(2000d, 3000d);
		
		//comprobacion
		assertThat(filtrados).isNotEmpty();
		assertThat(filtrados).hasSize(1);
	
	}
}
