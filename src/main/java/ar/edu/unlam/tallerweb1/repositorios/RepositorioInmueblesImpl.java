package ar.edu.unlam.tallerweb1.repositorios;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.Torneo;

@Repository
public class RepositorioInmueblesImpl implements RepositorioInmueble {

	private SessionFactory sessionFactory;

    @Autowired
	public RepositorioInmueblesImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Inmueble> todosLosInmuebles() {
		final Session session= sessionFactory.getCurrentSession();
		
		
		return session.createCriteria(Inmueble.class).list();
	}

	@Override
	public void guardarInmueble(Inmueble inmueble) {
		final Session session= sessionFactory.getCurrentSession();
		
		session.save(inmueble);
		
		
	}

	@Override
	public List<Inmueble> buscarInmueblePorProvincia(String provincia) {
		List <Inmueble> inmueblesProvincia = sessionFactory.getCurrentSession()
				.createCriteria(Inmueble.class)
				.add(Restrictions.like("provincia",provincia)).list();
		
		return inmueblesProvincia;
		
	}

	@Override
	public List<Inmueble> buscarInmueblePorCiudad(String ciudad) {
		List <Inmueble> inmueblesCiudad = sessionFactory.getCurrentSession()
				.createCriteria(Inmueble.class)
				.add(Restrictions.like("ciudad",ciudad)).list();
		
		return inmueblesCiudad;
		
	}

	
}
