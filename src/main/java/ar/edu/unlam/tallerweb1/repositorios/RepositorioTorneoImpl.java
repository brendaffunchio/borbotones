package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;
import java.util.Set;



import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository
public class RepositorioTorneoImpl implements RepositorioTorneo {

	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioTorneoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Torneo> listarTodosLosTorneos() {

		final Session session = sessionFactory.getCurrentSession();

		return session.createCriteria(Torneo.class)
				.list();

	}

	@Override
	public void guardarTorneo(Torneo torneo) {
		
		final Session session = sessionFactory.getCurrentSession();
	
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

	@Override
	public List<Torneo> ordenarTorneosSegunDistancia() {
		final Session session = sessionFactory.getCurrentSession();

		

		List <Torneo> torneosOrdenados = session.createCriteria(Torneo.class)
				.addOrder(Order.asc("distanciaConUsuario"))
				.list();
		
		return torneosOrdenados;
	}

	@Override
	public List<Torneo> filtrarTorneosPorDistancia(Double desdeKm, Double hastaKm) {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Torneo.class);
		
		
		if(desdeKm!=null && desdeKm != 0)
			criteria.add(Restrictions.ge("distanciaConUsuario", desdeKm));
		
		if(hastaKm!=null && hastaKm != 0)
			criteria.add(Restrictions.le("distanciaConUsuario", hastaKm));
		
		criteria.addOrder(Order.asc("distanciaConUsuario"));
		
		
		return criteria.list();
	}
	
	
	
	
}
