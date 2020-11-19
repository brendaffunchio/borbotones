package ar.edu.unlam.tallerweb1.repositorios;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
	public List<Inmueble> buscarInmueble(String nombreProvincia, String nombreCiudad) {
		
		final Session session = sessionFactory.getCurrentSession();
		List <Inmueble> inmueblesBuscados = new LinkedList<Inmueble>();
		
		Provincia provincia = (Provincia) session.createCriteria(Provincia.class)
				.add(Restrictions.eq("nombre", nombreProvincia)).uniqueResult();
		Ciudad ciudad = (Ciudad) session.createCriteria(Ciudad.class)
				.add(Restrictions.eq("nombre", nombreCiudad)).uniqueResult();
		
		if (nombreProvincia != null && !nombreProvincia.equals("") && nombreCiudad != null && !nombreCiudad.equals(""))
			inmueblesBuscados = session.createCriteria(Inmueble.class).createAlias("direccion", "direccionBuscada")
			.add(Restrictions.like("direccionBuscada.ciudad",ciudad))
				
			.add(Restrictions.eq("disponible", true)).list();

		return inmueblesBuscados;

		
		//hacer test
	}

	@Override
	public Inmueble verDetallesInmueble(Long id) {

		return sessionFactory.getCurrentSession().get(Inmueble.class, id);

	}

	@Override
	public void agregarInquilino(Long inmuebleId, Long usuarioId) {
		
		final Session session = sessionFactory.getCurrentSession();

		Inmueble inmueble = session.get(Inmueble.class, inmuebleId);
		Usuario inquilino = session.get(Usuario.class, usuarioId);
		List <Inmueble> inmueblesAlquilados = inquilino.getInmueblesAlquilados();
		

		if(inmueble.getDisponible().equals(true)) {
			inmueble.setInquilino(inquilino);
			inmueblesAlquilados.add(inmueble);
			inmueble.setDisponible(false);
		}
		
	}
	
	

}
