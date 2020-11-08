package ar.edu.unlam.tallerweb1.servicios;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.Provincia;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProvincia;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProvincia;

public class ServicioProvinciaImpl implements ServicioProvincia {
	
	private RepositorioProvincia repositorioProvincia;
	
	@Autowired
	public ServicioProvinciaImpl(RepositorioProvincia repositorioProvincia) {

		this.repositorioProvincia = repositorioProvincia;
	}
	

	@Override
	public LinkedList<Provincia> mostrarProvincias() {
		
		return repositorioProvincia.todasLasProvincias();
	}

}
