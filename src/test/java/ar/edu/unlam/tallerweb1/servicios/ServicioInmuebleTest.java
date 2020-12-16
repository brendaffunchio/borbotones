package ar.edu.unlam.tallerweb1.servicios;

import static org.mockito.Mockito.*;

import java.util.LinkedList;
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
		

	private Inmueble crearInmueble() {

		Inmueble inmueble = new Inmueble();
		inmueble.setId(1L);
		inmueble.setNombre("Depto Gamer 1");
		inmueble.setDisponible(true);
		inmueble.setPrecio(3000d);
		inmueble.setFoto("foto");
	
		return inmueble;
	}
	
private Direccion crearDireccion() {
	Direccion direccion= new Direccion();
	direccion.setCalle("Libertad");
	direccion.setNumero(325);
	return direccion;
}
	
    @Test
	public void queSePuedaGuardarUnInmueble() throws DireccionDuplicadaException, DireccionNoValidaException {
	 
	 //preparacion
    Direccion direccion1= crearDireccion();
    Direccion direccion2= crearDireccion();
    direccion2.setNumero(123);
    Inmueble inmueble1=crearInmueble();
    inmueble1.setDireccion(direccion1);
	
	 List <Inmueble>inmuebles= new LinkedList<Inmueble>();
  	 inmuebles.add(inmueble1);
	
  	 Inmueble inmueble2=crearInmueble();
  	 
	//ejecucion
  	when(repositorioInmuebleMock.listarTodosLosInmuebles()).thenReturn(inmuebles);
	servicio.guardarInmueble(inmueble2, direccion2);
	
	//comprobacion
	verify(repositorioInmuebleMock, times(1)).guardarInmueble(inmueble2);
	
	 
	 
 }
    @Test(expected=DireccionNoValidaException.class)
  	public void cuandoUnInmuebleSeIntentaGuardarConUnaDireccionInexistenteLanzaDireccionNoValidaException() throws DireccionDuplicadaException, DireccionNoValidaException {
  	 
  	 //preparacion
  	 Inmueble inmueble=crearInmueble();
  	 Direccion direccion = null;
  	List <Inmueble>inmuebles= new LinkedList<Inmueble>();
 	 inmuebles.add(inmueble);
  	
  	//ejecucion
 	when(repositorioInmuebleMock.listarTodosLosInmuebles()).thenReturn(inmuebles);
  	servicio.guardarInmueble( inmueble,direccion);
  	
  	//comprobacion
  	verify(repositorioInmuebleMock, never()).guardarInmueble(inmueble);
  	
  	 
  	 
   }
    @Test(expected=DireccionDuplicadaException.class)
  	public void cuandoUnInmuebleSeIntentaGuardarConUnaDireccionDuplicadaLanzaDireccionDuplicadaException() throws DireccionDuplicadaException, DireccionNoValidaException {
  	 
  	 //preparacion
    	Direccion direccion= crearDireccion();
  	Inmueble inmueble1=crearInmueble();
  	Inmueble inmueble2= crearInmueble();
  	 
  	 inmueble1.setDireccion(direccion);
  	
  	 
  	 List <Inmueble>inmuebles= new LinkedList<Inmueble>();
  	 inmuebles.add(inmueble1);
  	 
  	//ejecucion
  	when(repositorioInmuebleMock.listarTodosLosInmuebles()).thenReturn(inmuebles);
  	servicio.guardarInmueble( inmueble2,direccion);
  	
  	//comprobacion
  	verify(repositorioInmuebleMock, never()).guardarInmueble(inmueble2);
  	
  	 
  	 
   }
  	@Test
    public void queSeMuestreLaListaDeInmueblesDisponibles() {
    	//preparacion
  		Inmueble inmueble= crearInmueble();
  		List<Inmueble>inmuebles = new LinkedList<Inmueble>();
  		inmuebles.add(inmueble);
  		//ejecucion
  		when(repositorioInmuebleMock.listarTodosLosInmueblesDisponibles()).thenReturn(inmuebles);
  		servicio.listarTodosLosInmueblesDisponibles();
  		
  		//comprobacion
  		verify(repositorioInmuebleMock, times(1)).listarTodosLosInmueblesDisponibles();
    	
    }
 
  	@Test 
  	public void queBusqueUnInmueblePorElIdDeUnaProvinciaYElNombreDeUnaCiudad() {
  		//preparacion
  		Inmueble inmueble=crearInmueble();
  		Long provinciaId=1L;
  		String nombreCiudad="Canuelas";
 		List<Inmueble>inmuebles = new LinkedList<Inmueble>();
  		inmuebles.add(inmueble);
  		//ejecucion
  		when(repositorioInmuebleMock.buscarInmueble(provinciaId, nombreCiudad)).thenReturn(inmuebles);
  		servicio.buscarInmueble(provinciaId, nombreCiudad);
  		
  		//comprobacion
  		verify(repositorioInmuebleMock,times(1)).buscarInmueble(provinciaId, nombreCiudad);
  	}
  	
  	@Test
  	public void consultarInmueblePorId() {
  		//preparacion
  		Inmueble inmueble=crearInmueble();
  		
  		//ejecucion
  		when(repositorioInmuebleMock.consultarInmueblePorId(inmueble.getId())).thenReturn(inmueble);
  		servicio.consultarInmueblePorId(inmueble.getId());
  		
  		//comprobacion
  		verify(repositorioInmuebleMock,times(1)).consultarInmueblePorId(inmueble.getId());
  		
  	}
	@Test
  	public void queSePuedaAgregarUnInquilino() throws InmuebleNoDisponibleException, InmuebleInexistenteException, UsuarioInexistenteException {
  	//preparacion
  		Inmueble inmueble = crearInmueble();
  		Usuario usuario = new Usuario();
  		usuario.setId(1L);
  	
  		//ejecucion
  		when(repositorioInmuebleMock.consultarInmueblePorId(inmueble.getId())).thenReturn(inmueble);
  		when(repositorioUsuarioMock.consultarUsuarioPorId(usuario.getId())).thenReturn(usuario);
  		servicio.agregarInquilino(inmueble.getId(), usuario.getId());
  		
  		
  		//comprobacion 
  		verify(repositorioInmuebleMock,times(1)).modificarInmueble(inmueble);
  	}
  	
  	
  	@Test(expected=InmuebleInexistenteException.class)
  	public void queLanzeInmuebleInexistenteExcepTionSiElInmuebleEsNuloCuandoSeQuieraAlquilarUnInmueble() throws InmuebleNoDisponibleException, InmuebleInexistenteException, UsuarioInexistenteException {
  	//preparacion
  		Inmueble inmueble = crearInmueble();
  		Usuario usuario = new Usuario();
  		usuario.setId(1L);
  	
  		//ejecucion
  		when(repositorioInmuebleMock.consultarInmueblePorId(inmueble.getId())).thenReturn(null);
  		servicio.agregarInquilino(inmueble.getId(), usuario.getId());
  		
  		
  		//comprobacion 
  		verify(repositorioInmuebleMock,never()).modificarInmueble(inmueble);
  	}
	@Test(expected=InmuebleNoDisponibleException.class)
  	public void queLanzeInmuebleNoDisponibleExcepTionSiElInmuebleNoEstaDisponibleCuandoSeQuieraAlquilarUnInmueble() throws InmuebleNoDisponibleException, InmuebleInexistenteException, UsuarioInexistenteException {
  	//preparacion
  		Inmueble inmueble = crearInmueble();
  		inmueble.setDisponible(false);
  		Usuario usuario = new Usuario();
  		usuario.setId(1L);
  	
  		List <Inmueble>inmuebles= new LinkedList<Inmueble>();
  		inmuebles.add(inmueble);
  		//ejecucion
  		when(repositorioInmuebleMock.consultarInmueblePorId(inmueble.getId())).thenReturn(inmueble);
  		when(repositorioInmuebleMock.listarTodosLosInmueblesDisponibles()).thenReturn(inmuebles);
  		servicio.agregarInquilino(inmueble.getId(), usuario.getId());
  		
  		
  		//comprobacion 
  		verify(repositorioInmuebleMock,never()).modificarInmueble(inmueble);
  	}
	@Test(expected=UsuarioInexistenteException.class)
  	public void queLanzeUsuarioInexistenteExcepTionSiElUsuarioNoExisteCuandoSeQuieraAlquilarUnInmueble() throws InmuebleNoDisponibleException, InmuebleInexistenteException, UsuarioInexistenteException {
  	//preparacion
  		Inmueble inmueble = crearInmueble();
  		Usuario usuario = new Usuario();
  		usuario.setId(1L);
  	
  		List <Inmueble>inmuebles= new LinkedList<Inmueble>();
  		inmuebles.add(inmueble);
  		//ejecucion
  		when(repositorioInmuebleMock.consultarInmueblePorId(inmueble.getId())).thenReturn(inmueble);
  		when(repositorioInmuebleMock.listarTodosLosInmueblesDisponibles()).thenReturn(inmuebles);
  		when (repositorioUsuarioMock.consultarUsuarioPorId(usuario.getId())).thenReturn(null);
  		servicio.agregarInquilino(inmueble.getId(), usuario.getId());
  		
  		
  		//comprobacion 
  		verify(repositorioInmuebleMock,never()).modificarInmueble(inmueble);
  	}
  
  
  	@Test
  	public void queFiltreLosInmueblesSegunElPrecioElegido() {
  		//preparacion
  		Inmueble inmueble= crearInmueble();
  		Double desde= 2000d;
  		Double hasta = 3000d;
 		List<Inmueble>inmuebles = new LinkedList<Inmueble>();
  		inmuebles.add(inmueble);
  		//ejecucion
  		when(repositorioInmuebleMock.filtrarInmueblesPorPrecio(desde, hasta)).thenReturn(inmuebles);
  		servicio.filtrarInmueblesPorPrecio(desde, hasta);
  		
  		//comprobacion
  		verify(repositorioInmuebleMock,times(1)).filtrarInmueblesPorPrecio(desde, hasta);
  		
  	}
}
