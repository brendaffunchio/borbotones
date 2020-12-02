package ar.edu.unlam.tallerweb1.repositorios;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Direccion;

@Repository
public class RepositorioDireccionImpl implements RepositorioDireccion{
	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioDireccionImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public Direccion buscarDireccion(Direccion direccion) {
		
		final Session session = sessionFactory.getCurrentSession();
		String calle = direccion.getCalle();
		Integer numero = direccion.getNumero();
		Direccion direccionBuscada = (Direccion) session.createCriteria(Direccion.class)
				.add(Restrictions.eq("calle", calle))
				.add(Restrictions.eq("numero", numero))
				.uniqueResult();
		
		return direccionBuscada;
	}

	
	
	
}
