package ar.edu.unlam.tallerweb1.repositorios;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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

	public List<Torneo> torneos() {
		
		final Session session = sessionFactory.getCurrentSession();


        List<Usuario>participantes= new LinkedList<>();


        Integer cupo= participantes.size();


        return session.createCriteria(Torneo.class)

                .add(Restrictions.ge("cupo",cupo))

                .list();
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
	public List<Torneo> buscarTorneoPorJuego(String juego) {
		
		List <Torneo> torneosJuego = sessionFactory.getCurrentSession()
				.createCriteria(Torneo.class)
				.add(Restrictions.like("juego",juego)).list();
		
		return torneosJuego;
		
		
		
	}

	@Override
	public List<Torneo> buscarTorneoPorCategoria(String categoria) {
		
		List <Torneo> torneosCategoria = sessionFactory.getCurrentSession()
				.createCriteria(Torneo.class)
				.add(Restrictions.like("categoria",categoria)).list();
		
		return torneosCategoria;
		
	}

		
	
}
