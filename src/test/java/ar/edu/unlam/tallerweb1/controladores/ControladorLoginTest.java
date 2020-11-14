package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.ServletRequest;
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
		when(usuarioBuscadoMock.getRol()).thenReturn("ADMIN");

		// ejecucion
		ModelAndView mav = controladorLogin.validarLogin(usuarioMock, requestMock);

		// validacion
		assertThat(mav.getViewName()).isEqualTo("login");
		assertThat(mav.getModel().get("error")).isEqualTo("Usuario o clave incorrecta");
		// espia
		verify(sessionMock, times(0)).setAttribute("ROL", "ADMIN");

	}

	@Test
	public void loginConUsuarioYPasswordCorrectosDeberiaLlevarAlHome() {

		// preparacion
		ServicioLogin servicioLoginMock = mock(ServicioLogin.class);

		Usuario usuarioMock = mock(Usuario.class);

		HttpServletRequest requestMock = mock(HttpServletRequest.class);

		HttpSession sessionMock = mock(HttpSession.class);

		ControladorLogin controladorLogin = new ControladorLogin(servicioLoginMock);

		when(requestMock.getSession()).thenReturn(sessionMock);
		when(usuarioMock.getRol()).thenReturn("ADMIN");
		when(servicioLoginMock.consultarUsuario(usuarioMock)).thenReturn(usuarioMock);

		// ejecucion
		ModelAndView mav = controladorLogin.validarLogin(usuarioMock, requestMock);

		// validacion
		assertThat(mav.getViewName()).isEqualTo("redirect:/inicio");
		// esp�a
		verify(sessionMock, times(1)).setAttribute("ROL", "ADMIN");

	}
}
