package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.servicios.ServicioInmueble;

@Controller
public class ControladorInmueble {

	@Autowired
	public ServicioInmueble servicioInmueble;
	
	@Autowired
	public ControladorInmueble(ServicioInmueble servicioInmueble) {
		
		this.servicioInmueble=servicioInmueble;
		
	}
	
	
	@RequestMapping (path="ver-inmuebles",method = RequestMethod.GET)
	public ModelAndView mostrarInmuebles() {
		
		ModelMap modelo= new ModelMap();
		modelo.put("inmuebles", servicioInmueble.mostrarInmuebles());
		
		return new ModelAndView("InmueblesParaAlquilar",modelo);
	}
	
	
	@RequestMapping(path="ver-formulario-inmueble", method=RequestMethod.GET)
	public ModelAndView nuevoInmueble() {
		
		ModelMap modelo = new ModelMap();
		Inmueble inmueble = new Inmueble();
		modelo.put("inmueble",inmueble);
		
		return new ModelAndView ("publicarInmueble",modelo);
		
	}
	
	@RequestMapping(path="crear-inmueble", method=RequestMethod.POST)
	public ModelAndView crearInmueble (@ModelAttribute ("inmueble") Inmueble inmueble) {
		
		servicioInmueble.guardarInmueble(inmueble);
		
		return new ModelAndView ("redirect:/ver-inmuebles");
		
	}
}
