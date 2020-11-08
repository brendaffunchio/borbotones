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
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository
public class RepositorioTorneoImpl implements RepositorioTorneo {

	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioTorneoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Torneo> torneos() {

		final Session session = sessionFactory.getCurrentSession();

	/*	List<Usuario> participantes = new LinkedList<>();


Integer cupo = participantes.size();
*/
		return session.createCriteria(Torneo.class).list();
	}

	@Override
	public void guardarTorneo(Torneo torneo) {

		final Session session = sessionFactory.getCurrentSession();
		session.save(torneo);

	}

	@Override
	public void guardarParticipante(Usuario usuario) {

	}

	@Override
	public List<Torneo> buscarTorneo(String categoria, String juego) {
        //esto es and:
		/*Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Torneo.class);
		if (categoria != null && !categoria.equals(""))
			criteria.add(Restrictions.like("categoria", categoria));
		if (juego != null && !juego.equals(""))
			criteria.add(Restrictions.like("juego", juego));

		return criteria.list();
*/
		//esto es or:
		
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Torneo.class);
		if(categoria!=null&&!categoria.equals("")&&juego != null && !juego.equals("")) 
		criteria.add(Restrictions.or
				(Restrictions.like("categoria",categoria), Restrictions.like("juego",juego)));
			

		return criteria.list();
		

	}

	@Override
	public Torneo verDetallesTorneo(Long id) {
		
		 return sessionFactory.getCurrentSession().get(Torneo.class, id);
	}

}
