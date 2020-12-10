package ar.edu.unlam.tallerweb1.modelo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClaseBean {

	ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
            "/spring-servelt.xml");
    
	Ruta rutaInmueble = (Ruta) applicationContext.getBean("ruta");

	
	public String devolverRutaInmueble() {
		
		return rutaInmueble.getRutaInmueble();
	}
	

}
