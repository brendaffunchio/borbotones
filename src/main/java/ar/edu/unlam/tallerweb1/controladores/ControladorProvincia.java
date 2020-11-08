package ar.edu.unlam.tallerweb1.controladores;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import antlr.collections.List;
import ar.edu.unlam.tallerweb1.modelo.Provincia;
import ar.edu.unlam.tallerweb1.servicios.ServicioProvincia;

@Controller
public class ControladorProvincia {
	
	
	
	@Autowired
	ServicioProvincia servicioProvincia;
	
	public ControladorProvincia(ServicioProvincia servicioProvinica) {
		
		this.servicioProvincia = servicioProvincia;
	}
	
	
	
	public void guardarLasProvincias() {

		LinkedList <Provincia> provincias = new LinkedList<Provincia>();
		
		provincias = servicioProvincia.mostrarProvincias();
		
		

	}
	

}
