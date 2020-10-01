package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.servicios.ServicioInmueble;

@Controller
public class ControladorInmueble {

	@Autowired
	public ServicioInmueble servicioInmueble;
	
	@RequestMapping (path="/inmuebles",method = RequestMethod.GET)
	public ModelAndView MostrarInmuebles() {
		
		ModelMap modelo= new ModelMap();
		modelo.put("INMUEBLES", servicioInmueble.mostrarInmuebles());
		
		return new ModelAndView("InmueblesParaAlquilar",modelo);
	}
	
	
}
