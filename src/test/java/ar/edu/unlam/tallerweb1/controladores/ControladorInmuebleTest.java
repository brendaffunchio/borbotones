package ar.edu.unlam.tallerweb1.controladores;

import static org.mockito.Mockito.mock;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.fileupload.FileUploadException;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.protobuf.Empty;

import ar.edu.unlam.tallerweb1.modelo.Ciudad;
import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.FotoInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.Provincia;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioCiudad;
import ar.edu.unlam.tallerweb1.servicios.ServicioDireccion;
import ar.edu.unlam.tallerweb1.servicios.ServicioFoto;
import ar.edu.unlam.tallerweb1.servicios.ServicioInmueble;
import ar.edu.unlam.tallerweb1.servicios.ServicioProvincia;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuarios;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


public class ControladorInmuebleTest {
	
private ServicioInmueble servicioInmuebleMock= mock(ServicioInmueble.class);
private ServicioUsuarios servicioUsuarioMock = mock(ServicioUsuarios.class);
private ServicioCiudad servicioCiudadMock= mock(ServicioCiudad.class);
private ServicioProvincia servicioProvinciaMock= mock(ServicioProvincia.class);
private ServicioDireccion servicioDireccionMock= mock(ServicioDireccion.class);
private ServicioFoto servicioFotoMock= mock(ServicioFoto.class);
private List<Inmueble>inmueblesMock = mock(List.class);
private List<Provincia>provinciasMock = mock(List.class);
private List<Ciudad>ciudadesMock = mock(List.class);
private ControladorInmueble controlador = new ControladorInmueble(servicioInmuebleMock, servicioCiudadMock, servicioProvinciaMock, servicioDireccionMock, servicioUsuarioMock, servicioFotoMock);


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
public void queRetorneALaVistaInmueblesParaAlquilar() {
	//preparacion
	when(servicioInmuebleMock.listarTodosLosInmueblesDisponibles()).thenReturn(inmueblesMock);
when(servicioProvinciaMock.listarTodasProvincias()).thenReturn(provinciasMock);
	
//ejecucion
	final ModelAndView vista = controlador.mostrarInmuebles();
	
	//comprobacion
	assertThat(vista.getViewName()).isEqualTo("InmueblesParaAlquilar");
}

@Test 
public void queCuandoSeQuieraPublicarUnInmuebleRetorneALaVistaPublicarInmueble() {
	
	//preparacion
	Inmueble inmueble = new Inmueble();
	when(servicioProvinciaMock.listarTodasProvincias()).thenReturn(provinciasMock);
	when(servicioCiudadMock.listarCiudades()).thenReturn(ciudadesMock);

	//ejecucion
	final ModelAndView vista = controlador.nuevoInmueble();
	
	//comprobacion
	assertThat(vista.getViewName()).isEqualTo("publicarInmueble");

}



}
