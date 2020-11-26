package ar.edu.unlam.tallerweb1.servicios;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTorneo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;


@Service
@Transactional
public class ServicioUsuariosImpl implements ServicioUsuarios{

	//siempre se referencia a la interfaz
	
	private RepositorioUsuario repositorioUsuario;

	
	@Autowired
	public ServicioUsuariosImpl(RepositorioUsuario repositorioUsuario) {

		this.repositorioUsuario = repositorioUsuario;
		
	}

	@Override
	public void guardarUsuario(Usuario usuario, Direccion direccion) {
		repositorioUsuario.guardarUsuario(usuario, direccion);
		
	}

	@Override
	public List<Inmueble> mostrarInmueblesAlquilados(Long usuarioId) {
		
		return repositorioUsuario.mostrarInmueblesAlquilados(usuarioId);
	}

	@Override
	public Set<Torneo> mostrarTorneosQueParticipo(Long usuarioId) {
		
		return repositorioUsuario.mostrarTorneosQueParticipo(usuarioId);
	}

	@Override
	public List<Torneo> mostrarTorneosQueCree(Long usuarioId) {
		
		return repositorioUsuario.mostrarTorneosQueCree(usuarioId);
	}

	@Override
	public List<Usuario> usuariosMasGanadores() {
		
		 return repositorioUsuario.usuariosMasGanadores();
			
		
	}

	

	
	
}
