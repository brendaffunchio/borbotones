package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Ciudad;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCiudad;

@Service
@Transactional
public class ServicioCiudadImpl implements ServicioCiudad {

private RepositorioCiudad repositorioCiudad;
	
	
	@Autowired
	public ServicioCiudadImpl(RepositorioCiudad repositorioCiudad) {
		
		this.repositorioCiudad = repositorioCiudad;
	}
	@Override
	public List<Ciudad> listarCiudades() {
		
		return repositorioCiudad.listarCiudades();
	}

}
