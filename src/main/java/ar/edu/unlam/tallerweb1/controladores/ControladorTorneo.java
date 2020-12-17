package ar.edu.unlam.tallerweb1.controladores;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unlam.tallerweb1.modelo.CupoExcedidoException;
import ar.edu.unlam.tallerweb1.modelo.FotoInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.GanadorYaExisteException;
import ar.edu.unlam.tallerweb1.modelo.InmuebleInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.ParticipanteDuplicadoException;
import ar.edu.unlam.tallerweb1.modelo.ParticipanteInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.Torneo;
import ar.edu.unlam.tallerweb1.modelo.TorneoInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.UsuarioInexistenteException;
import ar.edu.unlam.tallerweb1.servicios.ServicioFoto;
import ar.edu.unlam.tallerweb1.servicios.ServicioTorneo;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuarios;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ControladorTorneo {

	private ServicioTorneo servicioTorneo;

	private ServicioUsuarios servicioUsuario;

	private ServicioFoto servicioFoto;

	@Autowired
	public ControladorTorneo(ServicioTorneo servicioTorneo, ServicioUsuarios servicioUsuario,
			ServicioFoto servicioFoto) {
		this.servicioTorneo = servicioTorneo;
		this.servicioUsuario = servicioUsuario;
		this.servicioFoto = servicioFoto;
	}

	@RequestMapping(path = "ver-torneos", method = RequestMethod.GET)
	public ModelAndView mostrarTorneos(@RequestParam("usuarioId") Long usuarioId) {

		ModelMap modelo = new ModelMap();

		if (usuarioId != null) {
			modelo.put("torneos", servicioTorneo.listarTorneosConDistancia(usuarioId));
		} else {
			modelo.put("torneos", servicioTorneo.listarTodosLosTorneos());
		}
		return new ModelAndView("torneosParaParticipar", modelo);

	}

	@RequestMapping(path = "ver-torneos-ordenados-distancia", method = RequestMethod.GET)
	public ModelAndView mostrarTorneosSegunDistancia() {

		ModelMap modelo = new ModelMap();

		modelo.put("torneos", servicioTorneo.ordenarTorneosSegunDistancia());
		return new ModelAndView("torneosParaParticipar", modelo);

	}

	@RequestMapping(path = "filtrar-torneos", method = RequestMethod.GET)
	public ModelAndView filtrarTorneosSegunRadio(@RequestParam(name = "desde") Double desdeKm,
			@RequestParam(name = "hasta") Double hastaKm) {

		ModelMap modelo = new ModelMap();
		modelo.put("torneos", servicioTorneo.filtrarTorneosPorDistancia(desdeKm, hastaKm));

		return new ModelAndView("torneosParaParticipar", modelo);

	}

	@RequestMapping(path = "ver-formulario-torneo", method = RequestMethod.GET)
	public ModelAndView nuevoTorneo(@RequestParam("usuarioId") Long usuarioId) {

		Torneo torneo = new Torneo();

		ModelMap modelo = new ModelMap();

		modelo.put("torneo", torneo);
		modelo.put("inmuebles", servicioUsuario.listarInmueblesAlquiladosDeUnUsuario(usuarioId));

		return new ModelAndView("organizarTorneos", modelo);

	}

	@RequestMapping(path = "crear-torneo", method = RequestMethod.POST)
	public ModelAndView crearTorneo(@RequestParam(name = "file", required = false) MultipartFile foto,
			@RequestParam(name = "creadorId") Long creadorId,
			@RequestParam(name = "inmuebleId", required = false) Long inmuebleId, Torneo torneo,
			RedirectAttributes flash) {

		ModelMap modelo = new ModelMap();

	
			try {
				servicioFoto.guardarFoto(torneo, foto);
				servicioTorneo.guardarTorneo(torneo, creadorId, inmuebleId);
			} catch (FotoInexistenteException |FileUploadException|IOException e) {
				modelo.put("errorFotoTorneo", e.getMessage());
				return new ModelAndView("errores", modelo);
			} catch (InmuebleInexistenteException e) {
				modelo.put("errorInmuebleInexistente", e.getMessage());
				return new ModelAndView("errores", modelo);
			} catch (UsuarioInexistenteException e) {
				modelo.put("errorCreadorTorneo", e.getMessage());
				return new ModelAndView("errores", modelo);
			}

		return new ModelAndView("torneoExitoso");
	}

	@RequestMapping(path = "participar", method = RequestMethod.POST)
	public ModelAndView agregarParticipante(@RequestParam(name = "torneoId") Long torneoId,
			@RequestParam(name = "usuarioId") Long usuarioId) {
		ModelMap modelo = new ModelMap();

		try {
			servicioTorneo.agregarParticipante(torneoId, usuarioId);
		} catch (ParticipanteDuplicadoException | CupoExcedidoException | TorneoInexistenteException
				| UsuarioInexistenteException e) {
			modelo.put("errorParticipar", e.getMessage());
			return new ModelAndView("errores", modelo);
		}
		;

		return new ModelAndView("participacionExitosa");
	}

	@RequestMapping(path = "desubscribirse", method = RequestMethod.POST)
	public ModelAndView eliminarParticipante(@RequestParam(name = "torneoId") Long torneoId,
			@RequestParam(name = "usuarioId", required = false) Long usuarioId) {

		ModelMap modelo = new ModelMap();

		try {
			servicioTorneo.eliminarParticipante(torneoId, usuarioId);
		} catch (ParticipanteInexistenteException | TorneoInexistenteException | UsuarioInexistenteException e) {
			modelo.put("errorDesubscribirse", e.getMessage());
			return new ModelAndView("errores", modelo);
		}

		return new ModelAndView("perfilUsuario");
	}

	@RequestMapping(path = "buscar-torneo", method = RequestMethod.GET)
	public ModelAndView mostrarTorneosPorJuego(@RequestParam (name = "categoria") String categoria, @RequestParam (name = "juego") String juego ) {

		ModelMap modelo = new ModelMap();

		if (servicioTorneo.buscarTorneo(categoria, juego).isEmpty()) {

			modelo.put("error", "No se encontro ningun torneo.");
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

		modelo.put("participantes", servicioTorneo.listarParticipantesDelTorneo(torneoId));

		return new ModelAndView("participantes", modelo);

	}

	@RequestMapping(path = "ver-lista-de-participantes-para-elegir-ganador", method = RequestMethod.GET)
	public ModelAndView verListaDeParticipantesDelTorneoParaElegirGanador(@RequestParam("torneoId") Long torneoId) {

		ModelMap modelo = new ModelMap();

		modelo.put("participantes", servicioTorneo.listarParticipantesDelTorneo(torneoId));
		modelo.put("torneoId", torneoId);

		return new ModelAndView("ganadorDelTorneo", modelo);

	}

	@RequestMapping(path = "elegirGanador", method = RequestMethod.POST)
	public ModelAndView elegirGanador(@RequestParam(name = "ganadorId") Long ganadorId,
			@RequestParam(name = "torneoGanadoId") Long torneoGanadoId) {

		ModelMap modelo = new ModelMap();

		try {
			servicioTorneo.elegirGanador(torneoGanadoId, ganadorId);
			Torneo torneo = servicioTorneo.consultarTorneoPorId(torneoGanadoId);
			modelo.put("torneo", torneo);
		} catch (GanadorYaExisteException | TorneoInexistenteException | UsuarioInexistenteException e) {
			modelo.put("errorGanador", e.getMessage());
			return new ModelAndView("errores", modelo);
		}

		return new ModelAndView("ganadorExitoso", modelo);

	}
}
