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
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuarios;

@Controller
public class ControladorUsuario {

	
	@Autowired
	private ServicioUsuarios servicioUsuarios;
	
	@Autowired
	public ControladorUsuario(ServicioUsuarios servicioUsuarios){
		this.servicioUsuarios = servicioUsuarios;
	}

	

	@RequestMapping (path="ver-formulario-usuario", method= RequestMethod.GET)
	public ModelAndView nuevoUsuario() {
		ModelMap model = new ModelMap();
		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		
		return new ModelAndView ("formularioUsuario", model);
		
	}
	
	
	@RequestMapping (path="crear-usuario",method=RequestMethod.POST)
	public ModelAndView crearUsuario (@ModelAttribute ("usuario") Usuario usuario, HttpServletRequest request) {
		ModelMap modelo = new ModelMap();
		
		
		servicioUsuarios.guardarUsuario(usuario);
		
		return new ModelAndView ("registracionExitosa");

	
}
	
}	
	

