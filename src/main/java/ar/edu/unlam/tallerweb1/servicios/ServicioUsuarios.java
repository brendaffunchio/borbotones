package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioUsuarios {

	
	void guardarUsuario(Usuario usuario, Direccion direccion);

	public List<Inmueble> mostrarInmueblesAlquilados(Long usuarioId);


}
