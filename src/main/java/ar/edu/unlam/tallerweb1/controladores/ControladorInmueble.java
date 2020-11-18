package ar.edu.unlam.tallerweb1.controladores;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import ar.edu.unlam.tallerweb1.servicios.ServicioCiudad;
import ar.edu.unlam.tallerweb1.servicios.ServicioInmueble;
import ar.edu.unlam.tallerweb1.servicios.ServicioProvincia;

@Controller
public class ControladorInmueble {

	public ServicioInmueble servicioInmueble;
	public ServicioCiudad servicioCiudad;
    public ServicioProvincia servicioProvincia;

	@Autowired
	public ControladorInmueble(ServicioInmueble servicioInmueble,ServicioCiudad servicioCiudad, ServicioProvincia servicioProvincia) {

		this.servicioInmueble = servicioInmueble;
		this.servicioCiudad = servicioCiudad;
		this.servicioProvincia = servicioProvincia;

	}
	
	@RequestMapping(path = "ver-inmuebles", method = RequestMethod.GET)
	public ModelAndView mostrarInmuebles() {

		ModelMap modelo = new ModelMap();
		modelo.put("inmuebles", servicioInmueble.mostrarInmuebles());

		return new ModelAndView("InmueblesParaAlquilar", modelo);
	}

	@RequestMapping(path = "formulario-inmueble", method = RequestMethod.GET)
	public ModelAndView nuevoInmueble() {

		ModelMap modelo = new ModelMap();
		Inmueble inmueble = new Inmueble();
		modelo.put("provincias", servicioProvincia.mostrarProvincias());
		modelo.put("inmueble", inmueble);
		modelo.put("ciudades", servicioCiudad.mostrarCiudades());

		return new ModelAndView("publicarInmueble", modelo);

	}

	public void guardarFoto(MultipartFile foto)  {

		if (!foto.isEmpty()) {
			try {

				String ruta = "C://Producto//inmuebles";

				byte[] bytes;
				bytes = foto.getBytes();
				Path rutaAbsoluta = Paths.get(ruta + "//" + foto.getOriginalFilename());
				Files.write(rutaAbsoluta, bytes);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
			}

		}

	}

	@RequestMapping(path="crear-inmueble",method=RequestMethod.POST)
	public ModelAndView crearInmueble(@RequestParam(name="file",required=false)
	MultipartFile foto, Inmueble inmueble, RedirectAttributes flash) throws FileNotFoundException  {
		
		ModelMap modelo = new ModelMap();
		
		
		if(foto.isEmpty()) {
			
			
			modelo.put("error", "No seleccióno una foto");
			
			
		}
		
		else {
			
			guardarFoto(foto);
			inmueble.setFoto(foto.getOriginalFilename());
			servicioInmueble.guardarInmueble(inmueble);
			
			return new ModelAndView ("redirect:/ver-inmuebles");
		}
		
		return new ModelAndView("errorSubidaDeImagen", modelo);
		
		
	}
	
  

	@RequestMapping(path = "buscar-inmueble", method = RequestMethod.GET)
	public ModelAndView mostrarTorneosPorJuego(HttpServletRequest request) {

		ModelMap modelo = new ModelMap();
		String provincia = request.getParameter("busqueda");
		String localidad = request.getParameter("busqueda");
		modelo.put("inmueblesBusqueda", servicioInmueble.buscarInmueble(provincia, localidad));

		return new ModelAndView("inmueblesPorBusqueda", modelo);
	}



	@RequestMapping(path = "ver-inmueble-detalle", method = RequestMethod.GET)
	public ModelAndView verDetalle(@RequestParam("id") Long id) {

		
		Inmueble inmuebleBuscado = servicioInmueble.verDetallesInmueble(id);

		ModelMap modelo = new ModelMap();

		modelo.put("detalleInmueble", inmuebleBuscado);

		return new ModelAndView("inmuebleDetalle", modelo);

	}

}
