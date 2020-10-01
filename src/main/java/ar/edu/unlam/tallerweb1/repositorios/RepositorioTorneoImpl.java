package ar.edu.unlam.tallerweb1.repositorios;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository
public class RepositorioTorneoImpl implements RepositorioTorneo {

//	@Override
//	public Torneo ListaTorneos(Torneo torneo) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public List<Torneo> torneos() {
		Torneo torneo1 = new Torneo();
		Torneo torneo2 = new Torneo();
		Torneo torneo3 = new Torneo();
		torneo1.setNombre("Fifa");
		torneo2.setNombre("Pes");
		torneo3.setNombre("LOL");
		torneo1.setFecha("26/10/2020");
		torneo2.setFecha("15/12/2020");
		torneo3.setFecha("17/04/2021");
		torneo1.setHorario("10hs");
		torneo2.setHorario("15hs");
		torneo3.setHorario("21hs");
		torneo1.setPrecio(800d);
		torneo2.setPrecio(500d);
		torneo3.setPrecio(350d);
		List<Torneo>torneos= new LinkedList<>();
		
		torneos.add(torneo1);
		torneos.add(torneo2);
		torneos.add(torneo3);
		
		return torneos;
	}
	
		
	
}
