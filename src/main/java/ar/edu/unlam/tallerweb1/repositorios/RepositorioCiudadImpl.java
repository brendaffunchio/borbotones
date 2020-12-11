package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Ciudad;

@Repository
public class RepositorioCiudadImpl implements RepositorioCiudad {

	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioCiudadImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<Ciudad> listarCiudades() {
		
		final Session session = sessionFactory.getCurrentSession();
		
		return session.createCriteria(Ciudad.class).list();
	
	}

}
