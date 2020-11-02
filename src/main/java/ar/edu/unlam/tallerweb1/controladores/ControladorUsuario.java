package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuarios;

@Controller
public class ControladorUsuario {

	
	@Autowired
	private ServicioUsuarios servicioUsuarios;
	
	@Autowired
	public ControladorUsuario(ServicioUsuarios servicioUsuarios){
		this.servicioUsuarios = servicioUsuarios;
	}

	

	@RequestMapping (path="ver-formulario-usuario", method= RequestMethod.GET)
	public ModelAndView nuevoUsuario() {
		ModelMap model = new ModelMap();
		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		
		return new ModelAndView ("formularioUsuario", model);
		
	}
	
	
	@RequestMapping (path="crear-usuario",method=RequestMethod.POST)
	public ModelAndView crearUsuario (@ModelAttribute ("usuario") Usuario usuario, HttpServletRequest request) {
		ModelMap modelo = new ModelMap();
		String emailParametro = request.getParameter("email");

		List<String> emails = servicioUsuarios.mostrarEmails();	
		
		Boolean esRepetido = servicioUsuarios.devolverEstadoDelEmail(emailParametro);
		
		for (String email : emails) {
			
			if(!email.equals(emailParametro)) {
				
				esRepetido = true;
				
				
			}
			else {
				
				esRepetido = false;
				
				
			}
		}
		
		if(esRepetido) {
			servicioUsuarios.guardarUsuario(usuario);
			
			return new ModelAndView ("registracionExitosa");
			
		}
		
		else {
			
			modelo.put("error","Su email ya existe");
		}
		
		
				return new ModelAndView ("formularioUsuario",modelo);
			
}
		
		/*for(!usuario.getEmail().equals(email)) {
		
		servicioUsuarios.guardarUsuario(usuario);
		
		return new ModelAndView ("registracionExitosa");
		
	}else {
		
		modelo.put("error","Su email ya existe");
	}
		
		return new ModelAndView ("formularioUsuario");
		*/
}
	

