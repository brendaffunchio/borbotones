package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import org.junit.Test;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.servicios.ServicioInmueble;
import ar.edu.unlam.tallerweb1.servicios.ServicioTorneo;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuarios;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

import static org.mockito.Mockito.*;

public class ControladorTorneoTest{

	@Test
	public void validarQueSeVayaALaVistaDeLosTorneosCuandoTraemosLaListaDeTorneosAunqueEsteVacia() {

		ServicioTorneo servicioTorneoMock = mock(ServicioTorneo.class);
		ServicioUsuarios servicioUsuariosMock = mock(ServicioUsuarios.class);

		List<Torneo> listaTorneoMock = mock(List.class);

		ControladorTorneo controladorTorneo = new ControladorTorneo(servicioTorneoMock, servicioUsuariosMock);

		when(servicioTorneoMock.mostrarTorneos()).thenReturn(listaTorneoMock);

		ModelAndView mav = controladorTorneo.mostrarTorneos();

		assertThat(mav.getViewName()).isEqualTo("torneosParaParticipar");
		assertThat(mav.getModel().isEmpty());

	}

	@Test
	public void validarQueSeCreaUnTorneoYSeRedirigaAlaVistaDeLosTorneos() {
		
	   ServicioTorneo servicioTorneoMock = mock(ServicioTorneo.class);
   	   ServicioUsuarios servicioUsuariosMock = mock(ServicioUsuarios.class);
       ControladorTorneo controladorTorneo = new ControladorTorneo (servicioTorneoMock, servicioUsuariosMock);
   	   Torneo torneoMock=mock(Torneo.class);
   	   Long creadorIdMock =mock(Long.class);
   	   Long inmuebleIdMock = mock(Long.class);
   	   MultipartFile fotoMock = mock(MultipartFile.class);
   	   HttpServletRequest requestMock=mock(HttpServletRequest.class);
   	HttpSession sessionMock = mock(HttpSession.class);
   	   RedirectAttributes flashMock = mock(RedirectAttributes.class);
   	   
   	   when(servicioTorneoMock.guardarTorneo(torneoMock, creadorIdMock, inmuebleIdMock)).thenReturn(true);
   	   
   	   ModelAndView mav = controladorTorneo.crearTorneo(fotoMock, creadorIdMock, torneoMock, requestMock, flashMock);
 
   	   
   	   assertThat(mav.getView()).isEqualTo("redirect:/ver-torneos");
	
   	   verify(sessionMock, times(1)).setAttribute("inscriptos",0);
	}

}
