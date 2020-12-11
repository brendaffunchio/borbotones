package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.servicios.ServicioUsuarios;

@Controller
public class ControladorInicio {

	ServicioUsuarios servicioUsuarios;
	
	@Autowired
	public ControladorInicio(ServicioUsuarios servicioUsuarios) {
		
		this.servicioUsuarios = servicioUsuarios;
	}
	
	@RequestMapping(path = "inicio", method = RequestMethod.GET)
	public ModelAndView irAHome() {
				
			ModelMap modelo = new ModelMap();
			
			modelo.put("usuarios", servicioUsuarios.listarUsuariosMasGanadores());
			
			
		return new ModelAndView("inicio",modelo);


	}
	
	

	@RequestMapping(path = "contacto", method = RequestMethod.GET)
	public ModelAndView irAContacto() {
		return new ModelAndView("contacto");


	}
	

	

}
