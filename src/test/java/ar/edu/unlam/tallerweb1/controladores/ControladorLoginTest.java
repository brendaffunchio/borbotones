package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;

import static org.mockito.Mockito.*;

public class ControladorLoginTest {

	@Test
	public void validarLoginSiUsuarioNoExisteDeberiaRemitirALoginYMostrarMensajeError() {

		// preparacion
		ServicioLogin servicioLoginMock = mock(ServicioLogin.class);

		Usuario usuarioMock = mock(Usuario.class);

		Usuario usuarioBuscadoMock = mock(Usuario.class);
		HttpServletRequest requestMock = mock(HttpServletRequest.class);

		HttpSession sessionMock = mock(HttpSession.class);

		ControladorLogin controladorLogin = new ControladorLogin(servicioLoginMock);

		when(servicioLoginMock.consultarUsuario(usuarioMock)).thenReturn(null);
		when(usuarioBuscadoMock.getRol()).thenReturn("admin");

		// ejecucion
		ModelAndView mav = controladorLogin.validarLogin(usuarioMock, requestMock);

		// validacion
		assertThat(mav.getViewName()).isEqualTo("login");
		assertThat(mav.getModel().get("error")).isEqualTo("Usuario o clave incorrecta");
		// espia
		verify(sessionMock, times(0)).setAttribute("rol", "admin");

	}

	@Test
	public void loginConUsuarioYPasswordCorrectosDeberiaLlevarAlIinicio() {

		// preparacion
		ServicioLogin servicioLoginMock = mock(ServicioLogin.class);

		Usuario usuarioMock = mock(Usuario.class);

		HttpServletRequest requestMock = mock(HttpServletRequest.class);

		HttpSession sessionMock = mock(HttpSession.class);

		ControladorLogin controladorLogin = new ControladorLogin(servicioLoginMock);

		when(requestMock.getSession()).thenReturn(sessionMock);
		when(usuarioMock.getRol()).thenReturn("invitado");
		when(servicioLoginMock.consultarUsuario(usuarioMock)).thenReturn(usuarioMock);

		// ejecucion
		ModelAndView mav = controladorLogin.validarLogin(usuarioMock, requestMock);

		// validacion
		assertThat(mav.getViewName()).isEqualTo("redirect:/inicio");
		// espía
		verify(sessionMock, times(1)).setAttribute("rol", "invitado");

	}

}
