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
import ar.edu.unlam.tallerweb1.modelo.FotoInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioCiudad;
import ar.edu.unlam.tallerweb1.servicios.ServicioFoto;
import ar.edu.unlam.tallerweb1.servicios.ServicioInmueble;
import ar.edu.unlam.tallerweb1.servicios.ServicioProvincia;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


public class ControladorInmuebleTest {
	
	
	@Test(expected = FotoInexistenteException.class)
	public void testQueVerificaQueLaFotoSeCargaEnElRepositorioYLanzaExeception() throws FotoInexistenteException {
		
		// preparacion
		ServicioFoto servicioInmuebleMock = mock(ServicioFoto.class);
			
	
		MultipartFile foto= null;
		
		servicioInmuebleMock.validarFoto(foto);
		
	}
	
	
	

	

}
