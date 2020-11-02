package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.servicios.ServicioInmueble;

@Controller
public class ControladorInmueble {

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
	
	
	@RequestMapping(path = "buscar-inmueble", method = RequestMethod.GET)
	public ModelAndView mostrarTorneosPorJuego(HttpServletRequest request) {

		ModelMap modelo = new ModelMap();
		String provincia = request.getParameter("busqueda");
		String localidad = request.getParameter("busqueda");
		modelo.put("inmueblesBusqueda", servicioInmueble.buscarInmueble(provincia,localidad));

		return new ModelAndView("inmueblesPorBusqueda", modelo);
	}
	
	@RequestMapping (path="ver-inmueble-detalles/{id}",method=RequestMethod.GET)
	public ModelAndView verDetallesInmueble (@PathVariable Long id) {
		
	Inmueble inmuebleDetalle = servicioInmueble.verDetallesInmueble(id);

		ModelMap modelo = new ModelMap();
		modelo.put("inmuebleDetalle", inmuebleDetalle );
		
		return new ModelAndView ("detallesInmueble",modelo);
	}
	
}
