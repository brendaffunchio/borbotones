package ar.edu.unlam.tallerweb1.persistencia;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDireccion;

public class RepositorioDireccionTest extends SpringTest {

	@Autowired
	RepositorioDireccion repositorio;

	@Test
	@Transactional
	@Rollback
	public void buscarDireccionPorCalleYNumero() {

		// preparacion
		Direccion direccion1 = new Direccion();
		direccion1.setCalle("Lara");
		direccion1.setNumero(325);
		Direccion direccion2 = new Direccion();
		direccion2.setCalle("Rivadavia");
		direccion2.setNumero(1356);
		session().save(direccion1);
		session().save(direccion2);

		// ejecucion
		Direccion buscada = repositorio.buscarDireccion(direccion1.getCalle(), direccion1.getNumero());

		// ejecucion
		assertThat(buscada).isNotNull();
		assertThat(buscada).isEqualTo(direccion1);
	}
}
