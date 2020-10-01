package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioTorneo;


@Controller
public class ControladorTorneo {
	
	@Autowired
	private ServicioTorneo servicioTorneo;


//	@RequestMapping	(path="/torneos", method= RequestMethod.POST)
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
	
	@RequestMapping (path="/torneos",method = RequestMethod.GET)
	public ModelAndView MostrarTorneos() {
		
		ModelMap modelo= new ModelMap();
		modelo.put("TORNEOS", servicioTorneo.mostrarTorneos());
		
		return new ModelAndView("torneosParaParticipar",modelo);
	}
	
	
}
