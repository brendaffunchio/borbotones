package ar.edu.unlam.tallerweb1.repositorios;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;



import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository
public class RepositorioTorneoImpl implements RepositorioTorneo {

	private Torneo torneo;
	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioTorneoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Torneo> torneos() {

		final Session session = sessionFactory.getCurrentSession();

		return session.createCriteria(Torneo.class)
				.list();

	}

	@Override
	public void guardarTorneo(Torneo torneo, Long creadorId, Long inmuebleId) {
		
		
		final Session session = sessionFactory.getCurrentSession();
		
		Usuario creadorTorneo= session.get(Usuario.class, creadorId);
		Inmueble inmueble = session.get(Inmueble.class, inmuebleId);
		
		torneo.setEstadoCompleto(false);
		torneo.setInscriptos(0);
		torneo.setCreador(creadorTorneo);
		torneo.setInmuebleDelTorneo(inmueble);
				
		session.save(torneo);
		
		
	

	}

	@Override
	public List<Torneo> buscarTorneo(String categoria, String juego) {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Torneo.class);
		
		 if(categoria != null && !categoria.equals("")) {
		 criteria.add(Restrictions.like("categoria", categoria));
		 
		 } 
		 
		 if (juego != null &&!juego.equals("")) {
		 criteria.add(Restrictions.like("juego", juego, MatchMode.ANYWHERE));
		 }
		 
		return criteria.list();
		
		
		



		// esto es and:
		
		 /*Criteria criteria =
			sessionFactory.getCurrentSession().createCriteria(Torneo.class);
		  criteria.add(Restrictions.like("categoria", categoria)); if (juego != null &&
		  !juego.equals("")) criteria.add(Restrictions.like("juego", juego));
		  return criteria.list();*/
		 
		// esto es or:
		/*final Session session = sessionFactory.getCurrentSession();
		List <Torneo> torneosBuscados = new LinkedList<>();
		if (categoria != null && !categoria.equals("") && juego != null && !juego.equals(""))
			torneosBuscados= session.createCriteria(Torneo.class)
			.add(Restrictions.and(Restrictions.like("categoria", categoria),
			 Restrictions.like("juego", juego)))
			.list();*/
			
		
		

	}


	@Override
	public Set<Usuario> mostrarParticipantesDelTorneo(Long torneoId) {
		
		final Session session = sessionFactory.getCurrentSession();
		
		Torneo torneo = session.get(Torneo.class, torneoId);
		
		 Set<Usuario>participantesDelTorneo = torneo.getParticipantes();
		
		return participantesDelTorneo;
	}


	@Override
	public void modificarTorneo(Torneo torneo) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(torneo);
		
	}

	@Override
	public Torneo consultarTorneoPorId(Long torneoId) {
		final Session session = sessionFactory.getCurrentSession();
		
		Torneo torneo= session.get(Torneo.class, torneoId);
		
		return torneo;
	}
	
	
	
	
}
