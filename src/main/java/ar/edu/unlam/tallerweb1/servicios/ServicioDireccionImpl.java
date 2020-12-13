package ar.edu.unlam.tallerweb1.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDireccion;

@Service
@Transactional
public class ServicioDireccionImpl implements ServicioDireccion {

	public RepositorioDireccion repositorioDireccion;
	
	@Autowired
	public ServicioDireccionImpl(RepositorioDireccion repositorioDireccion) {
		super();
		this.repositorioDireccion = repositorioDireccion;
	}


	@Override
	public Direccion buscarDireccion(String calle, Integer numero) {
		
		
		return repositorioDireccion.buscarDireccion(calle,numero);
	}
	
	

	

}
