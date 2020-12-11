package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import java.util.Set;

import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.DireccionNoValidaException;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.PasswordVaciaException;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.UsuarioYaExisteException;

public interface ServicioUsuarios {

	
	void guardarUsuario(Usuario usuario, Direccion direccion) throws PasswordVaciaException, UsuarioYaExisteException, DireccionNoValidaException;

	public List <Inmueble> listarInmueblesAlquiladosDeUnUsuario(Long usuarioId);

	public Set<Torneo> listarTorneosQueParticipaUnUsuario(Long usuarioId);

	public List<Torneo> listarTorneosQueCreoUnUsuario(Long usuarioId);

	public List <Usuario> listarUsuariosMasGanadores();
	
	public Usuario consultarUsuarioPorId(Long usuarioId);

	
}
