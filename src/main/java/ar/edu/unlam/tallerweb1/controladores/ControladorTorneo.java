package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Participante;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioTorneo;



@Controller
public class ControladorTorneo {
	
	@Autowired
	private ServicioTorneo servicioTorneo;

	@Autowired
	public ControladorTorneo(ServicioTorneo servicioTorneo){
		this.servicioTorneo = servicioTorneo;
	}
	
<<<<<<< HEAD
	@RequestMapping(path = "/inicio", method = RequestMethod.GET)
	public ModelAndView irAHome() {
		return new ModelAndView("inicio");
=======
	@RequestMapping (path="ver-torneos",method = RequestMethod.GET)
	public ModelAndView mostrarTorneos() {
		
		ModelMap modelo= new ModelMap();
		modelo.put("torneos", servicioTorneo.mostrarTorneos());
		
		return new ModelAndView("torneosParaParticipar",modelo);
>>>>>>> 10cca270ec755af1d939767dedaae30647d8455d
	}

	
	
	@RequestMapping (path="ver-formulario-torneo",method=RequestMethod.GET)
	public ModelAndView nuevoTorneo() {
		Torneo torneo = new Torneo();
		ModelMap modelo= new ModelMap();
		
		modelo.put("torneo",torneo);
		
		return new ModelAndView ("organizarTorneos",modelo);
		
	}
	
<<<<<<< HEAD
	
	@RequestMapping (path="torneos",method = RequestMethod.GET)
	public ModelAndView MostrarTorneos() {
		
		ModelMap modelo= new ModelMap();
		modelo.put("torneos", servicioTorneo.mostrarTorneos());
		
		return new ModelAndView("torneosParaParticipar",modelo);
	}
	

	@RequestMapping (path="torneosOrganizados",method=RequestMethod.POST)
=======
	@RequestMapping (path="crear-torneo",method=RequestMethod.POST)
>>>>>>> 10cca270ec755af1d939767dedaae30647d8455d
	public ModelAndView crearTorneo(@ModelAttribute ("torneo")Torneo torneo) {
		
		servicioTorneo.guardarTorneo(torneo);
		
		return new ModelAndView ("redirect:/torneos");
	}
	
	@RequestMapping (path="registrar-participante",method=RequestMethod.GET)
	public ModelAndView NuevoParticipante() {
		Participante participante = new Participante();
		ModelMap modelo= new ModelMap();
		
		modelo.put("participante", participante);
		
		return new ModelAndView ("registrarParticipante",modelo);
		
	}
	
	
	
	@RequestMapping (path="agregar-participante", method=RequestMethod.POST)
	public ModelAndView agregarParticipanteAlToreno(@ModelAttribute("participante") Participante participante) {
		
		servicioTorneo.guardarParticipante(participante);
		
		return new ModelAndView ("redirect:/participantes");
	}
	
	@RequestMapping (path="participantes", method=RequestMethod.GET)
	public ModelAndView participantesDelTorneo() {
		
		ModelMap modelo = new ModelMap();
		
		modelo.put("participantes", servicioTorneo.mostrarParticipantes());
		
		return new ModelAndView ("participantesDelTorneo", modelo);
	}




}

