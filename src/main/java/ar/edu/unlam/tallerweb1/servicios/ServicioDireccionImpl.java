package ar.edu.unlam.tallerweb1.servicios;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Direccion;

@Service
@Transactional
public class ServicioDireccionImpl implements ServicioDireccion {

	@Override
	public Direccion crearDireccion(String calle, Integer numero) {
		
		Direccion direccion = new Direccion();
		direccion.setCalle(calle);
		direccion.setNumero(numero);
		
		return direccion;
	}

}
