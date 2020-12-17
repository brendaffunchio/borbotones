package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;
import java.util.Set;

import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;


public interface RepositorioUsuario {
	
	public Usuario consultarUsuario (Usuario usuario);

	public void guardarUsuario(Usuario usuario);

	public List<Usuario>listarTodosLosUsuarios();
	
	public List<Inmueble> listarInmueblesAlquiladosDeUnUsuario(Long usuarioId);

	public List<Torneo> listarTorneosQueCreoUnUsuario(Long usuarioId);

	public List<Usuario> listarUsuariosMasGanadores();

    public Usuario consultarUsuarioPorId(Long usuarioId);
    
    public void modificarUsuario(Usuario usuario);

}