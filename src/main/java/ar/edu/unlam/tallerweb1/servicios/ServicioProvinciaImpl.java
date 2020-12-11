package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Provincia;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProvincia;


@Service
@Transactional
public class ServicioProvinciaImpl implements ServicioProvincia {
	
	private RepositorioProvincia repositorioProvincia;
	
	@Autowired
	public ServicioProvinciaImpl(RepositorioProvincia repositorioProvincia) {

		this.repositorioProvincia = repositorioProvincia;
	}
	

	@Override
	public List<Provincia> listarTodasProvincias() {
		
		return repositorioProvincia.listarTodasLasProvincias();
	}

}
