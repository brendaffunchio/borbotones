package ar.edu.unlam.tallerweb1.controladores;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadException;
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

import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.DireccionDuplicadaException;
import ar.edu.unlam.tallerweb1.modelo.DireccionNoValidaException;
import ar.edu.unlam.tallerweb1.modelo.FotoInexistenteException;
import ar.edu.unlam.tallerweb1.modelo.Inmueble;
import ar.edu.unlam.tallerweb1.servicios.ServicioCiudad;
import ar.edu.unlam.tallerweb1.servicios.ServicioDireccion;
import ar.edu.unlam.tallerweb1.servicios.ServicioInmueble;
import ar.edu.unlam.tallerweb1.servicios.ServicioProvincia;

@Controller
public class ControladorInmueble {

	public ServicioInmueble servicioInmueble;
	public ServicioCiudad servicioCiudad;
	public ServicioProvincia servicioProvincia;
	public ServicioDireccion servicioDireccion;

	@Autowired
	public ControladorInmueble(ServicioInmueble servicioInmueble, ServicioCiudad servicioCiudad,
			ServicioProvincia servicioProvincia, ServicioDireccion servicioDireccion) {

		this.servicioInmueble = servicioInmueble;
		this.servicioCiudad = servicioCiudad;
		this.servicioProvincia = servicioProvincia;
		this.servicioDireccion = servicioDireccion;

	}

	@RequestMapping(path = "ver-inmuebles", method = RequestMethod.GET)
	public ModelAndView mostrarInmuebles() {

		ModelMap modelo = new ModelMap();
		modelo.put("inmuebles", servicioInmueble.mostrarInmuebles());
		modelo.put("provincias", servicioProvincia.mostrarProvincias());

		return new ModelAndView("InmueblesParaAlquilar", modelo);
	}

	@RequestMapping(path = "ver-formulario-inmueble", method = RequestMethod.GET)
	public ModelAndView nuevoInmueble() {

		ModelMap modelo = new ModelMap();
		Inmueble inmueble = new Inmueble();
		modelo.put("inmueble", inmueble);
		modelo.put("provincias", servicioProvincia.mostrarProvincias());
		modelo.put("ciudades", servicioCiudad.mostrarCiudades());

		return new ModelAndView("publicarInmueble", modelo);

	}

	@RequestMapping(path = "crear-inmueble", method = RequestMethod.POST)
	public ModelAndView crearInmueble(@RequestParam(name = "calle") String calle,@RequestParam(name = "numero") Integer numero,
			@RequestParam(name = "file", required = false) MultipartFile foto, Inmueble inmueble,RedirectAttributes flash) {

		ModelMap modelo = new ModelMap();
		Direccion direccion = servicioDireccion.crearDireccion(calle, numero);
		
		try {
			servicioInmueble.validarFoto(foto);
			servicioInmueble.guardarFoto(foto);	
		} catch (FotoInexistenteException | FileUploadException | IOException e) {
			modelo.put("error", e.getMessage());
			return new ModelAndView("errores", modelo);
		}
		servicioInmueble.setFoto(inmueble, foto.getOriginalFilename());
		try {
			servicioInmueble.guardarInmueble(inmueble, direccion);
		} catch (DireccionNoValidaException | DireccionDuplicadaException e) {
			modelo.put("errorDireccionInmueble", e.getMessage());
			return new ModelAndView("errores", modelo);
		}

		return new ModelAndView("redirect:/ver-inmuebles");

	}

	@RequestMapping(path = "buscar-inmueble", method = RequestMethod.GET)
	public ModelAndView mostrarTorneosPorJuego(HttpServletRequest request) {

		ModelMap modelo = new ModelMap();
		String provinciaId = request.getParameter("provinciaId");
		String nombreCiudad = request.getParameter("ciudad");

		if (servicioInmueble.buscarInmueble(Long.parseLong(provinciaId), nombreCiudad).size() == 0) {

			modelo.put("error", "No se encontró ningún inmueble.");
		}

		else {

			modelo.put("inmueblesBusqueda", servicioInmueble.buscarInmueble(Long.parseLong(provinciaId), nombreCiudad));
		}

		return new ModelAndView("inmueblesPorBusqueda", modelo);
	}

	@RequestMapping(path = "ver-inmueble-detalle", method = RequestMethod.GET)
	public ModelAndView verDetalle(@RequestParam("inmuebleId") Long inmuebleId) {

		Inmueble inmuebleBuscado = servicioInmueble.verDetallesInmueble(inmuebleId);

		ModelMap modelo = new ModelMap();

		modelo.put("detalleInmueble", inmuebleBuscado);

		return new ModelAndView("inmuebleDetalle", modelo);

	}

	@RequestMapping(path = "alquilar", method = RequestMethod.POST)
	public ModelAndView agregarInquilino(@RequestParam(name = "inmuebleId") Long inmuebleId,
			@RequestParam(name = "usuarioId") Long usuarioId) {

		servicioInmueble.agregarInquilino(inmuebleId, usuarioId);

		return new ModelAndView("redirect:/ver-inmuebles");
	}

	@RequestMapping(path = "ver-inmueble-alquilado-detalle", method = RequestMethod.GET)
	public ModelAndView verDetalleInmuebleAlquilado(@RequestParam("inmuebleId") Long inmuebleId) {

		Inmueble inmuebleBuscado = servicioInmueble.verDetallesInmueble(inmuebleId);

		ModelMap modelo = new ModelMap();

		modelo.put("detalleInmuebleAlquilado", inmuebleBuscado);

		return new ModelAndView("inmuebleDetalleAlquilado", modelo);

	}

}
