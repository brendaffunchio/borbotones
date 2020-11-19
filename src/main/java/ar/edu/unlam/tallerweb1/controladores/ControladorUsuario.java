package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioCiudad;
import ar.edu.unlam.tallerweb1.servicios.ServicioProvincia;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuarios;

@Controller
public class ControladorUsuario {

	
	private ServicioUsuarios servicioUsuarios;
	public ServicioCiudad servicioCiudad;
    public ServicioProvincia servicioProvincia;
	
	@Autowired
	public ControladorUsuario(ServicioUsuarios servicioUsuarios, ServicioCiudad servicioCiudad, ServicioProvincia servicioProvincia){
		this.servicioUsuarios = servicioUsuarios;
		this.servicioCiudad = servicioCiudad;
		this.servicioProvincia = servicioProvincia;
	}

	

	@RequestMapping (path="ver-formulario-usuario", method= RequestMethod.GET)
	public ModelAndView nuevoUsuario() {
		ModelMap model = new ModelMap();
		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		model.put("provincias", servicioProvincia.mostrarProvincias());
		model.put("ciudades", servicioCiudad.mostrarCiudades());
		
		return new ModelAndView ("formularioUsuario", model);
		
	}
	
	
	@RequestMapping (path="crear-usuario",method=RequestMethod.POST)
	public ModelAndView crearUsuario (@RequestParam(name="calle") String calle,
			@RequestParam(name="numero") Integer numero, @ModelAttribute ("usuario") Usuario usuario, HttpServletRequest request) {
		ModelMap modelo = new ModelMap();
		
        Direccion direccion = new Direccion ();
		
		direccion.setCalle(calle);
		direccion.setNumero(numero);
		
		servicioUsuarios.guardarUsuario(usuario, direccion);
		
		return new ModelAndView ("registracionExitosa");

	
}
	
	@RequestMapping(path="ver-perfil-del-usuario")
	public ModelAndView verPerfilDelUsuario(@RequestParam("usuarioId") Long usuarioId) {
		
		ModelMap modelo = new ModelMap();
		
		return new ModelAndView ("perfilUsuario");
		
		
	}
	
	@RequestMapping(path = "ver-inmuebles-alquilados", method = RequestMethod.GET)
	public ModelAndView mostrarMisInmuebles(@RequestParam("usuarioId") Long usuarioId) {

		ModelMap modelo = new ModelMap();
		modelo.put("misInmueblesAlquilados", servicioUsuarios.mostrarInmueblesAlquilados(usuarioId));

		return new ModelAndView("misInmueblesAlquilados", modelo);
	}

	@RequestMapping(path = "ver-torneos-que-participo", method = RequestMethod.GET)
	public ModelAndView mostrarTorneosQueParticipaElUsuario(@RequestParam("usuarioId") Long usuarioId) {

		ModelMap modelo = new ModelMap();
		modelo.put("misTorneosParticipo", servicioUsuarios.mostrarTorneosQueParticipo(usuarioId));

		return new ModelAndView("misTorneosParticipo", modelo);
	}

	@RequestMapping(path = "ver-torneos-que-cree", method = RequestMethod.GET)
	public ModelAndView mostrarTorneosQueCreoElUsuario(@RequestParam("usuarioId") Long usuarioId) {

		ModelMap modelo = new ModelMap();
		modelo.put("misTorneosCreados", servicioUsuarios.mostrarTorneosQueCree(usuarioId));

		return new ModelAndView("misTorneosCreados", modelo);
	}
}	
	

