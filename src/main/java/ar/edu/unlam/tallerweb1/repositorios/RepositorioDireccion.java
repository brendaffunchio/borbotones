package ar.edu.unlam.tallerweb1.repositorios;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlam.tallerweb1.modelo.Direccion;

public interface RepositorioDireccion {

	public Direccion buscarDireccion(String calle, Integer numero);
	
	
}
