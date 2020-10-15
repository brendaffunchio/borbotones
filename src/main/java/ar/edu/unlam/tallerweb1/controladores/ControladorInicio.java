package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorInicio {

	
	
	@RequestMapping(path = "inicio", method = RequestMethod.GET)
	public ModelAndView irAHome() {
		return new ModelAndView("inicio");


	}
	
	@RequestMapping(path = "contacto", method = RequestMethod.GET)
	public ModelAndView irAContacto() {
		return new ModelAndView("contacto");


	}
}
