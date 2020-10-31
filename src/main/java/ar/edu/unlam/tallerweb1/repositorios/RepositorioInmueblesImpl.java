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
	public List<Inmueble> buscarInmueblePorLocalidad(String localidad) {
		List <Inmueble> inmueblesLocalidad= sessionFactory.getCurrentSession()
				.createCriteria(Inmueble.class)
				.add(Restrictions.like("localidad",localidad)).list();
		
		return inmueblesLocalidad;
		
	}

	@Override
	public List<Inmueble> buscarInmueble(String provincia, String localidad) {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Inmueble.class);
		if (provincia != null && !provincia.equals(""))
			criteria.add(Restrictions.like("provincia", provincia));
	
		if (localidad != null && !localidad.equals(""))
			criteria.add(Restrictions.like("localidad", localidad));
		
		return criteria.list();

	}

	@Override
	public Inmueble verDetallesInmueble(Long id) {
		
	 return (Inmueble) sessionFactory.getCurrentSession().createCriteria(Inmueble.class)
		  .add(Restrictions.eq("id",id)).uniqueResult();
		
		// return sessionFactory.getCurrentSession().get(Inmueble.class, id);
	}

}
