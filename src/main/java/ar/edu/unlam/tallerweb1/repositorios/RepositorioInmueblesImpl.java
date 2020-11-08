package ar.edu.unlam.tallerweb1.repositorios;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
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
	public RepositorioInmueblesImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Inmueble> todosLosInmuebles() {
		final Session session = sessionFactory.getCurrentSession();

		return session.createCriteria(Inmueble.class)

				.add(Restrictions.eq("disponible", true)).list();
	}

	@Override
	public void guardarInmueble(Inmueble inmueble) {
		final Session session = sessionFactory.getCurrentSession();

		inmueble.setDisponible(true);
		session.save(inmueble);

	}

	@Override
	public List<Inmueble> buscarInmueble(String provincia, String localidad) {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Inmueble.class);
		if (provincia != null && !provincia.equals("") && localidad != null && !localidad.equals(""))
			criteria.add(Restrictions.or(Restrictions.like("provincia", provincia),
					Restrictions.like("localidad", localidad))).add(Restrictions.eq("disponible", true));

		return criteria.list();

		
		//hacer test
	}

	@Override
	public Inmueble verDetallesInmueble(Long id) {

		return sessionFactory.getCurrentSession().get(Inmueble.class, id);

	}

}
