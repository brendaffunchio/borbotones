package ar.edu.unlam.tallerweb1.repositorios;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Provincia;

@Repository
public class RepositorioProvinciaImpl implements RepositorioProvincia {
	
	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioProvinciaImpl(SessionFactory sessionFactory) {
		
	
		this.sessionFactory = sessionFactory;
	}

	

	@Override
	public List<Provincia> listarTodasLasProvincias() {
		
		final Session session = sessionFactory.getCurrentSession();

		return session.createCriteria(Provincia.class).list();
				
				
	}

	
	
}
