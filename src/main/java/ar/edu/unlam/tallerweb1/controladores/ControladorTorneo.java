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
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioTorneo;

@Controller
public class ControladorTorneo {

	private ServicioTorneo servicioTorneo;

	@Autowired
	public ControladorTorneo(ServicioTorneo servicioTorneo) {
		this.servicioTorneo = servicioTorneo;
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

		modelo.put("torneo", torneo);

		return new ModelAndView("organizarTorneos", modelo);

	}

	@RequestMapping(path = "crear-torneo", method = RequestMethod.POST)
	public ModelAndView crearTorneo(@ModelAttribute("torneo") Torneo torneo) {

		servicioTorneo.guardarTorneo(torneo);

		return new ModelAndView("redirect:/ver-torneos");
	}

	@RequestMapping(path = "ver-formulario-participar")
	public ModelAndView nuevoParticipante() {

		ModelMap modelo = new ModelMap();
		Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);

		return new ModelAndView("participarTorneo", modelo);

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
