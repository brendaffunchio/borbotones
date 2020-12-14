package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.DireccionNoValidaException;
import ar.edu.unlam.tallerweb1.modelo.PasswordVaciaException;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.UsuarioYaExisteException;
import ar.edu.unlam.tallerweb1.servicios.ServicioCiudad;
import ar.edu.unlam.tallerweb1.servicios.ServicioDireccion;
import ar.edu.unlam.tallerweb1.servicios.ServicioProvincia;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuarios;

@Controller
public class ControladorUsuario {

	
	private ServicioUsuarios servicioUsuarios;
	public ServicioCiudad servicioCiudad;
    public ServicioProvincia servicioProvincia;
	public ServicioDireccion servicioDireccion;
	
	@Autowired
	public ControladorUsuario(ServicioUsuarios servicioUsuarios, 
			ServicioCiudad servicioCiudad, ServicioProvincia servicioProvincia
			, ServicioDireccion servicioDireccion){
		this.servicioUsuarios = servicioUsuarios;
		this.servicioCiudad = servicioCiudad;
		this.servicioProvincia = servicioProvincia;
		this.servicioDireccion= servicioDireccion;
	}

	

	@RequestMapping (path="ver-formulario-usuario", method= RequestMethod.GET)
	public ModelAndView nuevoUsuario() {
		ModelMap model = new ModelMap();
		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		model.put("provincias", servicioProvincia.listarTodasProvincias());
		model.put("ciudades", servicioCiudad.listarCiudades());
		
		return new ModelAndView ("formularioUsuario", model);
		
	}
	
	
	@RequestMapping (path="crear-usuario",method=RequestMethod.POST)
	public ModelAndView crearUsuario (@RequestParam(name="calle") String calle,
			@RequestParam(name="numero") Integer numero, @ModelAttribute ("usuario") Usuario usuario, HttpServletRequest request) {
		ModelMap modelo = new ModelMap();
	
        Direccion direccion = servicioDireccion.buscarDireccion(calle, numero);
		
			try {
				servicioUsuarios.guardarUsuario(usuario, direccion);
			} catch (PasswordVaciaException | UsuarioYaExisteException | DireccionNoValidaException e) {
				modelo.put("errorRegistrar", e.getMessage());
				return new ModelAndView("errores",modelo);
			}
			
		return new ModelAndView ("registracionExitosa");
	
}
	
	@RequestMapping(path="ver-perfil-del-usuario")
	public ModelAndView verPerfilDelUsuario() {
		
		return new ModelAndView ("perfilUsuario");
		
		
	}
	
	@RequestMapping(path = "ver-inmuebles-alquilados", method = RequestMethod.GET)
	public ModelAndView mostrarMisInmuebles(@RequestParam("usuarioId") Long usuarioId) {

		ModelMap modelo = new ModelMap();
		 if(servicioUsuarios.listarInmueblesAlquiladosDeUnUsuario(usuarioId).isEmpty()) {
			 modelo.put("error", "No alquilaste ningún inmueble");
		 }else {
			 modelo.put("misInmueblesAlquilados", servicioUsuarios.listarInmueblesAlquiladosDeUnUsuario(usuarioId));
		 }
				
		return new ModelAndView("misInmueblesAlquilados", modelo);
	}

	@RequestMapping(path = "ver-torneos-que-participo", method = RequestMethod.GET)
	public ModelAndView mostrarTorneosQueParticipaElUsuario(@RequestParam("usuarioId") Long usuarioId) {

		ModelMap modelo = new ModelMap();
		
		if (servicioUsuarios.listarTorneosQueParticipaUnUsuario(usuarioId).isEmpty()) {
			modelo.put("error", "No estás participando en ningún torneo");
		}else {
			modelo.put("misTorneosParticipo", servicioUsuarios.listarTorneosQueParticipaUnUsuario(usuarioId));
		}
		
		return new ModelAndView("misTorneosParticipo", modelo);
	}

	@RequestMapping(path = "ver-torneos-que-cree")
	public ModelAndView mostrarTorneosQueCreoElUsuario(@RequestParam("usuarioId") Long usuarioId) {

		ModelMap modelo = new ModelMap();
		
		if (servicioUsuarios.listarTorneosQueCreoUnUsuario(usuarioId).isEmpty()) {
			modelo.put("error", "No creaste ningún torneo");
		}else {
			modelo.put("misTorneosCreados", servicioUsuarios.listarTorneosQueCreoUnUsuario(usuarioId));
		}
		
		return new ModelAndView("misTorneosCreados", modelo);
	}
	
	@RequestMapping(path = "logout")
	public ModelAndView cerrarSesion(HttpServletRequest request) {
		request.getSession().removeAttribute("rol");
		request.getSession().removeAttribute("id");
		request.getSession().invalidate();
		
		return new ModelAndView("redirect:/login");
	}
	
	}
	
	

