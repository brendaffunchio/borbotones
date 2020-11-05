package ar.edu.unlam.tallerweb1.controladores;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

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
	
	//(@ModelAttribute ("inmueble") Inmueble inmueble) 
	@RequestMapping(path="crear-inmueble", method=RequestMethod.POST)
	public ModelAndView crearInmueble(@RequestParam(name = "file", required = false) MultipartFile foto, Inmueble inmueble, RedirectAttributes flash)  {
		
		
		
	if(!foto.isEmpty()) {
		
//		String currentUsersDir = System.getProperty("user.dir");
//			
//		String ruta = currentUsersDir+"\\src\\main\\webapp\\img";
		
		
		String currentUsersHomeDir = System.getProperty("user.home");
		
		String ruta = currentUsersHomeDir+"eclipse-workspace\\borbotones\\src\\main\\webapp\\img";
		
		try {
			
			
			byte[] bytes = foto.getBytes();
			
			Path rutaAbsoluta = Paths.get(ruta + "//" + foto.getOriginalFilename());
			Files.write(rutaAbsoluta, bytes);
			inmueble.setFoto(foto.getOriginalFilename());
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		}
		
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
		
	Inmueble inmueble = servicioInmueble.verDetallesInmueble(id);

		ModelMap modelo = new ModelMap();
		modelo.put("detalleInmueble", inmueble );
		
		return new ModelAndView ("inmuebleDetalle",modelo);
		
	}
	
	@RequestMapping(path = "ver-inmueble-detalle", method = RequestMethod.GET)
	public ModelAndView irAi(@RequestParam("id") Long id) {
		
		Long id_inmueble= id;
		Inmueble inmuebleBuscado = servicioInmueble.verDetallesInmueble(id_inmueble);
		
		ModelMap model= new ModelMap();
		
		model.put("detalle", inmuebleBuscado);
		
		return new ModelAndView("inmuebleDetalle");


	}
		
}



