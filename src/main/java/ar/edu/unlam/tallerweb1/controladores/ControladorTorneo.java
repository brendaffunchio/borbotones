package ar.edu.unlam.tallerweb1.controladores;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

import ar.edu.unlam.tallerweb1.modelo.CupoExcedidoException;
import ar.edu.unlam.tallerweb1.modelo.GanadorYaExistenteException;

import ar.edu.unlam.tallerweb1.modelo.ParticipanteDuplicadoException;
import ar.edu.unlam.tallerweb1.modelo.ParticipanteInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.TorneoInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioInmueble;
import ar.edu.unlam.tallerweb1.servicios.ServicioTorneo;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuarios;

import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ControladorTorneo {

	private ServicioTorneo servicioTorneo;

	private ServicioUsuarios servicioUsuario;

	@Autowired
	public ControladorTorneo(ServicioTorneo servicioTorneo, ServicioUsuarios servicioUsuario) {
		this.servicioTorneo = servicioTorneo;
		this.servicioUsuario = servicioUsuario;
	}

	@RequestMapping(path = "ver-torneos", method = RequestMethod.GET)
	public ModelAndView mostrarTorneos(@RequestParam("usuarioId") Long usuarioId) {

		ModelMap modelo = new ModelMap();
		
		if (usuarioId!=null) {
		modelo.put("torneos", servicioTorneo.mostrarTorneosConDistancia(usuarioId));
		}else {
			modelo.put("torneos", servicioTorneo.mostrarTorneos());
		}
		return new ModelAndView("torneosParaParticipar", modelo);

	}
	
	@RequestMapping(path = "ver-torneos-filtrados-distancia", method = RequestMethod.GET)
	public ModelAndView mostrarTorneosSegunDistancia() {

		ModelMap modelo = new ModelMap();
		
		modelo.put("torneos",servicioTorneo.ordenarTorneosSegunDistancia());
		return new ModelAndView("torneosParaParticipar", modelo);

	}

	@RequestMapping(path = "ver-formulario-torneo", method = RequestMethod.GET)
	public ModelAndView nuevoTorneo(@RequestParam("usuarioId") Long usuarioId) {

		Torneo torneo = new Torneo();

		ModelMap modelo = new ModelMap();
	
		modelo.put("torneo", torneo);
		modelo.put("inmuebles", servicioUsuario.mostrarInmueblesAlquilados(usuarioId));

		return new ModelAndView("organizarTorneos", modelo);

	}
	
	public void guardarFoto (MultipartFile foto) {
		if (!foto.isEmpty()) {
			String ruta = "C://Producto//torneos";
			
		 try {
			
			byte[] bytes = foto.getBytes();
			Path rutaAbsoluta=Paths.get(ruta+"//"+foto.getOriginalFilename());
			Files.write(rutaAbsoluta, bytes);
			
		} catch (Exception e) {
			
		}
		}
	}

	@RequestMapping(path = "crear-torneo", method = RequestMethod.POST)
	public ModelAndView crearTorneo(@RequestParam(name = "file", required = false) MultipartFile foto, 
			@RequestParam(name = "creadorId") Long creadorId,
			@RequestParam(name = "inmuebleId",required = false) Long inmuebleId,
			Torneo torneo,RedirectAttributes flash) {
		
        ModelMap modelo= new ModelMap();
        
		guardarFoto(foto);
		torneo.setFoto(foto.getOriginalFilename());
		if(inmuebleId != null) {
		servicioTorneo.guardarTorneo(torneo, creadorId, inmuebleId);
		
		}else {
			modelo.put("errorInmueble", "Necesitas alquilar al menos un inmueble para organizar un torneo");
		return new ModelAndView ("organizarTorneos",modelo);
		}
				
		
		return new ModelAndView ("torneoExitoso");
	}

	@RequestMapping(path = "participar", method=RequestMethod.POST)
	public ModelAndView agregarParticipante(@RequestParam(name="torneoId") Long torneoId,
			@RequestParam(name="usuarioId") Long usuarioId) {
        ModelMap modelo= new ModelMap();
        Torneo torneo =  servicioTorneo.consultarTorneoPorId(torneoId);
        Usuario usuario = servicioUsuario.consultarUsuarioPorId(usuarioId);
        
       if (torneo!=null && usuario!=null) {
		try {
			servicioTorneo.agregarParticipante(torneo, usuario);
		} catch (ParticipanteDuplicadoException | CupoExcedidoException e) {
			modelo.put("errorParticipar", e.getMessage());
			return new ModelAndView("errores",modelo);
		}
       }else {
    	   if (torneo!=null && usuario!=null) {
    		   modelo.put("errorParticipar", "Torneo o Usuario inexistente");
    	   }
       }
       
		return new ModelAndView("participacionExitosa");
	}

	@RequestMapping(path = "desubscribirse",method=RequestMethod.POST)
	public ModelAndView eliminarParticipante(@RequestParam(name="torneoId") Long torneoId,
			@RequestParam(name="usuarioId",required = false) Long usuarioId) {

		ModelMap modelo = new ModelMap();
		Torneo torneo =  servicioTorneo.consultarTorneoPorId(torneoId);
        Usuario participante = servicioUsuario.consultarUsuarioPorId(usuarioId);
        
		if(torneo!=null && participante!=null) {
		try {
			servicioTorneo.eliminarParticipante(torneo,participante);
		} catch (ParticipanteInexistenteException | TorneoInexistenteException e) {
			 modelo.put("errorDesubscribirse", e.getMessage());
			return new ModelAndView("errores", modelo);
		}
		}else {
			if(torneo!=null || participante!=null) {
			modelo.put("errorDesubscribirse","torneo o participante inexistente");
			return new ModelAndView("errores", modelo);
		}}
		return new ModelAndView("perfilUsuario");
	}

	
	@RequestMapping(path = "buscar-torneo", method = RequestMethod.GET)
	public ModelAndView mostrarTorneosPorJuego(HttpServletRequest request) {

		ModelMap modelo = new ModelMap();
		String categoria = request.getParameter("categoria");
		String juego = request.getParameter("juego");
		
		if(servicioTorneo.buscarTorneo(categoria, juego).size() == 0) {
			
			modelo.put("error", "No se encontr� ning�n torneo.");
		}
		
		else {
		
		modelo.put("torneos", servicioTorneo.buscarTorneo(categoria, juego));
		
		}

		return new ModelAndView("torneosParaParticipar", modelo);
	}

	@RequestMapping(path = "ver-torneo-detalles", method = RequestMethod.GET)
	public ModelAndView verDetallesTorneo(@RequestParam("torneoId") Long torneoId) {
	
		Torneo torneoDetalle = servicioTorneo.verDetallesTorneo(torneoId);

		ModelMap modelo = new ModelMap();
	
		modelo.put("torneoDetalle", torneoDetalle);

		return new ModelAndView("detallesTorneo", modelo);
	}

	
	@RequestMapping(path = "ver-mis-torneos-detalles", method = RequestMethod.GET)
	public ModelAndView verDetallesMiTorneoCreado(@RequestParam("torneoId") Long torneoId) {

		Torneo miTorneoDetalle = servicioTorneo.verDetallesTorneo(torneoId);

		ModelMap modelo = new ModelMap();

		modelo.put("miTorneoDetalle", miTorneoDetalle);

		return new ModelAndView("torneoCreadoDetalle", modelo);
	}
	
	@RequestMapping(path = "ver-torneos-participo-detalles", method = RequestMethod.GET)
	public ModelAndView verDetallesTorneoQueParticipo(@RequestParam("torneoId") Long torneoId) {

		
		Torneo torneoParticipoDetalle = servicioTorneo.verDetallesTorneo(torneoId);

		ModelMap modelo = new ModelMap();
		
		modelo.put("torneoParticipoDetalle", torneoParticipoDetalle);

		return new ModelAndView("torneosParticipoDetalle", modelo);
	}
	
	@RequestMapping(path = "ver-lista-de-participantes", method = RequestMethod.GET)
	public ModelAndView verListaDeParticipantesDelTorneo(@RequestParam("torneoId") Long torneoId) {
		
		ModelMap modelo = new ModelMap();
		
		modelo.put("participantes", servicioTorneo.mostrarParticipantesDelTorneo(torneoId));

	   
		return new ModelAndView("participantes", modelo);
		
		
	}
	@RequestMapping(path = "ver-lista-de-participantes-para-elegir-ganador",
			method = RequestMethod.GET)
	public ModelAndView verListaDeParticipantesDelTorneoParaElegirGanador(@RequestParam("torneoId") Long torneoId) {
		
		ModelMap modelo = new ModelMap();
		
		modelo.put("participantes", servicioTorneo.mostrarParticipantesDelTorneo(torneoId));
	    modelo.put("torneoId", torneoId);
	
		return new ModelAndView("ganadorDelTorneo", modelo);
		
		
	}
	@RequestMapping(path = "elegirGanador", method = RequestMethod.POST)
	public ModelAndView elegirGanador(@RequestParam(name="ganadorId") Long ganadorId, @RequestParam(name="torneoGanadoId") Long torneoGanadoId) {
		
ModelMap modelo= new ModelMap();
        Torneo torneo = servicioTorneo.consultarTorneoPorId(torneoGanadoId);
        Usuario usuario = servicioUsuario.consultarUsuarioPorId(ganadorId);
         
         
        if (torneo!=null && usuario!=null) {
        	modelo.put("torneo", torneo);
		try {
			servicioTorneo.elegirGanador(torneo, usuario);
		} catch (GanadorYaExistenteException e) {
			modelo.put("errorGanador",e.getMessage());
			return new ModelAndView ("errores",modelo);
		}
        }else {
        	
        	modelo.put("errorGanador","Torneo o Participante inexistente");
        }
        
		return new ModelAndView("ganadorExitoso", modelo);
		
		
	}
}
