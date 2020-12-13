package ar.edu.unlam.tallerweb1.persistencia;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Ciudad;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCiudad;

public class RepositorioCiudadTest extends SpringTest{

	@Autowired
	RepositorioCiudad repositorio;
	
	@Test
	@Transactional @Rollback
	public void listarTodasLasCiudades() {
		
		//preparacion
		Ciudad moron = new Ciudad();
		Ciudad lobos = new Ciudad();
		session().save(moron);
		session().save(lobos);
		
		//ejecucion
		List<Ciudad> ciudades= repositorio.listarCiudades();
		
		//comprobacion
		assertThat(ciudades).isNotEmpty();
		assertThat(ciudades).hasSize(2);
		
		
		
		
	}
}
