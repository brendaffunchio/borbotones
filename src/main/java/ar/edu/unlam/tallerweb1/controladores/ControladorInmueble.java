package ar.edu.unlam.tallerweb1.controladores;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.DireccionDuplicadaException;
import ar.edu.unlam.tallerweb1.modelo.DireccionNoValidaException;
import ar.edu.unlam.tallerweb1.modelo.FotoInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.modelo.InmuebleInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.InmuebleNoDisponibleException;
import ar.edu.unlam.tallerweb1.modelo.UsuarioInexistenteException;
import ar.edu.unlam.tallerweb1.servicios.ServicioCiudad;
import ar.edu.unlam.tallerweb1.servicios.ServicioDireccion;
import ar.edu.unlam.tallerweb1.servicios.ServicioFoto;
import ar.edu.unlam.tallerweb1.servicios.ServicioInmueble;
import ar.edu.unlam.tallerweb1.servicios.ServicioProvincia;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuarios;

@Controller
public class ControladorInmueble {

	@Value("${ruta.imagenes.torneos}")
	private String ruta;
	public ServicioInmueble servicioInmueble;
	public ServicioUsuarios servicioUsuario;
	public ServicioCiudad servicioCiudad;
	public ServicioProvincia servicioProvincia;
	public ServicioDireccion servicioDireccion;
	public ServicioFoto servicioFoto;

	@Autowired
	public ControladorInmueble(ServicioInmueble servicioInmueble, ServicioCiudad servicioCiudad,
			ServicioProvincia servicioProvincia, ServicioDireccion servicioDireccion, ServicioUsuarios servicioUsuario,
			ServicioFoto servicioFoto) {

		this.servicioInmueble = servicioInmueble;
		this.servicioCiudad = servicioCiudad;
		this.servicioProvincia = servicioProvincia;
		this.servicioDireccion = servicioDireccion;
		this.servicioUsuario = servicioUsuario;
		this.servicioFoto = servicioFoto;

	}

	@RequestMapping(path = "ver-inmuebles", method = RequestMethod.GET)
	public ModelAndView mostrarInmuebles() {

		ModelMap modelo = new ModelMap();
		modelo.put("inmuebles", servicioInmueble.listarTodosLosInmueblesDisponibles());
		modelo.put("provincias", servicioProvincia.listarTodasProvincias());
		

		return new ModelAndView("InmueblesParaAlquilar", modelo);
	}

	@RequestMapping(path = "ver-formulario-inmueble", method = RequestMethod.GET)
	public ModelAndView nuevoInmueble() {

		ModelMap modelo = new ModelMap();
		Inmueble inmueble = new Inmueble();
		modelo.put("inmueble", inmueble);
		modelo.put("provincias", servicioProvincia.listarTodasProvincias());
		modelo.put("ciudades", servicioCiudad.listarCiudades());

		return new ModelAndView("publicarInmueble", modelo);

	}

	@RequestMapping(path = "crear-inmueble", method = RequestMethod.POST)
	public ModelAndView crearInmueble(@RequestParam(name = "calle") String calle,
			@RequestParam(name = "numero") Integer numero,
			@RequestParam(name = "file", required = false) MultipartFile foto, Inmueble inmueble,
			RedirectAttributes flash) {

		ModelMap modelo = new ModelMap();

		try {
			servicioFoto.guardarFoto(inmueble, foto);
			Direccion direccion = servicioDireccion.buscarDireccion(calle, numero);
			servicioInmueble.guardarInmueble(inmueble, direccion);

		} catch (DireccionDuplicadaException | DireccionNoValidaException | FotoInexistenteException
				| FileUploadException | IOException e) {
			modelo.put("errorInmueble", e.getMessage());
			return new ModelAndView("errores", modelo);
		}

		return new ModelAndView("redirect:/ver-inmuebles");

	}

	@RequestMapping(path = "buscar-inmueble", method = RequestMethod.GET)
	public ModelAndView buscarInmueble(HttpServletRequest request, @RequestParam (name = "provinciaId") Long provinciaId) {

		ModelMap modelo = new ModelMap();
		modelo.put("provincias", servicioProvincia.listarTodasProvincias());

		
		String nombreCiudad = request.getParameter("ciudad");

		if (servicioInmueble.buscarInmueble(provinciaId, nombreCiudad).isEmpty()) {

			modelo.put("error", "No se encontro ningun inmueble.");
		}

		else {

			modelo.put("inmuebles", servicioInmueble.buscarInmueble(provinciaId, nombreCiudad));
		}

		return new ModelAndView("InmueblesParaAlquilar", modelo);
	}

	@RequestMapping(path = "filtrar-inmuebles", method = RequestMethod.GET)
	public ModelAndView filtrarInmueblesPorPrecio(@RequestParam(name = "desde") Double desdePrecio,
			@RequestParam(name = "hasta") Double hastaPrecio) {

		ModelMap modelo = new ModelMap();
		modelo.put("provincias", servicioProvincia.listarTodasProvincias());
		modelo.put("inmuebles", servicioInmueble.filtrarInmueblesPorPrecio(desdePrecio, hastaPrecio));
		

		return new ModelAndView("InmueblesParaAlquilar", modelo);

	}

	@RequestMapping(path = "ver-inmueble-detalle", method = RequestMethod.GET)
	public ModelAndView verDetalle(@RequestParam("inmuebleId") Long inmuebleId) {

		Inmueble inmuebleBuscado = servicioInmueble.consultarInmueblePorId(inmuebleId);
		

		ModelMap modelo = new ModelMap();

		modelo.put("detalleInmueble", inmuebleBuscado);

		return new ModelAndView("inmuebleDetalle", modelo);

	}

	@RequestMapping(path = "alquilar", method = RequestMethod.POST)
	public ModelAndView agregarInquilino(@RequestParam(name = "inmuebleId") Long inmuebleId,
			@RequestParam(name = "usuarioId") Long usuarioId) {
			ModelMap modelo = new ModelMap();

		try {
			servicioInmueble.agregarInquilino(inmuebleId, usuarioId);
		} catch (InmuebleNoDisponibleException | InmuebleInexistenteException | UsuarioInexistenteException e) {
			modelo.put("errorAlquilar", e.getMessage());
			return new ModelAndView("errores", modelo);
		}

		return new ModelAndView("redirect:/ver-inmuebles");
	}

	@RequestMapping(path = "ver-inmueble-alquilado-detalle", method = RequestMethod.GET)
	public ModelAndView verDetalleInmuebleAlquilado(@RequestParam("inmuebleId") Long inmuebleId) {

		Inmueble inmuebleBuscado = servicioInmueble.consultarInmueblePorId(inmuebleId);

		ModelMap modelo = new ModelMap();

		modelo.put("detalleInmuebleAlquilado", inmuebleBuscado);

		return new ModelAndView("inmuebleDetalleAlquilado", modelo);

	}

}
