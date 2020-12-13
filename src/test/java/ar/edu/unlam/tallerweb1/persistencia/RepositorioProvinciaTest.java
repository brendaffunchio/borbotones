package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Provincia;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProvincia;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


public class RepositorioProvinciaTest extends SpringTest{

	@Autowired
	RepositorioProvincia repositorio;
	
	@Test
	@Transactional @Rollback
	public void listarTodasLasProvincias() {
		
		//preparacion
		Provincia formosa = new Provincia();
		Provincia sanJuan = new Provincia();
		session().save(formosa);
		session().save(sanJuan);
		
		//ejecucion 
		List <Provincia> provincias= repositorio.listarTodasLasProvincias();
		
		//comprobacion
		assertThat(provincias).isNotEmpty();
		assertThat(provincias).hasSize(2);
	}
}
