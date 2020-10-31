package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public void guardarUsuario(Usuario usuario) {
		repositorioUsuario.guardarUsuario(usuario);
		
	}

	@Override
	public List<String> mostrarEmails() {
		
		return repositorioUsuario.mostrarEmails();
	}

	@Override
	public Boolean devolverEstadoDelEmail(String emailParametro) {
	
		return repositorioUsuario.devolverEstadoDelEmail(emailParametro);
	}

	

	
	
}
