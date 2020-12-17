package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.DireccionNoValidaException;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.PasswordVaciaException;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.UsuarioYaExisteException;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTorneo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

@Service
@Transactional
public class ServicioUsuariosImpl implements ServicioUsuarios {

	private RepositorioUsuario repositorioUsuario;
	private RepositorioTorneo repositorioTorneo;

	@Autowired
	public ServicioUsuariosImpl(RepositorioUsuario repositorioUsuario, RepositorioTorneo repositorioTorneo) {

		this.repositorioUsuario = repositorioUsuario;
		this.repositorioTorneo = repositorioTorneo;

	}

	@Override
	public void guardarUsuario(Usuario usuario, Direccion direccion)
			throws PasswordVaciaException, UsuarioYaExisteException, DireccionNoValidaException {
		List<Usuario> usuarios = repositorioUsuario.listarTodosLosUsuarios();

		for (Usuario usuarioBuscado : usuarios) {
			if (usuarioBuscado.equals(usuario))
				throw new UsuarioYaExisteException();
		}
		if (direccion == null)
			throw new DireccionNoValidaException();

		if (usuario.getPassword() == null || usuario.getPassword().equals(" "))
			throw new PasswordVaciaException();

		usuario.setRol("invitado");
		usuario.setTorGanados(0);
		usuario.setDireccion(direccion);

		repositorioUsuario.guardarUsuario(usuario);

	}

	@Override
	public List<Inmueble> listarInmueblesAlquiladosDeUnUsuario(Long usuarioId) {
		return repositorioUsuario.listarInmueblesAlquiladosDeUnUsuario(usuarioId);

	}

	@Override
	public Set<Torneo> listarTorneosQueParticipaUnUsuario(Long usuarioId) {

		Usuario usuario = repositorioUsuario.consultarUsuarioPorId(usuarioId);

		List<Torneo> torneos = repositorioTorneo.listarTodosLosTorneos();

		Set<Torneo> torneosQueParticipa = new TreeSet<Torneo>();

		for (Torneo torneo : torneos) {

			if (torneo.getParticipantes().contains(usuario)) {

				torneosQueParticipa.add(torneo);
			}
		}

		return torneosQueParticipa;

	}

	@Override
	public List<Torneo> listarTorneosQueCreoUnUsuario(Long usuarioId) {

		return repositorioUsuario.listarTorneosQueCreoUnUsuario(usuarioId);
	}

	@Override
	public List<Usuario> listarUsuariosMasGanadores() {

		return repositorioUsuario.listarUsuariosMasGanadores();

	}

	@Override
	public Usuario consultarUsuarioPorId(Long usuarioId) {

		return repositorioUsuario.consultarUsuarioPorId(usuarioId);
	}

}
