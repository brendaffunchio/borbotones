package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import java.util.Set;

import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.DireccionNoValidaException;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioUsuarios {

	
	void guardarUsuario(Usuario usuario, Direccion direccion);

	public List <Inmueble> mostrarInmueblesAlquilados(Long usuarioId);

	public Set<Torneo> mostrarTorneosQueParticipo(Long usuarioId);

	public List<Torneo> mostrarTorneosQueCree(Long usuarioId);

	public List <Usuario> usuariosMasGanadores();
	
	public Usuario consultarUsuarioPorId(Long usuarioId);

	
}
