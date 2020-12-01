package ar.edu.unlam.tallerweb1.repositorios;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Ciudad;
import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.Provincia;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

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
	public void guardarInmueble(Inmueble inmueble,Direccion direccion) {
		final Session session = sessionFactory.getCurrentSession();
		
		String calle=direccion.getCalle();
		Integer numero= direccion.getNumero();
         Direccion direccionBuscada= (Direccion) session.createCriteria(Direccion.class)
        		.add(Restrictions.eq("calle", calle))
        		.add(Restrictions.eq("numero", numero))
        		.uniqueResult();
        
        inmueble.setDireccion(direccionBuscada);
		inmueble.setDisponible(true);
		
		session.save(inmueble);

	}

	@Override
	public List<Inmueble> buscarInmueble(Long provinciaId, String nombreCiudad) {
			
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Inmueble.class);
		
		criteria.createAlias("direccion", "direccionBuscada");
		
		criteria.createAlias("direccionBuscada.ciudad", "ciudad");
		criteria.createAlias("ciudad.provincia", "provincia");
		
		if(provinciaId != null && provinciaId != 0 ) {
		criteria.add(Restrictions.like("provincia.id", provinciaId));
		}
		
		if(nombreCiudad != null && nombreCiudad != "") {
			
			criteria.add(Restrictions.like("ciudad.nombre", nombreCiudad, MatchMode.ANYWHERE));
		}
		
		return criteria.list();
		
	
					
		}
	

		
		//hacer test
	

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
	
	

}
