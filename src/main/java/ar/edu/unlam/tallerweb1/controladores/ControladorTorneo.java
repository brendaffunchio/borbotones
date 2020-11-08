package ar.edu.unlam.tallerweb1.controladores;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioInmueble;
import ar.edu.unlam.tallerweb1.servicios.ServicioTorneo;


import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ControladorTorneo {

	private ServicioTorneo servicioTorneo;
	
	private ServicioInmueble servicioInmueble;

	@Autowired
	public ControladorTorneo(ServicioTorneo servicioTorneo, ServicioInmueble servicioInmueble) {
		this.servicioTorneo = servicioTorneo;
		this.servicioInmueble= servicioInmueble;
	}
	
	
	@RequestMapping(path = "ver-torneos", method = RequestMethod.GET)
	public ModelAndView mostrarTorneos() {

		ModelMap modelo = new ModelMap();
		modelo.put("torneos", servicioTorneo.mostrarTorneos());
		

		return new ModelAndView("torneosParaParticipar", modelo);

	}

	@RequestMapping(path = "ver-formulario-torneo", method = RequestMethod.GET)
	public ModelAndView nuevoTorneo() {
		
		Torneo torneo = new Torneo();
		
		ModelMap modelo = new ModelMap();
		
		modelo.put("inmuebles", servicioInmueble.mostrarInmuebles());
		
		modelo.put("torneo", torneo);
		modelo.put("inmuebles", servicioInmueble.mostrarInmuebles());
		
		return new ModelAndView("organizarTorneos", modelo);

	}
	
	
	@RequestMapping(path="crear-torneo", method=RequestMethod.POST)
	public ModelAndView crearTorneo(@RequestParam(name = "file", required = false) MultipartFile foto, Torneo torneo, RedirectAttributes flash)  {
		
	if(!foto.isEmpty()) {
		
//		String currentUsersDir = System.getProperty("user.dir");
//		
//		String ruta = currentUsersDir+"\\src\\main\\webapp\\img";
		
		String currentUsersHomeDir = System.getProperty("user.home");
		
		String ruta = currentUsersHomeDir+"\\eclipse-workspace\\borbotones\\src\\main\\webapp\\img";
		
		try {
			
			byte[] bytes = foto.getBytes();
			
			Path rutaAbsoluta = Paths.get(ruta + "//" + foto.getOriginalFilename());
			Files.write(rutaAbsoluta, bytes);
			torneo.setFoto(foto.getOriginalFilename());
			
			
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		
		}
		
		servicioTorneo.guardarTorneo(torneo);
		
		return new ModelAndView ("redirect:/ver-torneos");
		
	}

	@RequestMapping(path = "nuevos-participantes", method = RequestMethod.POST)
	public ModelAndView crearParticipante(@ModelAttribute("usuario") Usuario usuario) {

		servicioTorneo.guardarParticipante(usuario);

		return new ModelAndView("redirect:/ver-torneos");
	}

	@RequestMapping(path ="buscar-torneo", method = RequestMethod.GET)
	public ModelAndView mostrarTorneosPorJuego(HttpServletRequest request) {

		ModelMap modelo = new ModelMap();
		String categoria = request.getParameter("busqueda");
		String juego = request.getParameter("busqueda");
		modelo.put("torneosBusqueda", servicioTorneo.buscarTorneo(categoria,juego));

		return new ModelAndView("torneosPorBusqueda", modelo);
	}
	
	@RequestMapping (path="ver-torneo-detalles/{id}",method=RequestMethod.GET)
	public ModelAndView verDetallesTorneo (@PathVariable Long id) {
		
	Torneo torneoDetalle = servicioTorneo.verDetallesTorneo(id);

		ModelMap modelo = new ModelMap();
		
		modelo.put("torneoDetalle", torneoDetalle );
		
		return new ModelAndView ("detallesTorneo",modelo);
	}
	
}

