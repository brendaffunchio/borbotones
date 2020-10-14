package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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

	
	
	@RequestMapping (path="ver-torneos",method = RequestMethod.GET)
	public ModelAndView mostrarTorneos() {
		
		ModelMap modelo= new ModelMap();
		modelo.put("torneos", servicioTorneo.mostrarTorneos());
		
		return new ModelAndView("torneosParaParticipar",modelo);
	}
	
	@RequestMapping (path="ver-formulario-torneo",method=RequestMethod.GET)
	public ModelAndView nuevoTorneo() {
		Torneo torneo = new Torneo();
		ModelMap modelo= new ModelMap();
		
		modelo.put("torneo",torneo);
		
		return new ModelAndView ("organizarTorneos",modelo);
		
	}
	
	@RequestMapping (path="crear-torneo",method=RequestMethod.POST)
	public ModelAndView crearTorneo(@ModelAttribute ("torneo")Torneo torneo) {
		
		servicioTorneo.guardarTorneo(torneo);
		
		return new ModelAndView ("redirect:/torneos");
	}

//	@RequestMapping	(path="participar", method= RequestMethod.POST)
//	public ModelAndView participarTorneo () {
//		
//		ModelMap modelo = new ModelMap();
//		Usuario participante = new Usuario();
//		modelo.put("nombre_participante",participante);
//		
//		modelo.put("PARTICIPANTES", servicioTorneo.agregarParticipanteAlTorneo(participante));
//		
//		return ;
//		
//		
//	}
}
