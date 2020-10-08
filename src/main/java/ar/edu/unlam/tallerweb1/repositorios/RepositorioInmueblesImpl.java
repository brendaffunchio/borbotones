package ar.edu.unlam.tallerweb1.repositorios;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Inmueble;

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

	
}
