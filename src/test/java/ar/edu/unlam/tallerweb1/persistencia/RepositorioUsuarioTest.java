package ar.edu.unlam.tallerweb1.persistencia;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

public class RepositorioUsuarioTest extends SpringTest {

	@Autowired
	RepositorioUsuario repositorio;

	@Test
	@Transactional
	public void guardarUsuario() {
		// preparacion
		final Usuario usuario = new Usuario();
		usuario.setTorGanados(0);

		// ejecucion
		repositorio.guardarUsuario(usuario);

		// comprobacion
		Usuario guardado = session().get(Usuario.class, usuario.getId());
		assertThat(guardado).isNotNull();
	}

	@Test
	@Transactional
	public void consultarUsuario() {
		// preparacion
		final Usuario usuario = new Usuario();
		usuario.setEmail("bren@gmail.com");
		usuario.setPassword("1234");
		usuario.setTorGanados(0);
		session().save(usuario);

		// ejecucion
		Usuario buscado=repositorio.consultarUsuario(usuario);

		// comprobacion
		assertThat(buscado).isNotNull();
	}

	@Test
	@Transactional
	public void mostrarUsuarios() {
		// preparacion
		final Usuario usuario = new Usuario();
		final Usuario usuario2 = new Usuario();
		usuario.setTorGanados(0);
        usuario2.setTorGanados(1);
        session().save(usuario);
        session().save(usuario2);

		// ejecucion
        List<Usuario>usuarios=repositorio.mostrarUsuarios();

		// comprobacion
		
		assertThat(usuarios).isNotEmpty();
		assertThat(usuarios).hasSize(2);
	}

	@Test
	@Transactional
	public void mostrarInmueblesAlquilados() {

	}

	@Test
	@Transactional
	public void mostrarTorneosQueParticipo() {

	}

	@Test
	@Transactional
	public void mostrarTorneosQueCree() {

	}

	@Test
	@Transactional
	public void usuariosMasGanadores() {

	}

	@Test
	@Transactional
	public void consultarUsuarioPorId() {

	}

	@Test
	@Transactional
	public void modificarUsuario() {

	}

}
