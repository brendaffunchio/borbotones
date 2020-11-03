package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.QueryHint;

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
	public void guardarUsuario(Usuario usuario) {
		String rol = "invitado";
		usuario.setRol(rol);
		

		final Session session = sessionFactory.getCurrentSession();
			session.save(usuario);
	}

	@Override
	public List<String> mostrarEmails() {
		final Session session = sessionFactory.getCurrentSession();

		List<Usuario> usuarios = session.createCriteria(Usuario.class).list();

		List<String> emails = new LinkedList();

		for (Usuario usuario : usuarios) {
			String email = usuario.getEmail();
			emails.add(email);
		}

		return emails;
	}

	@Override
	public Boolean devolverEstadoDelEmail(String emailParametro) {
		final Session session = sessionFactory.getCurrentSession();
		
		
		List<Usuario> usuarios = session.createCriteria(Usuario.class).list();

		List<String> emails = new LinkedList();
		
		Boolean esRepetido=false;
		for (Usuario usuario : usuarios) {
			
			if(usuario.getEmail().equals(emailParametro)) 
				
				esRepetido = true;
			
			}

		return esRepetido;
	}

	@Override
	public List<String> emailsUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}
	


	

}
