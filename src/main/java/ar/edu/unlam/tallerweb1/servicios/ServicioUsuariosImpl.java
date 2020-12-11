package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import java.util.Set;

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
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDireccion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;


@Service
@Transactional
public class ServicioUsuariosImpl implements ServicioUsuarios{

	
	
	private RepositorioUsuario repositorioUsuario;
	
	@Autowired
	public ServicioUsuariosImpl(RepositorioUsuario repositorioUsuario
			, RepositorioDireccion repositorioDireccion) {

		this.repositorioUsuario = repositorioUsuario;
	
	}

	@Override
	public void guardarUsuario(Usuario usuario, Direccion direccion) throws PasswordVaciaException, UsuarioYaExisteException, DireccionNoValidaException{
		
		for(Usuario usuarioBuscado: repositorioUsuario.listarTodosLosUsuarios()) {
			if(usuarioBuscado.equals(usuario)) throw new UsuarioYaExisteException();
		}
		if (direccion==null) throw new DireccionNoValidaException();
		
		if (usuario.getPassword()==null || usuario.getPassword().equals(" "))
			throw new PasswordVaciaException();
		
		usuario.setRol("invitado");
		usuario.setTorGanados(0);
		usuario.setDireccion(direccion);
        
		repositorioUsuario.guardarUsuario(usuario);
		
	}

	@Override
	public List<Inmueble> listarInmueblesAlquiladosDeUnUsuario(Long usuarioId){
		return repositorioUsuario.listarInmueblesAlquiladosDeUnUsuario(usuarioId);
			
	}
	

	@Override
	public Set<Torneo> listarTorneosQueParticipaUnUsuario(Long usuarioId) {
		
		return repositorioUsuario.listarTorneosQueParticipaUnUsuario(usuarioId);
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
