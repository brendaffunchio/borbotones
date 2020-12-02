package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;


// implelemtacion del repositorio de usuarios, la anotacion @Repository indica a Spring que esta clase es un componente que debe
// ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete ar.edu.unlam.tallerweb1.dao
// para encontrar esta clase.
@Repository("repositorioUsuario")
public class RepositorioUsuarioImpl implements RepositorioUsuario {

	// Como todo repositorio maneja acciones de persistencia, normalmente estará
	// inyectado el session factory de hibernate
	// el mismo está difinido en el archivo hibernateContext.xml
	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioUsuarioImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Usuario consultarUsuario(Usuario usuario) {

		// Se obtiene la sesion asociada a la transaccion iniciada en el servicio que
		// invoca a este metodo y se crea un criterio
		// de busqueda de Usuario donde el email y password sean iguales a los del
		// objeto recibido como parametro
		// uniqueResult da error si se encuentran más de un resultado en la busqueda.
		final Session session = sessionFactory.getCurrentSession();
		return (Usuario) session.createCriteria(Usuario.class).add(Restrictions.eq("email", usuario.getEmail()))
				.add(Restrictions.eq("password", usuario.getPassword())).uniqueResult();
	}

	@Override
	public void guardarUsuario(Usuario usuario, Direccion direccion) {
		
		final Session session = sessionFactory.getCurrentSession();
		
		String rol = "invitado";
		usuario.setRol(rol);
		usuario.setTorGanados(0);
		String calle = direccion.getCalle();
		Integer numero = direccion.getNumero();
		
		Direccion direccionBuscada= (Direccion) session.createCriteria(Direccion.class)
       		.add(Restrictions.eq("calle",calle))
       		.add(Restrictions.eq("numero",numero))
       		.uniqueResult();
       
		if (direccionBuscada!=null) {
        usuario.setDireccion(direccionBuscada);
        
		session.save(usuario);
		}
			}

	@Override
	public List<Inmueble> mostrarInmueblesAlquilados(Long usuarioId) {
		
		final Session session = sessionFactory.getCurrentSession();
		
		
		 return session.createCriteria(Inmueble.class).add(Restrictions.eq("inquilino.id",usuarioId)).list();
		
				
		
	}

	@Override
	public Set<Torneo> mostrarTorneosQueParticipo(Long usuarioId) {
		
		final Session session = sessionFactory.getCurrentSession();
		Usuario usuario = session.get(Usuario.class, usuarioId);
		
		Set <Torneo>torneosQueParticipo = usuario.getTorneosParticipa();
		
		return torneosQueParticipo;
				
		
	}
	
	
	@Override
	public List<Torneo> mostrarTorneosQueCree(Long usuarioId) {
		
		final Session session = sessionFactory.getCurrentSession();
		
		
		return session.createCriteria(Torneo.class).add(Restrictions.eq("creador.id",usuarioId)).list();
		

		
	}

	@Override
	public List<Usuario> usuariosMasGanadores() {
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

	
	@Override
	public void cerrarSesion() {
		final Session session = sessionFactory.getCurrentSession();
		
		//session.disconnect();
		//session.close();
	}

	@Override
	public void cerrarSesion(Long usuarioId) {
		// TODO Auto-generated method stub
		
	}

	
}
