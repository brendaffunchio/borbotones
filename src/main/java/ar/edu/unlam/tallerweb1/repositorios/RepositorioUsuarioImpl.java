package ar.edu.unlam.tallerweb1.repositorios;


import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository("repositorioUsuario")
public class RepositorioUsuarioImpl implements RepositorioUsuario {


	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioUsuarioImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Usuario consultarUsuario(Usuario usuario) {

		final Session session = sessionFactory.getCurrentSession();
		return (Usuario) session.createCriteria(Usuario.class).add(Restrictions.eq("email", usuario.getEmail()))
				.add(Restrictions.eq("password", usuario.getPassword())).uniqueResult();
	}

	@Override
	public void guardarUsuario(Usuario usuario) {
		
		final Session session = sessionFactory.getCurrentSession();
		
		session.save(usuario);
		}
		
	@Override
	public List<Usuario>listarTodosLosUsuarios(){
		
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Usuario.class).list();
	}
	@Override
	public List<Inmueble> listarInmueblesAlquiladosDeUnUsuario(Long usuarioId) {
		
		final Session session = sessionFactory.getCurrentSession();
		
		
		 return session.createCriteria(Inmueble.class).add(Restrictions.eq("inquilino.id",usuarioId)).list();
		
				
		
	}

	
	@Override
	public List<Torneo> listarTorneosQueCreoUnUsuario(Long usuarioId) {
		
		final Session session = sessionFactory.getCurrentSession();
		
		
		return session.createCriteria(Torneo.class).add(Restrictions.eq("creador.id",usuarioId)).list();
		

		
	}

	@Override
	public List<Usuario> listarUsuariosMasGanadores() {
		final Session session = sessionFactory.getCurrentSession();
		
		
		return session.createCriteria(Usuario.class)
				.add(Restrictions.gt("torGanados", 0))
				.addOrder(Order.desc("torGanados")).list();
	}

	@Override
	public Usuario consultarUsuarioPorId(Long usuarioId) {
		final Session session = sessionFactory.getCurrentSession();
		Usuario usuario = session.get(Usuario.class, usuarioId);
		return usuario;
	}

	@Override
	public void modificarUsuario(Usuario usuario) {
		final Session session = sessionFactory.getCurrentSession();
		
		session.update(usuario);
	}

	
	
	
}
