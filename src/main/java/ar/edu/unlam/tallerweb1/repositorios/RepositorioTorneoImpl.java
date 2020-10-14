package ar.edu.unlam.tallerweb1.repositorios;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository
public class RepositorioTorneoImpl implements RepositorioTorneo {

	private SessionFactory sessionFactory;

    @Autowired
	public RepositorioTorneoImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Torneo> torneos() {
		final Session session = sessionFactory.getCurrentSession();
		
		
		return session.createCriteria(Torneo.class).list();
	}

	@Override
	public void guardarTorneo(Torneo torneo) {
		
		final Session session = sessionFactory.getCurrentSession();
		session.save(torneo);
		
	}
	
		
	
}
