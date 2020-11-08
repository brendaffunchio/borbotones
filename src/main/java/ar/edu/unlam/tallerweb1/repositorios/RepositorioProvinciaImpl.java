package ar.edu.unlam.tallerweb1.repositorios;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.Provincia;

public class RepositorioProvinciaImpl implements RepositorioProvincia {
	
	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioProvinciaImpl(SessionFactory sessionFactory) {
		
	
		this.sessionFactory = sessionFactory;
	}

	

	@Override
	public LinkedList<Provincia> todasLasProvincias() {
		
		final Session session = sessionFactory.getCurrentSession();

		return (LinkedList<Provincia>) session.createCriteria(Provincia.class).list();
				
				
	}

	
	
}
