package ar.edu.unlam.tallerweb1.repositorios;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Inmueble;

@Repository
public class RepositorioInmueblesImpl implements RepositorioInmueble {

	@Override
	public List<Inmueble> todosLosInmuebles() {
		Inmueble salaGamer1 = new Inmueble();
		Inmueble salaGamer2 = new Inmueble();
		
		salaGamer1.setNombre("Sala Gamer LOL");
		salaGamer2.setNombre("Sala Gamer Fifa");
		salaGamer1.setPrecio(12000d);
		salaGamer2.setPrecio(20000d);
		
		List <Inmueble> todosLosInmuebles= new LinkedList<>();
		
		todosLosInmuebles.add(salaGamer1);
		todosLosInmuebles.add(salaGamer2);
		
		return todosLosInmuebles;
	}

}
