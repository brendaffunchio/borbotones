package ar.edu.unlam.tallerweb1.repositorios;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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

	private Torneo torneo;
	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioTorneoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Torneo> torneos() {

		final Session session = sessionFactory.getCurrentSession();

		return session.createCriteria(Torneo.class).add(Restrictions.eq("estadoCompleto", false))

				.list();

	}

	@Override
	public void guardarTorneo(Torneo torneo) {

		final Session session = sessionFactory.getCurrentSession();
		torneo.setEstadoCompleto(false);
		torneo.setInscriptos(0);
		session.save(torneo);

	}

	@Override
	public List<Torneo> buscarTorneo(String categoria, String juego) {
		// esto es and:
		/*
		 * Criteria criteria =
		 * sessionFactory.getCurrentSession().createCriteria(Torneo.class); if
		 * (categoria != null && !categoria.equals(""))
		 * criteria.add(Restrictions.like("categoria", categoria)); if (juego != null &&
		 * !juego.equals("")) criteria.add(Restrictions.like("juego", juego));
		 * 
		 * return criteria.list();
		 */
		// esto es or:

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Torneo.class);
		if (categoria != null && !categoria.equals("") && juego != null && !juego.equals(""))
			criteria.add(Restrictions.or(Restrictions.like("categoria", categoria), Restrictions.like("juego", juego)))
			.add(Restrictions.eq("estadoCompleto", false));

		return criteria.list();

	}

	@Override
	public Torneo verDetallesTorneo(Long id) {

		return sessionFactory.getCurrentSession().get(Torneo.class, id);
	}

	@Override
	public void agregarParticipante(Long torneoId, Long usuarioId) {
		final Session session = sessionFactory.getCurrentSession();

		Torneo torneo = session.get(Torneo.class, torneoId);
		Usuario participante = session.get(Usuario.class, usuarioId);

		Set<Torneo> torneosParticipa = participante.getTorneosParticipa();
		Set<Usuario> participantes = torneo.getParticipantes();

		Integer inscriptos = torneo.getInscriptos();

		if (!participantes.contains(participante)
				&&torneo.getCupo() > torneo.getInscriptos()) {
			participantes.add(participante);
			torneosParticipa.add(torneo);
			inscriptos++;
			torneo.setInscriptos(inscriptos);

		}

		
		if (torneo.getInscriptos() >= torneo.getCupo()) {
			torneo.setEstadoCompleto(true);

		}

	}
	
	@Override
	public void eliminarParticipante(Long torneoId, Long usuarioId) {
		final Session session = sessionFactory.getCurrentSession();

		Torneo torneo = session.get(Torneo.class, torneoId);
		Usuario participante = session.get(Usuario.class, usuarioId);

		Set<Torneo> torneosParticipa = participante.getTorneosParticipa();
		Set<Usuario> participantes = torneo.getParticipantes();

		Integer inscriptos = torneo.getInscriptos();

		if (participantes.contains(participante) && torneosParticipa.contains(torneo)) {
			participantes.remove(participante);
			torneosParticipa.remove(torneo);
			inscriptos--;
			torneo.setInscriptos(inscriptos);

		}
		
	}
}
