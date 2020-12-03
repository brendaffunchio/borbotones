package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;
import java.util.Set;

import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

// Interface que define los metodos del Repositorio de Usuarios.
public interface RepositorioUsuario {
	
	Usuario consultarUsuario (Usuario usuario);

	void guardarUsuario(Usuario usuario);

	public List<Inmueble> mostrarInmueblesAlquilados(Long usuarioId);

	public Set<Torneo> mostrarTorneosQueParticipo(Long usuarioId);

	public List<Torneo> mostrarTorneosQueCree(Long usuarioId);

	public List<Usuario> usuariosMasGanadores();

    public Usuario consultarUsuarioPorId(Long usuarioId);
    
    public void modificarUsuario(Usuario usuario);

}