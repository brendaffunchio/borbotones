package ar.edu.unlam.tallerweb1.controladores;

import static org.mockito.Mockito.mock;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.fileupload.FileUploadException;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.protobuf.Empty;
import com.sun.xml.fastinfoset.tools.FI_DOM_Or_XML_DOM_SAX_SAXEvent;

import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.FotoInexistenteExeception;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioCiudad;
import ar.edu.unlam.tallerweb1.servicios.ServicioInmueble;
import ar.edu.unlam.tallerweb1.servicios.ServicioProvincia;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


public class ControladorInmuebleTest {
	
	@Test 
	public void testQueVerificaQueLaFotoSeCargaEnElRepositorio() throws FotoInexistenteExeception {
		
		// preparacion
		ServicioInmueble servicioInmuebleMock = mock(ServicioInmueble.class);
		
		MultipartFile fotoMock = mock(MultipartFile.class);
		
		doNothing().when(servicioInmuebleMock).validarFoto(fotoMock);
		
		// validacion
		
		assertThat(fotoMock).isNotNull();
		
	}
	
	@Test(expected = FotoInexistenteExeception.class)
	public void testQueVerificaQueLaFotoSeCargaEnElRepositorioYLanzaExeception() throws FotoInexistenteExeception {
		
		// preparacion
		ServicioInmueble servicioInmuebleMock = mock(ServicioInmueble.class);
			
	
		MultipartFile foto= null;
		
		servicioInmuebleMock.validarFoto(foto);
		
	}
	
	
	@Test
	public void testQueVerificaQueSePuedeSubirUnaFoto() throws FileNotFoundException{
		
		// preparacion
				ControladorInmueble controladorInmuebleMock = mock(ControladorInmueble.class);
		
				ServicioInmueble servicioInmuebleMock = mock(ServicioInmueble.class);
				
				Inmueble inmuebleMock = mock(Inmueble.class);
				
				MultipartFile fotoMock = mock(MultipartFile.class);
				
				Direccion direccionMock = mock(Direccion.class);
				
				RedirectAttributes flashMock = mock(RedirectAttributes.class);
				
				ServicioProvincia servicioProvinciaMock = mock(ServicioProvincia.class);
				ServicioCiudad servicioCiudadMock = mock(ServicioCiudad.class);
				
				ControladorInmueble controladorInmueble = new ControladorInmueble(servicioInmuebleMock, servicioCiudadMock , servicioProvinciaMock);
				
				doThrow(new NullPointerException()).when(controladorInmuebleMock).guardarFoto(fotoMock);
				
				doNothing().when(servicioInmuebleMock).guardarInmueble(inmuebleMock, direccionMock);
				
				// ejecucion
				ModelAndView mav = controladorInmueble.crearInmueble("Machado", 2290, fotoMock, inmuebleMock, flashMock);
				
				// validacion
				
				assertThat(mav.getModel().get("error")).isEqualTo("No seleccióno una foto");
				
				
	}

	

}
