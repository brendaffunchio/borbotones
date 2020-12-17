package ar.edu.unlam.tallerweb1.repositorios;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Inmueble;



@Repository
public class RepositorioInmueblesImpl implements RepositorioInmueble {

	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioInmueblesImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Inmueble> listarTodosLosInmueblesDisponibles() {
		final Session session = sessionFactory.getCurrentSession();

		return session.createCriteria(Inmueble.class)

				.add(Restrictions.eq("disponible", true))
				.addOrder(Order.asc("precio")).list();
	}
	@Override
	public List<Inmueble> listarTodosLosInmuebles() {
		final Session session = sessionFactory.getCurrentSession();

		return session.createCriteria(Inmueble.class).list();
	}
	@Override
	public void guardarInmueble(Inmueble inmueble) {
		final Session session = sessionFactory.getCurrentSession();
		    
		session.save(inmueble);
        
	}

	@Override
	public List<Inmueble> buscarInmueble(Long provinciaId, String nombreCiudad) {
			
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Inmueble.class);
		
		criteria.createAlias("direccion", "direccionBuscada");
		
		criteria.createAlias("direccionBuscada.ciudad", "ciudad");
		
		if(provinciaId != null && provinciaId != 0 ) {
		criteria.add(Restrictions.like("ciudad.provincia.id", provinciaId));
		
		
		}
		
		if(nombreCiudad != null && nombreCiudad != "") {
			
			criteria.add(Restrictions.like("ciudad.nombre", nombreCiudad, MatchMode.ANYWHERE));
			
		}
		
		criteria.add(Restrictions.eq("disponible",true));
		
		return criteria.list();
		
	
					
		}
	

	

	@Override
	public Inmueble consultarInmueblePorId(Long inmuebleId) {
		final Session session = sessionFactory.getCurrentSession();

		Inmueble inmueble = session.get(Inmueble.class, inmuebleId);
		
		return inmueble;
	}

	@Override
	public void modificarInmueble(Inmueble inmueble) {
		final Session session = sessionFactory.getCurrentSession();
		
		session.update(inmueble);
	}

	@Override
	public List<Inmueble> filtrarInmueblesPorPrecio(Double desdePrecio, Double hastaPrecio) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Inmueble.class);
		
		if(desdePrecio!=null && desdePrecio != 0)
			criteria.add(Restrictions.ge("precio", desdePrecio));
		
		if(hastaPrecio!=null && hastaPrecio != 0)
			criteria.add(Restrictions.le("precio", hastaPrecio));
		
		criteria.add(Restrictions.eq("disponible", true));
		
		return criteria.list();
	}
	
	

}
