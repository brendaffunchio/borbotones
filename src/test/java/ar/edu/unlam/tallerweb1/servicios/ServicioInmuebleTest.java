package ar.edu.unlam.tallerweb1.servicios;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.DireccionDuplicadaException;
import ar.edu.unlam.tallerweb1.modelo.DireccionNoValidaException;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.InmuebleInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.InmuebleNoDisponibleException;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.UsuarioInexistenteException;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioInmueble;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

public class ServicioInmuebleTest {
	
	private RepositorioInmueble repositorioInmuebleMock = mock(RepositorioInmueble.class);
	private RepositorioUsuario repositorioUsuarioMock = mock(RepositorioUsuario.class);
	private ServicioInmueble servicio = new ServicioInmuebleImpl(repositorioInmuebleMock, repositorioUsuarioMock);
	Direccion direccion= new Direccion();
	private List<Inmueble> listaInmuebleMock = mock(List.class);
	

	private Inmueble crearInmueble() {

		Inmueble inmueble = new Inmueble();
		inmueble.setId(1L);
		inmueble.setNombre("Depto Gamer 1");
		inmueble.setDisponible(true);
		inmueble.setPrecio(3000d);
		inmueble.setFoto("foto");
		inmueble.setDireccion(direccion);
	
		return inmueble;
	}
	

	
    @Test
	public void queSePuedaGuardarUnInmueble() throws DireccionDuplicadaException, DireccionNoValidaException {
	 
	 //preparacion
	 Inmueble inmueble=crearInmueble();
	
	
	//ejecucion
	servicio.guardarInmueble(inmueble, direccion);
	
	//comprobacion
	verify(repositorioInmuebleMock, times(1)).guardarInmueble(inmueble);
	
	 
	 
 }
    @Test(expected=DireccionNoValidaException.class)
  	public void cuandoUnInmuebleSeIntentaGuardarConUnaDireccionInexistenteLanzaDireccionNoValidaException() throws DireccionDuplicadaException, DireccionNoValidaException {
  	 
  	 //preparacion
  	 Inmueble inmueble=crearInmueble();
  	Direccion direccionInmueble = null;
  	
  	//ejecucion
  	servicio.guardarInmueble(inmueble, direccionInmueble);
  	
  	//comprobacion
  	verify(repositorioInmuebleMock, never()).guardarInmueble(inmueble);
  	
  	 
  	 
   }
  	@Test
    public void queSeMuestreLaListaDeInmuebles() {
    	//preparacion
  		Inmueble inmueble= crearInmueble();
  		
  		//ejecucion
  		when(servicio.listarTodosLosInmuebles()).thenReturn(listaInmuebleMock);
  		servicio.listarTodosLosInmuebles();
  		
  		//comprobacion
  		verify(repositorioInmuebleMock, times(1)).listarTodosLosInmuebles();
    	
    }
  	
  	@Test 
  	public void queBusqueUnInmueblePorElIdDeUnaProvinciaYElNombreDeUnaCiudad() {
  		//preparacion
  		Inmueble inmueble=crearInmueble();
  		Long provinciaId=1L;
  		String nombreCiudad="Canuelas";
  		
  		//ejecucion
  		when(servicio.buscarInmueble(provinciaId, nombreCiudad)).thenReturn(listaInmuebleMock);
  		servicio.buscarInmueble(provinciaId, nombreCiudad);
  		
  		//comprobacion
  		verify(repositorioInmuebleMock,times(1)).buscarInmueble(provinciaId, nombreCiudad);
  	}
  	
  	@Test
  	public void consultarInmueblePorId() {
  		//preparacion
  		Inmueble inmueble=crearInmueble();
  		
  		//ejecucion
  		when(servicio.consultarInmueblePorId(inmueble.getId())).thenReturn(inmueble);
  		servicio.consultarInmueblePorId(inmueble.getId());
  		
  		//comprobacion
  		verify(repositorioInmuebleMock,times(1)).consultarInmueblePorId(inmueble.getId());
  		
  	}
  	
  	@Test
  	public void queSeAgregueUnInquilinoAlInmueble() throws InmuebleNoDisponibleException, InmuebleInexistenteException, UsuarioInexistenteException {
  	//preparacion
  		Inmueble inmueble = crearInmueble();
  		Usuario usuario = new Usuario();
  		usuario.setId(1L);
  	
  		//ejecucion
  		//da rojo porque el inmueble y el usuario los tiene que consultar en la base de datos
  		servicio.agregarInquilino(inmueble.getId(), usuario.getId());
  		
  		
  		//comprobacion 
  		verify(repositorioInmuebleMock,times(1)).modificarInmueble(inmueble);
  	}
  	
  	@Test
  	public void queFiltreLosInmueblesSegunElPrecioElegido() {
  		//preparacion
  		Inmueble inmueble= crearInmueble();
  		Double desde= 2000d;
  		Double hasta = 3000d;
  		
  		//ejecucion
  		when(servicio.filtrarInmueblesPorPrecio(desde, hasta)).thenReturn(listaInmuebleMock);
  		servicio.filtrarInmueblesPorPrecio(desde, hasta);
  		
  		//comprobacion
  		verify(repositorioInmuebleMock,times(1)).filtrarInmueblesPorPrecio(desde, hasta);
  		
  	}
}
